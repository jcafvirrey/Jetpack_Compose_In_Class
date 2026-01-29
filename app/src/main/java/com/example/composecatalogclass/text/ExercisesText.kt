package com.example.composecatalogclass.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun MyParentForm(modifier: Modifier){
    // Estado único que almacena el texto introducido por el usuario.
    // Este estado es compartido por ambos TextField (state hoisting).

    Column(modifier=modifier){
        //SimpleForm(value=name,onValueChange={name = it})
        ValidatedInput(modifier)
    }
}
@Composable
fun SimpleForm(value:String, onValueChange:(String)->Unit) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it)
        },
        label = { Text("Nombre") },                   // Etiqueta del campo
        placeholder = { Text("Introduce tu nombre") } // Texto de ayuda
    )

    // Texto informativo que refleja el estado actual
    Text(text = "Nombre introducido: $value")

    // ─────────────────────────────────────────────
    // TextField inferior: SOLO LECTURA
    // ─────────────────────────────────────────────
    TextField(
        value = value,          // Mismo estado que el campo superior
        onValueChange = {},   // No se modifica porque es solo lectura
        label = { Text("Nombre (solo lectura)") },
        readOnly = true        // El campo no permite edición
    )
}

@Composable
fun ValidatedInput(modifier: Modifier = Modifier) {
    // Estado 1: texto del usuario
    var username by remember { mutableStateOf("") }

    // Estado 2: indica si el valor actual cumple las reglas
    var isValid by remember { mutableStateOf(false) }

    Column(modifier = modifier) {

        TextField(
            value = username,
            onValueChange = { newValue ->
                // ✅ La validación se realiza dentro de onValueChange (requisito del enunciado).

                // 1) Actualizamos el estado principal
                username = newValue

                // 2) Calculamos si cumple las reglas:
                // - No puede contener espacios
                // - Longitud mínima: 5
                val hasSpaces = username.contains(" ")
                val minLengthOk = username.length >= 5

                // 3) Guardamos el resultado en el estado de validez
                isValid = !hasSpaces && minLengthOk
            },
            label = { Text("Nombre de usuario") }, // Requisito: label

            // Propiedad adicional (investigación): singleLine
            singleLine = true,

            // Propiedad adicional (investigación): keyboardOptions
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )

        // Text informativo: longitud actual
        Text(text = "Longitud: ${username.length} caracteres")

        // Text de aviso: solo aparece cuando NO es válido (requisito).
        // (Didácticamente es habitual evitar mostrar aviso si aún no se ha escrito nada.)
        if (username.isNotEmpty() && !isValid) {
            Text(text = "El nombre no es válido")
        }
    }
}

