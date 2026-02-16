package com.example.composecatalogclass.imageandicons

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.composecatalogclass.R


@Composable
fun MyImage(modifier: Modifier) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.ic_compose),
            contentDescription = "Avatar image preview",
            modifier = Modifier
                .size(350.dp)
                .border(
                    width = 15.dp,
                    shape = RoundedCornerShape(50),
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.Red,
                            Color.Yellow
                        )
                    ))
                .clip(RoundedCornerShape(20)),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun MyNetworkImage(modifier:Modifier) {
    AsyncImage(
        model = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAREAAAC5CAMAAAA4cvuLAAABlVBMVEX////X7/5ChfQ93IQHMEIEFhk3v244cLL22gDx+f8ALT9HiPTz+P8AAADb8/9AgOYbQ1Xx2Uk4gPREifyFnKektb8rYJ47gvQELj0xffPc9f/t+P/4/P8ADwc5crbl9f/s8v4jS3vw1jMs2n30/fj//vo814A6z3qfvfl7pvfI2vxclPUAJzsWRW9Sj/W4z/v25o3y21O+8tTh+esd2Xdb4JT68MFrnfbT4fwkePOLsPgFHSQ7d8jM5/0AHjWRtfgAES2gxvrA1Pv68Lz9+OH04G379M/36p+I6bL04Xp45KSf7L/U9+OS6rax8MuA5qtU35DN8uiT5saw6d3E7e891Y8uosk6sHo/vLKpxNMAAC8YZFQ1w3o+yaBBluAgfl5VcYEMPkcmk2YQS0s4qWc8eVgAGDwjekd3jZAqjlQ/tbxmh3+/1+NBkeYwr2RDe2A4mJc4qYs4d7EACRg3tXw5iagNNCc5gq0cZkArjZgAJBpVon56krFLXWRfj8onNzyQteE9etPZ3+FmeIK/x8sAOG3f6sq6MxrzAAALfUlEQVR4nO2d+VvbRhqAbWOwQWY" +
                "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",
        contentDescription = "Image from Network",
        modifier = Modifier.size(250.dp),
        onError = {
            Log.i("Image","Ha ocurrido un error ${it.result.throwable.message}")
        }
    )
}
@Preview
@Composable
fun MyImagePreview() {
    MyImage(modifier = Modifier)
}