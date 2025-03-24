package com.example.taller1.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.material.ScaffoldState
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch
import com.example.taller1.ui.theme.Destinations.*

class SideMenu {

    val navigatonItems = listOf(
        Screen1,
        Screen2,
        Screen3,
        Screen4,
        Screen5,
        Screen6
    )

    @Composable
    fun Drawer() {
/*        val menu_items = listOf(
            "Inicio",
            "Perfil",
            "Reportes",
            "Eventos",
            "Configuración"
        )*/
        Column {
            navigatonItems.forEach {item ->
                DrawerItem(item = item)
            }
        }

    }

    @Composable
    fun DrawerItem(item: Destinations) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(6.dp)
                .clip(RoundedCornerShape(12.dp))
        ){
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

