package com.example.composecatalogclass.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
@Composable
fun MainLayout() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // 1) CABECERA (80.dp)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color.Cyan),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "CABECERA")
        }

        // 2) ZONA CENTRAL (ocupa el espacio restante)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // ocupa el resto de la pantalla
        ) {
            // Bloque IZQUIERDA
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
                    .background(Color.Red),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "IZQUIERDA")
            }

            // Bloque DERECHA
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
                    .background(Color.Green),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "DERECHA")
            }
        }

        // 3) SEPARACIÓN (Spacer 20.dp)
        Spacer(modifier = Modifier.height(20.dp))

        // 4) PIE (60.dp)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "PIE DE PANTALLA")
        }
    }
}


@Composable
fun MainLayoutPreview() {
    MainLayout()
}

@Composable
fun ConstraintMainLayout() {
    ConstraintLayout(modifier = Modifier.background(Color.White)) {
        // Referencias
        val (topBox, centerBox, leftBox, rightBox, bottomBox) = createRefs()

        // Caja superior (TOP)
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(Color.Cyan)
                .constrainAs(topBox) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "TOP")
        }

        // Caja central (CENTER)
        Box(
            modifier = Modifier
                .size(140.dp)
                .background(Color.Yellow)
                .constrainAs(centerBox) {
                    top.linkTo(topBox.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "CENTER")
        }

        // Caja izquierda (LEFT)
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Red)
                .constrainAs(leftBox) {
                    end.linkTo(centerBox.start, margin = 16.dp)
                    top.linkTo(centerBox.top)
                    bottom.linkTo(centerBox.bottom)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "LEFT")
        }

        // Caja derecha (RIGHT)
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Green)
                .constrainAs(rightBox) {
                    start.linkTo(centerBox.end, margin = 16.dp)
                    top.linkTo(centerBox.top)
                    bottom.linkTo(centerBox.bottom)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "RIGHT")
        }

        // Caja inferior (BOTTOM)
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(Color.LightGray)
                .constrainAs(bottomBox) {
                    top.linkTo(centerBox.bottom, margin = 20.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "BOTTOM")
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun ConstraintMainLayoutPreview() {
    ConstraintMainLayout()
}