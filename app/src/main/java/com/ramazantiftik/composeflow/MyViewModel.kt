package com.ramazantiftik.composeflow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    val countDownTimerFlow= flow<Int> {
        val countDownFrom=10
        var counter=countDownFrom
        emit(countDownFrom)

        while (counter>0){
            delay(1000)
            counter--
            emit(counter)

        }
    }

    init {
        collectInViewModel()
    }

    private fun collectInViewModel(){


        viewModelScope.launch {

            countDownTimerFlow.collectLatest {
                //delay(2000)
                println("counter is: ${it}")
            }


            /*
            countDownTimerFlow.onEach {
                println(it)
            }.launchIn(viewModelScope)
             */

        }
            /*
            countDownTimerFlow
                .filter {
                    it%3==0
                }
                .map {
                    it+it
                }
                .collect{
                println("counter is: ${it}")
            }
        }
             */


        /*viewModelScope.launch {
            countDownTimer.collect{
                println("counter is: ${it}")
            }
        }
         */


    }


    //LiveData Comparisons

    private val _liveData=MutableLiveData<String>("KotlinLiveData")
    val liveData: LiveData<String> = _liveData

    private val _stateFlow= MutableStateFlow("KotlinStateFlow")
    val stateFlow= _stateFlow.asStateFlow()

    private val _sharedFlow= MutableSharedFlow<String>()
    val sharedFlow= _sharedFlow.asSharedFlow()

    fun changeLiveData(){
        _liveData.value="Live Data"
    }

    fun changeStateFlow(){
        _stateFlow.value="State Flow"
    }

    fun changeSharedFlow(){
        viewModelScope.launch {
            _sharedFlow.emit("Shared Flow")
        }
    }


}