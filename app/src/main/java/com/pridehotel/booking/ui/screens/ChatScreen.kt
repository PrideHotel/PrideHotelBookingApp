// ChatScreen.kt
package com.pridehotel.booking.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // Ensure this import is present
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pridehotel.booking.ui.viewmodels.ChatViewModel

@Composable
fun ChatScreen(vm: ChatViewModel) {
  var text by remember { mutableStateOf("") }
  // Collect the state at the top level of the Composable function
  val chatMessages by vm.chat.collectAsState()

  Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
    LazyColumn(
      modifier = Modifier.weight(1f).padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      // Use the collected state here
      items(chatMessages) { (bot, user) -> // Assuming chatMessages is List<Pair<String, String>>
        if (user.isNotEmpty()) Text("You: $user", style = MaterialTheme.typography.bodyMedium)
        if (bot.isNotEmpty()) Text("Bot: $bot", style = MaterialTheme.typography.bodyMedium)
      }
    }
    Row(Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      TextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier.weight(1f),
        placeholder = { Text("Ask me…") }
      )
      Button(onClick = {
        if (text.isNotBlank()) {
          vm.send(text)
          text = ""
        }
      }) {
        Text("Send")
      }
    }
  }
}

