package com.example.composecatalogclass.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable

fun MyBox() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .background(Color.Red)
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.Center
        )
        {
            Text("Hola Mundo")
        }
    }
}

@Composable
fun MyColumn(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Hola 1", modifier = Modifier
                .background(Color.Red)
        )
        Text(
            "Hola 2", modifier = Modifier
                .background(Color.Green)
        )
        Text(
            "Hola 3", modifier = Modifier
                .background(Color.Cyan)
        )
        Text(
            "Hola 4", modifier = Modifier
                .background(Color.Yellow)
        )
        Text(
            "Hola 5", modifier = Modifier
                .background(Color.Blue)
        )
        Text(
            "Hola 1", modifier = Modifier
                .background(Color.Red)
        )
        Text(
            "Hola 2", modifier = Modifier
                .background(Color.Green)
        )
        Text(
            "Hola 3", modifier = Modifier
                .background(Color.Cyan)
        )
        Text(
            "Hola 4", modifier = Modifier
                .background(Color.Yellow)
        )
        Text(
            "Hola 5", modifier = Modifier
                .background(Color.Blue)
        )
        Text(
            "Hola 1", modifier = Modifier
                .background(Color.Red)
        )
        Text(
            "Hola 2", modifier = Modifier
                .background(Color.Green)
        )
        Text(
            "Hola 3", modifier = Modifier
                .background(Color.Cyan)
        )
        Text(
            "Hola 4", modifier = Modifier
                .background(Color.Yellow)
        )
        Text(
            "Hola 5", modifier = Modifier
                .background(Color.Blue)
        )
        Text(
            "Hola 1", modifier = Modifier
                .background(Color.Red)
        )
        Text(
            "Hola 2", modifier = Modifier
                .background(Color.Green)
        )
        Text(
            "Hola 3", modifier = Modifier
                .background(Color.Cyan)
        )
        Text(
            "Hola 4", modifier = Modifier
                .background(Color.Yellow)
        )
        Text(
            "Hola 5", modifier = Modifier
                .background(Color.Blue)
        )
        Text(
            "Hola 1", modifier = Modifier
                .background(Color.Red)
        )
        Text(
            "Hola 2", modifier = Modifier
                .background(Color.Green)
        )
        Text(
            "Hola 3", modifier = Modifier
                .background(Color.Cyan)
        )
        Text(
            "Hola 4", modifier = Modifier
                .background(Color.Yellow)
        )
        Text(
            "Hola 5", modifier = Modifier
                .background(Color.Blue)
        )
        Text(
            "Hola 1", modifier = Modifier
                .background(Color.Red)
        )
        Text(
            "Hola 2", modifier = Modifier
                .background(Color.Green)
        )
        Text(
            "Hola 3", modifier = Modifier
                .background(Color.Cyan)
        )
        Text(
            "Hola 4", modifier = Modifier
                .background(Color.Yellow)
        )
        Text(
            "Hola 5", modifier = Modifier
                .background(Color.Blue)
        )
        Text(
            "Hola 1", modifier = Modifier
                .background(Color.Red)
        )
        Text(
            "Hola 2", modifier = Modifier
                .background(Color.Green)
        )
        Text(
            "Hola 3", modifier = Modifier
                .background(Color.Cyan)
        )
        Text(
            "Hola 4", modifier = Modifier
                .background(Color.Yellow)
        )
        Text(
            "Hola 5", modifier = Modifier
                .background(Color.Blue)
        )
        Text(
            "Hola 1", modifier = Modifier
                .background(Color.Red)
        )
        Text(
            "Hola 2", modifier = Modifier
                .background(Color.Green)
        )
        Text(
            "Hola 3", modifier = Modifier
                .background(Color.Cyan)
        )
        Text(
            "Hola 4", modifier = Modifier
                .background(Color.Yellow)
        )
        Text(
            "Hola 5", modifier = Modifier
                .background(Color.Blue)
        )
        Text(
            "Hola 1", modifier = Modifier
                .background(Color.Red)
        )
        Text(
            "Hola 2", modifier = Modifier
                .background(Color.Green)
        )
        Text(
            "Hola 3", modifier = Modifier
                .background(Color.Cyan)
        )
        Text(
            "Hola 4", modifier = Modifier
                .background(Color.Yellow)
        )
        Text(
            "Hola 5", modifier = Modifier
                .background(Color.Blue)
        )
        Text(
            "Hola 1", modifier = Modifier
                .background(Color.Red)
        )
        Text(
            "Hola 2", modifier = Modifier
                .background(Color.Green)
        )
        Text(
            "Hola 3", modifier = Modifier
                .background(Color.Cyan)
        )
        Text(
            "Hola 4", modifier = Modifier
                .background(Color.Yellow)
        )
        Text(
            "Hola 5", modifier = Modifier
                .background(Color.Blue)
        )
    }
}

@Composable
fun MyRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .horizontalScroll(
                rememberScrollState()

            ),
    ) {
        Text(
            "Hola 1", modifier = Modifier
                .background(Color.Red)
        )
        Text(
            "Hola 2", modifier = Modifier
                .background(Color.Green)
        )
        Text(
            "Hola 3", modifier = Modifier
                .background(Color.Gray)
        )
        Text(
            "Hola 4", modifier = Modifier
                .background(Color.Blue)
        )
        Text(
            "Hola 1", modifier = Modifier
                .background(Color.Red)
        )
        Text(
            "Hola 2", modifier = Modifier
                .background(Color.Green)
        )
        Text(
            "Hola 3", modifier = Modifier
                .background(Color.Gray)
        )
        Text(
            "Hola 4", modifier = Modifier
                .background(Color.Blue)
        )
        Text(
            "Hola 1", modifier = Modifier
                .background(Color.Red)
        )
        Text(
            "Hola 2", modifier = Modifier
                .background(Color.Green)
        )
        Text(
            "Hola 3", modifier = Modifier
                .background(Color.Gray)
        )
        Text(
            "Hola 4", modifier = Modifier
                .background(Color.Blue)
        )
    }
}

@Composable
fun MyComplexLayout(modifier: Modifier) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Red),
            contentAlignment = Alignment.Center
        ) {
            Text("RED", fontWeight = FontWeight.Bold)
        }
        Row {
            Box(modifier = Modifier
                .weight(1f)
                .height(50.dp)
                .background(Color.Gray),
                contentAlignment = Alignment.Center){
                Text("GREY")
            }
            Box(modifier = Modifier
                .weight(1f)
                .height(50.dp)
                .background(Color.White),
                contentAlignment = Alignment.Center){
                Text("WHITE")
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Blue),
            contentAlignment = Alignment.Center
        ) {

            Text("BLUE", fontWeight = FontWeight.Bold)
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Yellow),
            contentAlignment = Alignment.Center
        ) {
            Text("YELLOW", fontWeight = FontWeight.Bold)
        }
    }

}