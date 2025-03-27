package com.example.taller1.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.example.taller1.ui.theme.Destinations.*

// Clase SideMenu
class SideMenu {

    val navigationItems = listOf(
        Screen1,
        Screen2,
        Screen3,
        Screen4,
        Screen5,
        Screen6
    )

    // Composable para el Drawer (Menú lateral)
    @Composable
    fun Drawer() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = "Icono de usuario",
                tint = Color.DarkGray,
                modifier = Modifier.size(150.dp)
            )
            Text(
                text = "Nombre usuario",
                color = Color.DarkGray,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "Correo usuario",
                color = Color.DarkGray,
                modifier = Modifier.padding(8.dp)
            )
        }

        // Ítems de navegación
        Column {
            navigationItems.forEach { item ->
                DrawerItem(item = item)
            }
        }
    }

    // Composable para un ítem individual en el Drawer
    @Composable
    fun DrawerItem(item: Destinations) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(6.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                tint = Color.LightGray,
                modifier = Modifier.size(30.dp)
            )
            Text(
                text = item.title,
                modifier = Modifier.padding(8.dp),
                color = Color.Black
            )
        }
    }

    // Composable para la barra superior
    @Composable
    fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState, title: String) {
        TopAppBar(
            title = { Text(title) },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open() // Abre el menú lateral
                    }
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Menu,
                        contentDescription = "Menú desplegable",
                        tint = Color.Red,
                        modifier = Modifier.size(40.dp)
                    )
                }
            },
            backgroundColor = Color.White,
            contentColor = Color.Black
        )
    }

    // Pantalla principal que integra el SideMenu
    @Composable
    fun SideMenuScreen(title: String) {
        val scaffoldState = rememberScaffoldState() // Inicializamos el ScaffoldState
        val scope = rememberCoroutineScope() // Inicializamos el CoroutineScope

        // Scaffold que maneja el menú lateral y la barra superior
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBar(scope = scope, scaffoldState = scaffoldState, title = title)
            },
            drawerContent = {
                Drawer() // Contenido del Drawer
            }
        ) {
            // Contenido principal de la pantalla (puedes agregar aquí el contenido principal)
            Text("Contenido principal", modifier = Modifier.padding(it))
        }
    }
}
