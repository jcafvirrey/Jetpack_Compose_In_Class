package com.example.composecatalogclass.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmAction(modifier: Modifier = Modifier) {

    // ESTADO 1 (obligatorio): mensaje que se mostrará en el Text superior.
    // - Al ser "state", si cambia, Compose redibuja automáticamente la parte afectada.
    var message by rememberSaveable { mutableStateOf("Acción pendiente") }

    // ESTADO 2 (obligatorio): controla si el botón está habilitado.
    // - Empezamos en true para que el botón se pueda pulsar.
    var isEnabled by rememberSaveable { mutableStateOf(true) }

    // (No es estado, es un valor derivado del estado isEnabled)
    // - Si el botón está habilitado => "Pendiente"
    // - Si está deshabilitado => "Confirmado"
    val estadoTexto = if (isEnabled) "Pendiente" else "Confirmado"

    Column(modifier = modifier) {

        // 1) Text superior con el mensaje actual (depende del estado "message")
        Text(text = message)

        // 2) Botón Confirmar (depende del estado "isEnabled")
        Button(
            // IMPORTANTE: el enunciado exige usar enabled explícitamente
            enabled = isEnabled,
            onClick = {
                // Cuando se pulsa el botón:
                // 1) Cambiamos el mensaje
                message = "Acción confirmada"

                // 2) Deshabilitamos el botón
                isEnabled = false
            }
        ) {
            // El enunciado exige que el contenido sea un Text
            Text(text = "Confirmar")
        }

        // 3) Text inferior "Estado: <valor>"
        // - Observa cómo se actualiza solo al cambiar isEnabled
        Text(text = "Estado: $estadoTexto")
    }
}
@Composable
fun ButtonStyleSelector(modifier: Modifier = Modifier) {

    // ESTADO 1 (obligatorio): qué tipo de botón está seleccionado.
    // Guardamos un String porque el enunciado lo pide así ("Button", "Outlined", "Text").
    var selectedButton by rememberSaveable { mutableStateOf("Button") }

    // ESTADO 2 (obligatorio): uno adicional (a elección del alumno).
    // Usaremos isEnabled para poder activar/desactivar el botón de ejemplo.
    var isEnabled by rememberSaveable { mutableStateOf(true) }

    Column(modifier = modifier) {

        // 1) Text superior que refleja el estado "selectedButton"
        Text(text = "Botón seleccionado: $selectedButton")

        // 2) Botones de selección (cuando se pulsan, actualizan el estado)
        Row {
            Button(onClick = { selectedButton = "Button" }) {
                Text("Button")
            }

            OutlinedButton(onClick = { selectedButton = "Outlined" }) {
                Text("OutlinedButton")
            }

            TextButton(onClick = { selectedButton = "Text" }) {
                Text("TextButton")
            }
        }

        // Botón extra para demostrar el 2º estado (isEnabled) sin usar componentes no vistos.
        // Si no quieres incluirlo en clase todavía, puedes quitar este botón y dejar isEnabled = true.
        Button(onClick = { isEnabled = !isEnabled }) {
            Text(if (isEnabled) "Deshabilitar ejemplo" else "Habilitar ejemplo")
        }

        // Separador visual (opcional)
        Text(text = "Ejemplo:")

        // ──────────────────────────────────────────────────────────────
        // 3) Área inferior: mostramos un botón de ejemplo según selectedButton
        // ──────────────────────────────────────────────────────────────

        // PROPIEDAD INVESTIGADA:
        // shape = RoundedCornerShape(16.dp)
        // ¿Para qué sirve?
        // - Permite cambiar la forma del botón, redondeando sus esquinas.
        // - Cuanto mayor es el valor (dp), más redondeadas serán las esquinas.
        val exampleShape = RoundedCornerShape(16.dp)

        when (selectedButton) {

            "Button" -> {
                Button(
                    enabled = isEnabled,
                    onClick = { /* En este ejemplo, no necesitamos acción */ },
                    // Propiedad investigada aplicada al botón de ejemplo
                    shape = exampleShape
                ) {
                    Text("Botón de ejemplo (Button)")
                }
            }

            "Outlined" -> {
                OutlinedButton(
                    enabled = isEnabled,
                    onClick = { },
                    // Propiedad investigada aplicada al botón de ejemplo
                    shape = exampleShape
                    // Nota: en OutlinedButton también podríamos investigar "border",
                    // pero con shape ya cumplimos el requisito.
                ) {
                    Text("Botón de ejemplo (Outlined)")
                }
            }

            "Text" -> {
                TextButton(
                    enabled = isEnabled,
                    onClick = { },
                    // Propiedad investigada aplicada al botón de ejemplo
                    shape = exampleShape
                ) {
                    Text("Botón de ejemplo (Text)")
                }
            }
        }
    }
}