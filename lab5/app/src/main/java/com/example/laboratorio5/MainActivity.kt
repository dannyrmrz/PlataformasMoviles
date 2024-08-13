package com.example.laboratorio5

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
import com.example.laboratorio5.ui.theme.Laboratorio5Theme


import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MyComposeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    MaterialTheme {
        MyScreenContent()
    }
}

@Composable
fun MyScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        HeaderSection()
        Spacer(modifier = Modifier.height(16.dp))
        RestaurantInfoCard()
    }
}

@Composable
fun HeaderSection() {
    Column {
        Text(
            text = "Martes",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "17 de septiembre",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun RestaurantInfoCard() {
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Moonrise",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Casa Vida, Via 6 3-30",
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Horario: 12:00 PM - 9:00 PM",
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    Toast.makeText(context, "Daniela Ramírez de León", Toast.LENGTH_LONG).show()
                }
            ) {
                Text("Iniciar")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/Moonrise+Comida+Vegana/@14.6195449,-90.5167419,18z/data=!4m7!3m6!1s0x8589a3894aaddddb:0x78841e86052531c5!4b1!8m2!3d14.6196182!4d-90.5144417!16s%2Fg%2F11fmc8hwmv?hl=en&entry=ttu"))
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text("Descargar")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse("https://www.google.com/maps/search/?api=1&query=Moonrise&query_place_id=ChIJd8BlQ2BZwokRAFUEcm_qrcA")
                    }
                    context.startActivity(intent)
                }
            ) {
                Text("Directions")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    Toast.makeText(context, "Comida Vegana\nQQQ", Toast.LENGTH_LONG).show()
                }
            ) {
                Text("Detalles")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}
