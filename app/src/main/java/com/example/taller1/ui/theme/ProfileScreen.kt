package com.example.taller1.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen() {
    var name by remember { mutableStateOf("Nombre Usuario") }
    var direccion by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val scaffoldState = rememberScaffoldState() // Recuerda el estado del Scaffold
    val scope = rememberCoroutineScope() // Recuerda el scope de la coroutine para abrir/cerrar el drawer
    val sideMenu = SideMenu()

    val title = "Mi Perfil"

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            sideMenu.TopBar(scope, scaffoldState, title) // Barra superior
        },
        drawerContent = {
            sideMenu.Drawer() // Contenido del menú lateral (Drawer)
        }
    ) { contentPadding ->
        // Contenido principal de la pantalla
        Column(
            modifier = Modifier
                .padding(contentPadding) // Aplica el padding del Scaffold
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Icono y nombre de usuario centrados
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp), // Espacio superior
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.AccountCircle,
                    contentDescription = "Icono de usuario",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(150.dp)
                )
                Text(
                        text = name,
                color = Color.Black,
                fontSize = 24.sp, // Aumenta el tamaño del texto
                fontWeight = FontWeight.Bold, // Texto en negrita
                modifier = Modifier.padding(8.dp) // Padding alrededor del texto
                )
            }
            // Campos de texto
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Dirección de correo") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp) // Padding alrededor del campo de texto
            )
            OutlinedTextField(
                value = direccion,
                onValueChange = { direccion = it },
                label = { Text("Dirección") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp) // Padding alrededor del campo de texto
            )
            Text(
                text = "En la app encontraras un botón de ayuda el cual alertara a las autoridades, para cuando te sientas inseguro o necesites una comunicación rápida con ellos.",
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 16.dp) // Más espacio en los lados y arriba/abajo
            )
            Row {
                androidx.compose.material3.Button(
                    onClick = { /* Acción de editar */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                    modifier = Modifier
                        .padding(start = 20.dp) // Espacio entre los botones
                        .size(width = 120.dp, height = 50.dp)

                ) {
                    androidx.compose.material3.Text("Editar", color = Color.White)
                }
                androidx.compose.material3.Button(
                    onClick = { /* Acción de eliminar */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier
                        .padding(start = 20.dp) // Espacio entre los botones
                        .size(width = 120.dp, height = 50.dp)
                ) {
                    androidx.compose.material3.Text("Eliminar", color = Color.White)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}
