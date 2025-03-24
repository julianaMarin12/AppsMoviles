package com.example.taller1.ui.theme

import androidx.compose.material.ScaffoldState
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

class SideMenu {

    @Composable
    fun Drawer() {
        val menu_items = listOf(
            "Inicio",
            "Perfil",
            "Reportes",
            "Eventos",
            "Configuración"
        )
        Column {
            menu_items.forEach { item ->
                TextButton(onClick = { /* Handle navigation */ }) {
                    Text(
                        item,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }

    @Composable
    fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState, title: String) {
        TopAppBar(
            title = { Text(title) },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open() // Abrimos el drawer
                    }
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Menu,
                        contentDescription = "Menu desplegable",
                        tint = Color.Red,
                        modifier = Modifier.size(40.dp)
                    )
                }
            },
            backgroundColor = Color.White,
            contentColor = Color.Black
        )
    }
}

