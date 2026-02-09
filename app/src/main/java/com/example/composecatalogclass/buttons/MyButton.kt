package com.example.composecatalogclass.buttons

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun MyButtons(modifier: Modifier){
    Column(modifier = modifier){
        Button(onClick = { Log.i("2DAM", "Boton pulsado") },
            enabled = true,
            border = BorderStroke(2.dp, Color.Cyan),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Green,
            )
        ) {
            Text("Pulsame")
        }
        OutlinedButton(onClick = {},
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.Green,

            )) {
            Text("Oultined Button")
        }
        TextButton(onClick = {}){
            Text("TextButton1")
            Text("TextButton2")
        }
        ElevatedButton(onClick = {},
            elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 5.dp) ) {
            Text("Elevated Button")
        }
        FilledTonalButton(onClick = {}) {
            Text("Filled Tonal Button")
        }
        Button(onClick = {}) {
            Text("Button")
        }
    }

}
