// ChatViewModel.kt
package com.pridehotel.booking.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.pridehotel.booking.data.repository.ChatRepository // Fixed import path

class ChatViewModel(
  private val repo: ChatRepository
) : ViewModel() {
  private val _chat = MutableStateFlow<List<Pair<String,String>>>(emptyList())
  val chat: StateFlow<List<Pair<String,String>>> = _chat

  fun send(userMsg: String) {
    if (userMsg.isBlank()) return // Avoid sending empty messages

    // Add user message to chat immediately for UI update
    // The bot's response will replace the temporary empty string later
    _chat.value = _chat.value + Pair("", userMsg)

    viewModelScope.launch {
      val botResponse = repo.ask(userMsg)
      // Update the last entry: replace the temporary empty bot message with the actual response,
      // and keep the user's message.
      val currentChatList = _chat.value.toMutableList()
      if (currentChatList.isNotEmpty()) {
        val lastMessagePair = currentChatList.last()
        // Ensure we are updating the correct pair that was added for this user message
        if (lastMessagePair.first.isEmpty() && lastMessagePair.second == userMsg) {
          currentChatList[currentChatList.size - 1] = Pair(botResponse, userMsg)
          _chat.value = currentChatList
        } else {
          // Fallback or error handling if the expected pair isn't found
          // This might happen if multiple sends occur rapidly or state is unexpected
          // For now, just add as a new pair, but this logic might need refinement
          _chat.value = _chat.value + Pair(botResponse, "") // Or handle error appropriately
        }
      } else {
        // Should not happen if we added the user message pair correctly
        _chat.value = _chat.value + Pair(botResponse, "") // Or handle error
      }
    }
  }
}

