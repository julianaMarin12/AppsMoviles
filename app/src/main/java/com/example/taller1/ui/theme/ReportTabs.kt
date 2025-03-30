package com.example.taller1.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ReportTabs() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val sideMenu = SideMenu()
    val title = "Reportes"

    var selectedTabIndex by remember {
        mutableIntStateOf(0) }

    val tabs = listOf("Mis reportes", "Crear reporte")

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            sideMenu.TopBar(scope, scaffoldState, title)
        },
        drawerContent = { sideMenu.Drawer() }
    ){ contentPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(16.dp)
        ) {
            // Crear las pestañas
            TabRow(
                selectedTabIndex = selectedTabIndex,
                backgroundColor = Color.White,
                contentColor = Color.Black
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }

            // Mostrar el contenido de cada pestaña
            when (selectedTabIndex) {
                0 -> ProfileScreen()  // Aquí pones tu pantalla de "Mis reportes"
                1 -> CreateReportScreenPreview()  // Aquí pones tu pantalla de "Crear reporte"
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReportTabsPreview() {
    ReportTabs()
}