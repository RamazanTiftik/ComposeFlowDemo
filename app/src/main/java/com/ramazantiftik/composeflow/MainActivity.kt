package com.ramazantiftik.composeflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramazantiftik.composeflow.ui.theme.ComposeFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFlowTheme {

                val viewModel: MyViewModel by viewModels()
                firstScreen(viewModel = viewModel)
                secondScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun firstScreen(viewModel: MyViewModel){

    val counter=viewModel.countDownTimerFlow.collectAsState(initial = 10)

    Surface(color = MaterialTheme.colors.background) {
        Box(modifier = Modifier.fillMaxSize()){
            Text(text = counter.value.toString(),
                fontSize = 26.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center))
        }
    }

}

@Composable
fun secondScreen(viewModel: MyViewModel){

    val liveDataValue=viewModel.liveData
    val stateFlowValue=viewModel.stateFlow.collectAsState()
    val sharedFlowValue=viewModel.sharedFlow.collectAsState(initial = "")


    Surface(color = MaterialTheme.colors.background) {
        Box(modifier = Modifier.fillMaxSize()){
            
            Column(modifier = Modifier.align(Alignment.Center)) {

                //live data observer'lenmiyor dependencies sorunu

                Text(text = liveDataValue.value ?: "",
                fontSize = 26.sp)
                Button(onClick = {
                    viewModel.changeLiveData()
                }) {
                    Text(text = "LiveData Button")
                }
                
                Spacer(modifier = Modifier.padding(10.dp))

                Text(text = stateFlowValue.value,
                    fontSize = 26.sp)
                Button(onClick = {
                    viewModel.changeStateFlow()
                }) {
                    Text(text = "StateFlow Button")
                }

                Spacer(modifier = Modifier.padding(10.dp))

                Text(text = sharedFlowValue.value,
                    fontSize = 26.sp)
                Button(onClick = {
                    viewModel.changeSharedFlow()
                }) {
                    Text(text = "SharedFlow Button")
                }

            }
            
        }
    }

}