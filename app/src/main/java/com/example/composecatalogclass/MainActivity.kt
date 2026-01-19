package com.example.composecatalogclass

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composecatalogclass.ui.theme.ComposeCatalogClassTheme
import com.example.composecatalogclass.uicomponents.ConstraintAdvanced
import com.example.composecatalogclass.uicomponents.ExerciseLayout
import com.example.composecatalogclass.uicomponents.MyBasicConstraintLayout
import com.example.composecatalogclass.uicomponents.MyBox
import com.example.composecatalogclass.uicomponents.MyColumn
import com.example.composecatalogclass.uicomponents.MyComplexLayout
import com.example.composecatalogclass.uicomponents.MyRow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCatalogClassTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //MyBox()
                    //MyColumn(Modifier.padding(innerPadding))
                    //MyRow(Modifier.padding(innerPadding))
                    //MyComplexLayout(Modifier.padding(innerPadding))
                    //ExerciseLayout(Modifier.padding(innerPadding))
                    //MyBasicConstraintLayout(Modifier.padding(innerPadding))
                    ConstraintAdvanced(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

