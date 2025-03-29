package com.example.taller1.ui.theme

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.material.icons.rounded.AddPhotoAlternate
import coil.compose.rememberAsyncImagePainter

@Composable
fun CreateReportScreen() {
    var reportTitle by remember { mutableStateOf("") }
    var reportDescription by remember { mutableStateOf("") }
    var isImportant by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val sideMenu = SideMenu()
    val title = "Reportes"

    // Estado para almacenar una única imagen
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var capturedImageBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    // Lanzadores de actividad para abrir la galería y la cámara
    val pickImageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it // Guarda la URI seleccionada
        }
    }

    val takePhotoLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        bitmap?.let {
            capturedImageBitmap = it // Guarda el bitmap capturado
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título del reporte
        TextField(
            value = reportTitle,
            onValueChange = { reportTitle = it },
            label = { Text("Título del reporte") },
            modifier = Modifier.fillMaxWidth()
        )

        // Categoría (Spinner simulado)
        var selectedCategory by remember { mutableStateOf("Seleccionar") }
        var expanded by remember { mutableStateOf(false) }

        Box(
            Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
                .background(Color.LightGray)
                .padding(16.dp)
        ) {
            Text(selectedCategory)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = { selectedCategory = "Categoría 1"; expanded = false }) {
                Text("Categoría 1")
            }
            DropdownMenuItem(onClick = { selectedCategory = "Categoría 2"; expanded = false }) {
                Text("Categoría 2")
            }
        }

        // Descripción
        TextField(
            value = reportDescription,
            onValueChange = { reportDescription = it },
            label = { Text("Descripción del incidente") },
            modifier = Modifier.fillMaxWidth()
        )

        // Ubicación (con icono de marcador)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.LocationOn,
                contentDescription = "Ubicación"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Latitud y longitud")
        }

        // Imagen incidente (espacio para imagen)
        if (showDialog) {
            ImagePickerDialog(
                onDismiss = { showDialog = false },
                onPickGallery = { pickImageLauncher.launch("image/*") }, // Abre la galería
                onTakePhoto = { takePhotoLauncher.launch() } // Toma una foto con la cámara
            )
        }

        // Imagen seleccionada o capturada con la opción de cambiar
        ImagePickerBox(
            selectedImageUri = selectedImageUri,
            capturedImageBitmap = capturedImageBitmap,
            onImageClick = { showDialog = true } // Abrir el diálogo para cambiar la imagen
        )

        // Importante Checkbox
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                colors = CheckboxDefaults.colors(checkedColor = Color.Red),
                checked = isImportant,
                onCheckedChange = { isImportant = it }
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text("Es importante")
        }

        // Botón Reportar
        androidx.compose.material3.Button(
            onClick = { /* Acción de reportar */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            androidx.compose.material3.Text("Reportar", color = Color.White)
        }
    }

}

@Composable
fun ImagePickerDialog(
    onDismiss: () -> Unit,
    onPickGallery: () -> Unit,
    onTakePhoto: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Seleccionar Imagen") },
        text = { Text(text = "Elige si deseas cargar una imagen desde la galería o tomar una foto.") },
        confirmButton = {
            TextButton(onClick = {
                onPickGallery()
                onDismiss()
            }) {
                Text("Galería")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onTakePhoto()
                onDismiss()
            }) {
                Text("Cámara")
            }
        }
    )
}

@Composable
fun ImagePickerBox(
    selectedImageUri: Uri?,
    capturedImageBitmap: Bitmap?,
    onImageClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp) // Ajusta el alto para darle más espacio a la imagen
            .background(Color.LightGray)
            .clickable {
                onImageClick()
            }
    ) {
        if (selectedImageUri != null) {
            // Mostrar la imagen seleccionada desde la galería
            Image(
                painter = rememberAsyncImagePainter(selectedImageUri),
                contentDescription = "Imagen seleccionada",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Inside // La imagen se ajusta sin sobrepasar los límites del contenedor
            )
        } else if (capturedImageBitmap != null) {
            // Mostrar la imagen capturada por la cámara
            Image(
                painter = rememberAsyncImagePainter(selectedImageUri),
                contentDescription = "Imagen seleccionada",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Inside // La imagen se ajusta sin sobrepasar los límites del contenedor
            )

        } else {
            // Mostrar el ícono para agregar una imagen
            Icon(
                imageVector = Icons.Rounded.AddPhotoAlternate,
                contentDescription = "Agregar imagen",
                modifier = Modifier.align(Alignment.Center)
                    .size(100.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateReportScreenPreview() {
    CreateReportScreen()
}