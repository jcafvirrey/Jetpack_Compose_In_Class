

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
 ┌─────────────────────────────────────────────────────────┐
 │ NotificationSoundScreen()  ← Composable principal (pantalla)
 │  - Aquí vive el ESTADO principal de la pantalla:
 │      selectedSound, ignorarSilencio, largo, botonGuardar
 │  - Llama a los componentes hijos y les pasa:
 │      * datos (estado)
 │      * funciones (callbacks) para modificar ese estado
 │
 │  Llama en orden a:
 │    1) TopBar()
 │    2) InfoText()
 │    3) BasicSoundsSection(selected, onSelectedChange)
 │         └── SoundOptionItem(text, selected, onClick)  (varias veces)
 │    4) EnhancedSoundSection(ignorarSilencio, onIgnorarChange, largo, onLargoChange)
 │         └── SwitchCard(title, checked, onCheckedChange) (dos veces)
 │    5) SaveButton(estado)
 └─────────────────────────────────────────────────────────┘

 IDEA CLAVE:
 - Los hijos NO guardan el estado.
 - Los hijos reciben el estado y notifican eventos.
 - El padre decide cómo cambiar el estado. (State Hoisting)
*/

/* ---------------- COLORS ----------------
   Colores reutilizables de la pantalla.
   Tenerlos como constantes ayuda a mantener consistencia visual.
*/
private val DarkBackground = Color(0xFF0E0E0E)
private val CardBackground = Color(0xFF1C1C1E)
private val Green = Color(0xFF2ECC71)
private val Red = Color(0xFFE74C3C)
private val Orange = Color(0xFFF39C12)

/* ---------------- SCREEN ----------------
   Esta es la pantalla principal. Aquí es donde:
   - definimos el estado
   - construimos la estructura (Column)
   - llamamos a composables más pequeños (hijos)
*/
@Composable
fun NotificationSoundScreen(modifier: Modifier = Modifier) {

    // ─────────────────────────────────────────────
    // ESTADOS PRINCIPALES DE LA PANTALLA
    // ─────────────────────────────────────────────
    //
    // rememberSaveable:
    // - recuerda el valor entre recomposiciones
    // - y además intenta conservarlo ante cambios de configuración (por ejemplo rotación)
    //
    // Este estado vive en el PADRE: eso permite que varios hijos compartan información.

    var selectedSound by rememberSaveable { mutableStateOf("Predeterminado del sistema") }
    var ignorarSilencio by rememberSaveable { mutableStateOf(false) }
    var largo by rememberSaveable { mutableStateOf(false) }

    // Esta variable decide si el botón "Guardar Cambios" está habilitado.
    // La activamos cuando el usuario cambia algún ajuste.
    var botonGuardar by rememberSaveable { mutableStateOf(false) }

    // ─────────────────────────────────────────────
    // ESTRUCTURA VISUAL DE LA PANTALLA
    // ─────────────────────────────────────────────
    //
    // Column: apila elementos verticalmente (de arriba a abajo).
    // background: color de fondo oscuro, como en la captura.
    // padding: márgenes internos.
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(horizontal = 16.dp)
            .padding(top = 40.dp)
    ) {

        // 1) Barra superior
        TopBar(label = "Sonidos de notificación")

        Spacer(Modifier.height(12.dp))

        // 2) Texto informativo con icono
        InfoText("Los siguientes ajustes son relevantes para todos los sitios en este dispositivo",)

        Spacer(Modifier.height(12.dp))

        // 3) Sección de radios (sonidos básicos)
        //
        // STATE HOISTING:
        // - Le pasamos el estado actual (selectedSound)
        // - Le pasamos una función para cambiarlo (onSelectedChange)
        BasicSoundsSection(
            selected = selectedSound,
            onSelectedChange = { nuevoValor ->
                // El hijo (BasicSoundsSection) no cambia el estado directamente.
                // Solo "pide" el cambio.
                selectedSound = nuevoValor

                // Si hay cambios, habilitamos el botón de guardar
                botonGuardar = true
            }
        )

        Spacer(Modifier.height(24.dp))

        // 4) Sección de switches
        //
        // Igual que antes:
        // - Se pasa estado y callbacks.
        EnhancedSoundSection(
            ignorarSilencio = ignorarSilencio,
            onIgnorarChange = { nuevoValor ->
                ignorarSilencio = nuevoValor
                botonGuardar = true
            },
            largo = largo,
            onLargoChange = { nuevoValor ->
                largo = nuevoValor
                botonGuardar = true
            }
        )

        Spacer(Modifier.height(24.dp))

        // 5) Botón final (habilitado solo si hay cambios)
        SaveButton(botonGuardar, text = "Guardar Cambios")
    }
}

/* ---------------- TopBar ----------------
   Barra superior: flecha de volver + título.
   Solo dibuja UI, no tiene estado.
*/
@Composable
fun TopBar(label:String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        // IconButton: botón con icono (en este ejercicio no implementamos navegación)
        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Outlined.ArrowBackIosNew,
                contentDescription = "Volver",
                tint = Color.White
            )
        }

        Spacer(Modifier.width(8.dp))

        Text(
            text = label,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

/* ---------------- Texto informativo ----------------
   Bloque informativo inicial: icono + texto.
   Es un componente independiente.
*/
@Composable
fun InfoText(info:String) {
    Column {
        Row(verticalAlignment = Alignment.Top) {

            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(18.dp)
            )

            Spacer(Modifier.width(8.dp))

            Text(
                text = info,
                color = Color.Gray,
                fontSize = 16.sp
            )
        }
    }
}

/* ---------------- Selección de sonidos (RadioButtons) ----------------
   Esta sección recibe el estado desde fuera:
   - selected: cuál está marcado
   - onSelectedChange: qué hacer si el usuario cambia la selección

   Por tanto, este composable NO crea estado propio.
*/
@Composable
fun BasicSoundsSection(
    selected: String,
    onSelectedChange: (String) -> Unit
) {
    // Lista de opciones (datos). Este patrón es muy útil:
    // - si mañana añadimos otra opción, solo añadimos un texto aquí.
    val options = listOf(
        "Predeterminado del sistema",
        "Bip",
        "Luz",
        "Rápido",
        "Llamada"
    )

    Column {
        Spacer(Modifier.height(12.dp))

        Text(
            text = "SONIDOS BÁSICOS",
            color = Color.LightGray,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(Modifier.height(12.dp))

        // Recorremos la lista y dibujamos un SoundOptionItem por opción.
        // Esto evita repetir código manualmente.
        options.forEach { option ->
            SoundOptionItem(
                text = option,
                selected = (selected == option),
                onClick = {
                    // Avisamos al padre: "esta es la opción que se ha pulsado"
                    onSelectedChange(option)
                }
            )
        }
    }
}

/* ---------------- Item individual de sonido ----------------
   Un "item" reutilizable: RadioButton + texto.
   Es importante que sea pequeño y reutilizable.
*/
@Composable
fun SoundOptionItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()

            // clickable en toda la fila:
            // permite pulsar en el texto también, no solo en el circulito.
            .clickable { onClick() }
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.White,
                unselectedColor = Color.Gray
            )
        )

        Spacer(Modifier.width(12.dp))

        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}

/* ---------------- Sección de switches ----------------
   Otro ejemplo claro de state hoisting:
   - Recibe ignorarSilencio y largo desde el padre
   - Recibe callbacks para cambiarlos
*/
@Composable
fun EnhancedSoundSection(
    ignorarSilencio: Boolean,
    onIgnorarChange: (Boolean) -> Unit,
    largo: Boolean,
    onLargoChange: (Boolean) -> Unit
) {
    Column {

        Text(
            text = "Sonido aumentado",
            color = Color.White,
            fontSize = 16.sp
        )

        Spacer(Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.Top) {

            Spacer(Modifier.width(8.dp))

            InfoText("Aplicable únicamente para alarmas de Seguridad / Emergencia")
        }

        Spacer(Modifier.height(16.dp))

        // Tarjeta 1: Ignorar silencio
        SwitchCard(
            title = "Ignorar Silencio",
            checked = ignorarSilencio,
            onCheckedChange = onIgnorarChange
        )

        Spacer(Modifier.height(16.dp))

        // Tarjeta 2: Largo
        SwitchCard(
            title = "Largo (30 seg.)",
            checked = largo,
            onCheckedChange = onLargoChange
        )
    }
}

/* ---------------- Tarjeta con Switch ----------------
   Composable reutilizable:
   - muestra un título
   - muestra un switch
   - recibe estado y callback
*/
@Composable
fun SwitchCard(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(CardBackground)
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // weight(1f): el texto ocupa todo el espacio posible,
        // dejando el Switch pegado a la derecha.
        Text(
            text = title,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,

            // Colores para acercarnos al estilo de la captura
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color.Green,
                uncheckedThumbColor = Color.LightGray,
                uncheckedTrackColor = Color.DarkGray
            )
        )
    }
}

/* ---------------- Botón Guardar ----------------
   - enabled depende del estado botonGuardar.
   - si está deshabilitado, se ve gris (como en la captura).
*/
@Composable
fun SaveButton(estado: Boolean, text: String) {

    Button(
        onClick = { },
        enabled = estado,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            disabledContainerColor = Color.Gray
        )
    ) {
        Text(
            text = text,
            color = Color.DarkGray,
            fontSize = 16.sp
        )
    }
}