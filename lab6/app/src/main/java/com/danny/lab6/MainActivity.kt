package com.danny.lab6

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterApp()
        }
    }
}

@Composable
fun CounterApp() {
    var counter by remember { mutableStateOf(0) }
    var totalIncrements by remember { mutableStateOf(0) }
    var totalDecrements by remember { mutableStateOf(0) }
    var maxValue by remember { mutableStateOf(Int.MIN_VALUE) }
    var minValue by remember { mutableStateOf(Int.MAX_VALUE) }
    var totalChanges by remember { mutableStateOf(0) }
    val history = remember { mutableStateListOf<Pair<Int, Boolean>>() }

    val context = LocalContext.current

    fun reset() {
        counter = 0
        totalIncrements = 0
        totalDecrements = 0
        maxValue = Int.MIN_VALUE
        minValue = Int.MAX_VALUE
        totalChanges = 0
        history.clear()
    }

    fun updateStats(isIncrement: Boolean) {
        if (isIncrement) {
            totalIncrements++
        } else {
            totalDecrements++
        }
        totalChanges++
        maxValue = maxOf(maxValue, counter)
        minValue = minOf(minValue, counter)
        history.add(Pair(counter, isIncrement))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título
        Text(
            text = "Daniela Ramírez de León",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        // Contador
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                counter--
                updateStats(isIncrement = false)
            }) {
                Text(text = "−", fontSize = 24.sp)
            }
            Spacer(modifier = Modifier.width(24.dp))
            Text(text = "$counter", fontSize = 36.sp)
            Spacer(modifier = Modifier.width(24.dp))
            Button(onClick = {
                counter++
                updateStats(isIncrement = true)
            }) {
                Text(text = "+", fontSize = 24.sp)
            }
        }

        // Estadísticas
        Text(text = "Total incrementos: $totalIncrements")
        Text(text = "Total decrementos: $totalDecrements")
        Text(text = "Valor máximo: $maxValue")
        Text(text = "Valor mínimo: $minValue")
        Text(text = "Total cambios: $totalChanges")

        // Historial
        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            items(history.size) { index ->
                val (value, isIncrement) = history[index]
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .background(if (isIncrement) Color.Green else Color.Red)
                        .fillMaxWidth()
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = value.toString(),
                        color = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón de Reiniciar
        Button(onClick = {
            reset()
            Toast.makeText(
                context,
                "Se ha reiniciado el contador",
                Toast.LENGTH_SHORT
            ).show()
        }) {
            Text(text = "Reiniciar")
        }
    }
}