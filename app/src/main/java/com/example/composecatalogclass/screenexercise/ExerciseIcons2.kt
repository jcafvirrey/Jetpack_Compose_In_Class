package com.example.composecatalogclass.screenexercise

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/* ---------------- COLORS ----------------
   Colores de la interfaz. En Compose es habitual tenerlos como constantes para reutilizarlos.
*/

private val DarkBackground = Color(0xFF0E0E0E)
private val CardBackground = Color(0xFF1C1C1E)
private val Green = Color(0xFF2ECC71)
private val Red = Color(0xFFE74C3C)
private val Orange = Color(0xFFF39C12)


@Composable

fun SecurityScreen(modifier: Modifier) {
    // ─────────────────────────────────────────────
// 1) ESTADO “ÚNICO” EN EL PADRE (STATE HOISTING)
// ─────────────────────────────────────────────
//
// IDEA CLAVE:
// - Si varios composables deben reaccionar al mismo estado, ese estado debe vivir "arriba" (en el padre).
// - Los hijos NO guardan el estado, solo lo reciben y lo muestran.
//
// Esto es precisamente "State Hoisting": subir el estado al nivel superior más adecuado.

// Estado del botón general seleccionado: "Parcial", "Desarmar", "Armar" o "Ninguna"
// - remember: recuerda el valor aunque haya recomposición.
// - mutableStateOf: crea un estado observable por Compose.
// - var + by: sintaxis cómoda para leer/escribir el estado.
    var selectedAction by remember { mutableStateOf("Ninguna") }

    // Estado de cada cochera (0=Parcial, 1=Desarmado, 2=Armado)
    // Estos valores serán consumidos por las CocheraCard para decidir qué icono debe tener borde.
    var modoP1 by remember { mutableStateOf(2) } // por defecto Armado
    var modoP2 by remember { mutableStateOf(2) } // por defecto Armado

    // La pantalla se construye con una Column principal (layout vertical).
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(16.dp)
            .padding(top = 30.dp)
    ) {
        Header()
        Spacer(Modifier.height(16.dp))
        // Tabs tiene su propio estado interno (selectedTab) porque solo afecta a Tabs.
        // No influye en otros composables (por eso no lo subimos al padre).
        Tabs()
        Spacer(Modifier.height(24.dp))
        // ─────────────────────────────────────────────
        // 2) BOTONES GENERALES (MainActions) - CONTROLADOS POR EL PADRE
        // ─────────────────────────────────────────────
        //
        // MainActions NO tiene estado interno.
        // Recibe:
        // - selectedAction: el estado actual
        // - onSelectAction: una función callback para "pedir" cambios al padre
        //
        // Este patrón es el correcto:
        // Padre = dueño del estado
        // Hijo  = pinta según estado + lanza eventos
        MainActions(
            selectedAction = selectedAction,
            onSelectAction = { action ->
                // 2.1) Actualizamos el estado del botón general seleccionado
                // Esto provoca recomposición en cualquier composable que dependa de selectedAction
                selectedAction = action
                // 2.2) IMPORTANTE: el botón general afecta a TODAS las cocheras
                // Aquí aplicamos el cambio global:
                // - Si pulso "Desarmar" => modoP1 y modoP2 pasan a 1
                // - Si pulso "Armar"    => modoP1 y modoP2 pasan a 2
                // - Si pulso "Parcial"  => modoP1 y modoP2 pasan a 0
                //
                // Esto provoca recomposición de CocheraCard, porque ellas reciben selectedMode desde modoP1/modoP2.
                when (action) {
                    "Parcial" -> {
                        modoP1 = 0
                        modoP2 = 0
                    }
                    "Desarmar" -> {
                        modoP1 = 1
                        modoP2 = 1
                    }
                    "Armar" -> {
                        modoP1 = 2
                        modoP2 = 2
                    }
                }
            }
        )
        Spacer(Modifier.height(24.dp))
        // ─────────────────────────────────────────────
        // 3) COCHERAS (CocheraCard) - TAMBIÉN CONTROLADAS POR EL PADRE
        // ─────────────────────────────────────────────
        //
        // Cada CocheraCard recibe:
        // - selectedMode (modo actual)
        // - onSelectMode (callback para cambiar SOLO esa cochera)
        //
        // Gracias a esto, el alumnado ve dos comportamientos:
        // A) Cambio global desde MainActions (afecta a todas)
        // B) Cambio individual desde la propia CocheraCard (solo afecta a una)
        CocheraCard(
            title = "Cochera P1",
            selectedMode = modoP1,
            onSelectMode = { nuevoModo ->
                // Cambio INDIVIDUAL: solo actualiza la cochera P1
                modoP1 = nuevoModo }
        )
        Spacer(Modifier.height(12.dp))
        CocheraCard(
            title = "Cochera P2",
            selectedMode = modoP2,
            onSelectMode = { nuevoModo ->
                // Cambio INDIVIDUAL: solo actualiza la cochera P2
                modoP2 = nuevoModo }
        )
    }
    }
/* ---------------- HEADER ---------------- */

@Composable
fun Header() {
    // Row para colocar texto a la izquierda e icono a la derecha
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Texto del usuario
        Text(
            text = "Juan Carlos Alumbreros",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        ) // Icono candado
        Icon(
            imageVector = Icons.Outlined.Lock,
            contentDescription = "Estado de seguridad",
            tint = Color.White
        )
    }
}

/* ---------------- TABS ---------------- */
@Composable
fun Tabs() {
    // Estado local: solo afecta a Tabs, por eso puede vivir aquí.
    // Si más adelante el contenido de la pantalla dependiera de la pestaña,
    // entonces sí tendría sentido subirlo al padre.
    var selectedTab by remember { mutableStateOf("Sistema") }
    Row {
        TabItem(
            text = "Sistema",
            selected = (selectedTab == "Sistema"),
            onClick = { selectedTab = "Sistema" })
        Spacer(Modifier.width(8.dp))
        TabItem(
            text = "Detectores",
            selected = (selectedTab == "Detectores"),
            onClick = { selectedTab = "Detectores" })
        Spacer(Modifier.width(8.dp))
        TabItem(
            text = "Salidas",
            selected = (selectedTab == "Salidas"),
            onClick = { selectedTab = "Salidas" })
    }
}

@Composable
fun TabItem(text: String, selected: Boolean, onClick: () -> Unit) {
    // La apariencia visual depende del estado (selected).
    // Esto es UI declarativa: "si está seleccionado, se pinta diferente".
    val background = if (selected) CardBackground else Color.Transparent
// (Opcional pero didáctico) Un borde suave para remarcar la selección.
    val borderColor = if (selected) Color.White else Color.Transparent
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(background)
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(20.dp))
            .clickable { onClick() } // evento: el hijo notifica, y Tabs cambia su estado
            .padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
        )
    }
} /* ---------------- MAIN ACTIONS ---------------- */

@Composable
fun MainActions(
    selectedAction: String,
    onSelectAction: (String) -> Unit
) {
    // MainActions NO guarda el estado.
    // Solo recibe el estado seleccionado (selectedAction)
    // y ofrece callbacks para que el padre decida qué hacer....
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ActionButton(
            text = "Armado\nParcial",
            color = Orange,
            selected = (selectedAction == "Parcial"),
            onClick = { onSelectAction("Parcial") }
        )
        ActionButton(
            text = "Desarmar",
            color = Green,
            selected = (selectedAction == "Desarmar"),
            onClick = { onSelectAction("Desarmar") }
        )
        ActionButton(
            text = "Armar",
            color = Red,
            selected = (selectedAction == "Armar"),
            onClick = { onSelectAction("Armar") }
        )
    }
}

@Composable
fun ActionButton(text: String, color: Color, selected: Boolean, onClick: () -> Unit) {
    // El borde depende del estado selected:
    // - Si está seleccionado, borde del color del botón
    // - Si no, borde invisible (transparente)
    val borderColor = if (selected) color else Color.Transparent
    Column(
        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .border(2.dp, borderColor, CircleShape)
                .clickable { onClick() }, // el padre decide qué pasa al pulsar
            contentAlignment = Alignment.Center
        )
        {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(text = text, color = Color.White, fontSize = 12.sp, textAlign = TextAlign.Center)
    }
}

/* ---------------- COCHERA CARD ---------------- */
@Composable
fun CocheraCard(
    title: String,
    selectedMode: Int,
    onSelectMode: (Int) -> Unit
) {
    // selectedMode viene del padre (modoP1 / modoP2).
    // Esto permite que:
    // - Un cambio global afecte a todas las cocheras
    // - Y un cambio individual afecte solo a una (según el callback que le pasemos)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(CardBackground)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 15.sp,
            modifier = Modifier.weight(1f)
        )

        // Icono 0: Parcial (Orange)
        SmallIcon(
            color = Orange,
            selected = (selectedMode == 0),
            onClick = { onSelectMode(0) }
        )

        Spacer(Modifier.width(10.dp))

        // Icono 1: Desarmado (Green)
        SmallIcon(
            color = Green,
            selected = (selectedMode == 1),
            onClick = { onSelectMode(1) }
        )

        Spacer(Modifier.width(10.dp))

        // Icono 2: Armado (Red)
        SmallIcon(
            color = Red,
            selected = (selectedMode == 2),
            onClick = { onSelectMode(2) }
        )
    }
}

@Composable
fun SmallIcon(
    color: Color,
    selected: Boolean,
    onClick: () -> Unit
) {
    // Aquí está el “truco” visual:
    // el borde solo aparece cuando selected == true.
    val borderColor = if (selected) color else Color.Transparent

    Box(
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .border(1.5.dp, borderColor, CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Lock,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(18.dp)
        )
    }
}
