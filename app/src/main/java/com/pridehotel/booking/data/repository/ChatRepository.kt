package com.pridehotel.booking.data.repository

import com.aallam.openai.client.OpenAI
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
// If BetaOpenAI is still needed for specific features and is resolvable after a successful Gradle sync:
// import com.aallam.openai.api.core.BetaOpenAI

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChatRepository(apiKey: String) {

  // Initialize the OpenAI client with the API key
  private val client = OpenAI(token = apiKey)

  suspend fun ask(question: String): String = withContext(Dispatchers.IO) {
    try {
      val systemMessage = ChatMessage(
        role = ChatRole.System,
        content = "You are a hotel assistant for Pride Hotel. Be helpful and concise."
      )
      val userMessage = ChatMessage(
        role = ChatRole.User,
        content = question
      )

      val chatCompletionRequest = ChatCompletionRequest(
        model = ModelId("gpt-4o"), // Using a modern and capable model like gpt-4o or gpt-4-turbo
        messages = listOf(systemMessage, userMessage)
      )

      // API call
      // If client.chatCompletion or parts of its response are Beta, @OptIn(BetaOpenAI::class) might be needed here or on the function.
      val completion = client.chatCompletion(request = chatCompletionRequest)

      // Accessing the response content safely
      completion.choices.firstOrNull()?.message?.content?.trim().orEmpty()
    } catch (e: Exception) {
      // For a real app, use a proper logging framework.
      println("ChatRepository Error: OpenAI API call failed: ${e.message}")
      e.printStackTrace() // For debugging during development
      "Sorry, I encountered an issue while trying to connect to the assistant. Please try again later." // User-friendly error message
    }
  }
}

