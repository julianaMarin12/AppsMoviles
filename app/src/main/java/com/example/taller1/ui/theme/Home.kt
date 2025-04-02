package com.example.taller1.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Verified
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                elevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                        }
                        Text(
                            text = "Inicio",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black
                        )
                    }
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                        shape = MaterialTheme.shapes.medium,
                        elevation = ButtonDefaults.elevation(4.dp)
                    ) {
                        Text("AYUDA", color = Color.White)
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(
            contentPadding = PaddingValues(top = 24.dp, bottom = 24.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(10) { index ->
                PostItem(index = index)
            }
        }
    }
}

@Composable
fun PostItem(index: Int) {
    Card(
        elevation = 6.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Título ${index + 1}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = "Ubicación",
                            tint = Color.Red,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Ubicación ${index + 1}", color = Color.Red)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Categoría ${index + 1}", fontSize = 12.sp, color = Color.Gray)
                }
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Opciones",
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color(0xFFE0E0E0)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Image,
                    contentDescription = "Imagen",
                    modifier = Modifier.size(64.dp),
                    tint = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                        "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                        "Ut enim ad minim veniam (Reporte #${index + 1}).",
                fontSize = 14.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Justify
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Verified,
                    contentDescription = "Verificado",
                    tint = Color(0xFF4CAF50),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("000-${index + 1}", color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
