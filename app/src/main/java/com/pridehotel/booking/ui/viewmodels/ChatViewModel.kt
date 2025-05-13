// ChatViewModel.kt
package com.pridehotel.booking.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.pridehotel.booking.data.ChatRepository

class ChatViewModel(
  private val repo: ChatRepository
) : ViewModel() {
  private val _chat = MutableStateFlow<List<Pair<String,String>>>(emptyList())
  val chat: StateFlow<List<Pair<String,String>>> = _chat

  fun send(userMsg: String) {
    _chat.value = _chat.value + ("" to userMsg) // temporary user msg
    viewModelScope.launch {
      val response = repo.ask(userMsg)
      _chat.value = _chat.value.dropLast(1) + (_chat.value.last().first to userMsg) +
          (response to "")
    }
  }
}
