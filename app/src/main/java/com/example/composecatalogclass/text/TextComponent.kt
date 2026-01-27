package com.example.composecatalogclass.text

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

@Composable
fun MyTexts(modifier: Modifier) {
    Column(modifier = modifier) {
        Text("Hola Mundo")
        Text("Hola Mundo2", color = Color.Red)
        Text("Hola Mundo3", fontSize = 25.sp)
        Text("FontStyle", fontStyle = FontStyle.Italic)
        Text("FontWeight", fontSize = 25.sp, fontWeight = FontWeight.ExtraBold)
        Text("LetterSpacing", letterSpacing = 2.sp)
        Text("TextDecoration", textDecoration = TextDecoration.LineThrough)
        Text(
            "TextDecoration2", textDecoration = TextDecoration.combine(
                listOf(
                    TextDecoration.LineThrough,
                    TextDecoration.Underline
                )
            )
        )
        //Continuación clase día 27-01-2026
        Text(
            "TextDecoration2",

            textDecoration = TextDecoration.LineThrough + TextDecoration.Underline
        )
        Text(
            "Link",
            textDecoration = TextDecoration.Underline,
            color = Color.Blue,
            modifier = Modifier.clickable{}
        )
        Text(
            "Align",
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center
        )
    }

}


