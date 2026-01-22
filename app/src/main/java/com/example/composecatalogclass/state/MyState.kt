package com.example.composecatalogclass.state

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MyState(modifier: Modifier = Modifier) {
    var number by rememberSaveable { mutableStateOf(value = 0) }
    var number2 by rememberSaveable { mutableStateOf(value = 0) }
    Column(modifier = modifier){
        StateExample1(number){number +=1}
        StateExample2(number = number2, onClick = {number2 -=1})
        Button(onClick = {number= 0
            number2 = 0}) {
            Text("Reset")
        }
    }
}

@Composable
fun StateExample1(number: Int, onClick: ()->Unit) {
    Text(text = "Pulsame-1: $number", modifier = Modifier.clickable { onClick() })
}
@Composable
fun StateExample2(number: Int, onClick: () -> Unit) {
    Text(text = "Pulsame-2: $number", modifier = Modifier.clickable { onClick() })
}