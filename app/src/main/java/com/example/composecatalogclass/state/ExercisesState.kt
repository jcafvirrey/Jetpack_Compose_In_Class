package com.example.composecatalogclass.state

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ModeSelector(modifier: Modifier) {

    // Estado que representa el modo actual de la aplicación.
    // Es un estado de Compose, por lo que cualquier cambio
    // provocará la recomposición de los composables que lo usen.
    var modo by remember { mutableStateOf("NORMAL") }

    Column(modifier = modifier) {

        // Este Text depende del estado 'modo'.
        // Cada vez que 'modo' cambie, este Text se volverá a dibujar.
        Text(text = "Modo actual: $modo")

        // Al pulsar este Text, actualizamos el estado 'modo'.
        // No modificamos la UI directamente, solo el estado.
        Text(
            text = "NORMAL",
            modifier = Modifier.clickable {
                modo = "NORMAL"
            }
        )

        Text(
            text = "SILENCIO",
            modifier = Modifier.clickable {
                modo = "SILENCIO"
            }
        )

        Text(
            text = "VIBRACIÓN",
            modifier = Modifier.clickable {
                modo = "VIBRACIÓN"
            }
        )
    }
}
@Composable
fun UserStatusScreen(modifier: Modifier) {

    // Estado único y centralizado (fuente única de verdad).
    // Se guarda con rememberSaveable para que no se pierda
    // ante recomposiciones o cambios de configuración.
    var status by rememberSaveable { mutableStateOf("Desconectado") }

    Column(modifier = modifier) {

        // Este Text se recompone automáticamente cuando cambia 'status'.
        Text(text = "Estado actual: $status")

        // Cada hijo recibe una acción (callback),
        // pero NO el estado directamente.
        StatusOption(label = "Conectado") {
            status = "Conectado"
        }

        StatusOption(label = "Ausente") {
            status = "Ausente"
        }

        StatusOption(label = "Desconectado") {
            status = "Desconectado"
        }
    }
}
@Composable
fun StatusOption(
    label: String,
    onSelect: () -> Unit
) {
    // Este composable NO tiene estado.
    // Su única función es mostrar información
    // y avisar al padre cuando ocurre un evento.
    Text(
        text = label,
        modifier = Modifier.clickable {
            // El hijo no cambia el estado.
            // Simplemente notifica al padre.
            onSelect()
        }
    )
}