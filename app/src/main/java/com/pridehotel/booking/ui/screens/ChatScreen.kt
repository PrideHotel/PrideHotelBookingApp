// ChatScreen.kt
package com.pridehotel.booking.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pridehotel.booking.ui.viewmodels.ChatViewModel

@Composable
fun ChatScreen(vm: ChatViewModel) {
  var text by remember { mutableStateOf("") }
  Column(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
    LazyColumn(
      modifier = Modifier.weight(1f).padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      items(vm.chat.collectAsState().value) { (bot, user) ->
        if (user.isNotEmpty()) Text("You: $user")
        if (bot.isNotEmpty()) Text("Bot: $bot")
      }
    }
    Row(Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      TextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier.weight(1f),
        placeholder = { Text("Ask me…") }
      )
      Button(onClick = { vm.send(text); text = "" }) {
        Text("Send")
      }
    }
  }
}
