// ChatRepository.kt
package com.pridehotel.booking.data

import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.client.OpenAI
import com.aallam.openai.client.chat.ChatCompletionRequest
import com.aallam.openai.client.chat.ChatMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChatRepository(apiKey: String) {
  @OptIn(BetaOpenAI::class)
  private val client = OpenAI { model = "gpt-4"; this.apiKey = apiKey }

  suspend fun ask(question: String): String = withContext(Dispatchers.IO) {
    val req = ChatCompletionRequest(
      model = "gpt-4",
      messages = listOf(
        ChatMessage(system = "You are a hotel assistant."),
        ChatMessage(user = question)
      )
    )
    client.chatCompletion(req).choices.first().message?.content.orEmpty()
  }
}
