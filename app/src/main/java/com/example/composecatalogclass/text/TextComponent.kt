package com.example.composecatalogclass.text

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
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
            modifier = Modifier.clickable {}
        )
        Text(
            "Align",
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun MyTextFieldParent(modifier: Modifier) {
    var user by remember { mutableStateOf("Hola Mundo") }
    var value by remember { mutableStateOf("") }
    var value3 by remember { mutableStateOf("") }
    var value4 by remember {mutableStateOf("")}
    var password by remember {mutableStateOf("")}
    var outlined by remember {mutableStateOf("")}

    Column(modifier = modifier) {
//        MyTextField(user = user) { user = it }
//        Spacer(Modifier.height(5.dp))
//        MySecondTextField(value) {value = it }
//        Spacer(Modifier.height(5.dp))
//        MyThirdTextField(value3) {value3 = it }
//        Spacer(Modifier.height(5.dp))
//        MyAdvancedTextField(value4) {value4 = it }
        MyPasswordTextField(password) { password = it}
        MyOutLinedTextField(outlined){outlined = it}
    }
}

@Composable
fun MyTextField(user: String, onUserChange: (String) -> Unit) {
    TextField(value = user,
        onValueChange = { onUserChange(it) },
        readOnly = user.isEmpty())
}
@Composable
fun MySecondTextField(value: String, onValueChange: (String)->Unit){
    TextField(value,
        onValueChange = {onValueChange(it)},
        placeholder = {
            Text("Profesor")
        } )
}

@Composable
fun MyThirdTextField(value: String, onValueChange: (String) -> Unit) {
    TextField(
        value,
        onValueChange = { onValueChange(it) },
        placeholder = {
            Text("PlaceHolder")
        },
        label = {
            Text("Hola Etiqueta")
        })
}

@Composable
fun MyAdvancedTextField(value:String, onValueChange: (String) -> Unit){
    TextField(value,
        onValueChange = {
            if(it.contains("a")){
                onValueChange(it.replace("a","@"))
            }else{
                onValueChange(it)
            }
        })
}
@Composable
fun MyPasswordTextField(value: String, onValueChange: (String) -> Unit){
    var passwordHidden: Boolean by remember { mutableStateOf(true) }
    TextField(
        value,
        onValueChange = {onValueChange(it)},
        singleLine = true,
        label = {Text("Introduce tu contraseña:")},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if(passwordHidden){
            PasswordVisualTransformation()
        }else{
            VisualTransformation.None
        },
        trailingIcon = {
            Text(
                text = if(passwordHidden) "Mostrar" else "Ocultar",
                modifier = Modifier.clickable{passwordHidden = !passwordHidden}
            )
        }
    )
}

@Composable
fun MyOutLinedTextField(value:String,onValueChange: (String) -> Unit){
    OutlinedTextField(value = value, onValueChange = {onValueChange(it)})
}


