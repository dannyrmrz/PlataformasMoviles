 package com.danny.lab7

 import android.os.Bundle
 import androidx.activity.ComponentActivity
 import androidx.activity.compose.setContent
 import androidx.compose.foundation.background
 import androidx.compose.foundation.clickable
 import androidx.compose.foundation.layout.*
 import androidx.compose.foundation.lazy.LazyColumn
 import androidx.compose.foundation.lazy.items
 import androidx.compose.material.icons.Icons
 import androidx.compose.material.icons.filled.Notifications
 import androidx.compose.material3.Icon
 import androidx.compose.material3.MaterialTheme
 import androidx.compose.material3.Text
 import androidx.compose.runtime.*
 import androidx.compose.ui.Alignment
 import androidx.compose.ui.Modifier
 import androidx.compose.ui.graphics.Color
 import androidx.compose.ui.text.font.FontWeight
 import androidx.compose.ui.text.style.TextAlign
 import androidx.compose.ui.unit.dp
 import androidx.compose.ui.unit.sp
 import com.danny.lab7.Notification
 import com.danny.lab7.NotificationType
 import com.danny.lab7.generateFakeNotifications

 class MainActivity : ComponentActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContent {
             MaterialTheme {
                 NotificationScreen()
             }
         }
     }
 }

 @Composable
 fun NotificationScreen() {
     val notifications = remember { generateFakeNotifications() }
     var selectedFilter by remember { mutableStateOf<NotificationType?>(null) }

     Column {
         FilterRow(selectedFilter) { filter ->
             selectedFilter = if (selectedFilter == filter) null else filter
         }
         Spacer(modifier = Modifier.height(8.dp))
         NotificationList(notifications, selectedFilter)
     }
 }

 @Composable
 fun FilterRow(selectedFilter: NotificationType?, onFilterSelected: (NotificationType) -> Unit) {
     Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
         NotificationType.values().forEach { type ->
             FilterButton(
                 text = type.name.replace("_", " ").capitalize(),
                 isSelected = selectedFilter == type,
                 onClick = { onFilterSelected(type) }
             )
         }
     }
 }

 @Composable
 fun FilterButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
     val backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
     val contentColor = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface

     Box(
         modifier = Modifier
             .padding(horizontal = 8.dp)
         .background(backgroundColor, shape = MaterialTheme.shapes.small)
         .clickable { onClick() }
         .padding(8.dp),
     contentAlignment = Alignment.Center
     ) {
         Text(
             text = text,
             color = contentColor,
             fontWeight = FontWeight.Bold,
             fontSize = 16.sp
         )
     }
 }

 @Composable
 fun NotificationList(notifications: List<Notification>, filter: NotificationType?) {
     val filteredNotifications = notifications.filter {
         filter == null || it.type == filter
     }

     LazyColumn {
         items(filteredNotifications) { notification ->
             NotificationItem(notification)
         }
     }
 }

 @Composable
 fun NotificationItem(notification: Notification) {
     val backgroundColor = when (notification.type) {
         NotificationType.GENERAL -> Color(0xFFB3E5FC) // Azul claro
         NotificationType.NEW_POST -> Color(0xFFC8E6C9) // Verde claro
         NotificationType.NEW_MESSAGE -> Color(0xFFFFF9C4) // Amarillo claro
         NotificationType.NEW_LIKE -> Color(0xFFFFCDD2) // Rojo claro
     }

     val iconColor = when (notification.type) {
         NotificationType.GENERAL -> Color(0xFF0288D1) // Azul oscuro
         NotificationType.NEW_POST -> Color(0xFF2E7D32) // Verde oscuro
         NotificationType.NEW_MESSAGE -> Color(0xFFFBC02D) // Amarillo oscuro
         NotificationType.NEW_LIKE -> Color(0xFFD32F2F) // Rojo oscuro
     }

     Row(
         modifier = Modifier
             .fillMaxWidth()
             .background(backgroundColor)
             .padding(16.dp),
         verticalAlignment = Alignment.CenterVertically
     ) {
         Icon(
             imageVector = Icons.Default.Notifications,
             contentDescription = null,
             tint = iconColor,
             modifier = Modifier.size(24.dp)
         )
         Spacer(modifier = Modifier.width(8.dp))
         Column {
             Text(
                 text = notification.title,
                 fontWeight = FontWeight.Bold,
                 fontSize = 18.sp,
                 color = iconColor
             )
             Spacer(modifier = Modifier.height(4.dp))
             Text(
                 text = "${notification.sendAt}",
                 fontSize = 14.sp,
                 color = Color.Gray
             )
             Spacer(modifier = Modifier.height(4.dp))
             Text(
                 text = notification.body,
                 fontSize = 14.sp
             )
         }
     }
 }
