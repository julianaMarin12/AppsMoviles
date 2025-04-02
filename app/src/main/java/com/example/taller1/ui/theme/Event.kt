package com.example.taller1.ui.theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign

@Composable
fun EventScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Eventos",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                backgroundColor = Color.White,
                elevation = 4.dp
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            item {
                Column(modifier = Modifier.padding(vertical = 8.dp)) {


                }
            }


            item {
                CalendarView()
                Spacer(modifier = Modifier.height(16.dp))
                Divider(color = Color.LightGray)
            }

            item {
                Text(
                    text = "Eventos programados",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                EventList()
            }
        }
    }
}

@Composable
fun CalendarView() {
    val days = listOf("Su", "Mo", "Tu", "We", "Th", "Fr", "Sa")
    val selectedDay = 26
    val headerColor = Color(0xFFFFA726)
    val selectedColor = Color(0xFFFF5E5E)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(headerColor, shape = MaterialTheme.shapes.medium)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Prev",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "February 26, 2025",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Next",
                tint = Color.Transparent,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            days.forEach { day ->
                Text(
                    text = day,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        val weeks = listOf(
            listOf(2, 3, 4, 5, 6, 7, 8),
            listOf(9, 10, 11, 12, 13, 14, 15),
            listOf(16, 17, 18, 19, 20, 21, 22),
            listOf(23, 24, 25, 26, 27, 28, null)
        )

        weeks.forEach { week ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                week.forEach { day ->
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(
                                color = if (day == selectedDay) selectedColor else Color.Transparent,
                                shape = MaterialTheme.shapes.small
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (day != null) {
                            Text(
                                text = day.toString(),
                                color = if (day == selectedDay) Color.White else Color.Black,
                                fontWeight = if (day == selectedDay) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun EventList() {
    val events = listOf(
        "EVENTO 1 (SST)",
        "EVENTO 2 (ROBOS)",
        "EVENTO 3 (PREVENCIÓN)"
    )

    Column {
        events.forEach { event ->
            Card(
                backgroundColor = Color(0xFFFF5E5E),
                elevation = 6.dp,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Cancelar",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = event,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                    }

                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White,
                            contentColor = Color(0xFFFF5E5E)
                        ),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                        modifier = Modifier.height(30.dp)
                    ) {
                        Text(
                            text = "ASISTIRÉ",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun EventScreenPreview() {
    EventScreen()
}