package io.quickstart.kafka.todos.messages

import io.quickstart.domain.todos.entities.TodoEvent
import java.time.LocalDateTime

interface TodoEventMessage {
    data class TodoUpdated(
        val id: Int,
        val title: String,
        val description: String
    ) : TodoEventMessage

    data class TodoCompleted(
        val id: Int,
        val doneAt: LocalDateTime,
        val doneBy: Int
    ) : TodoEventMessage
}

fun TodoEvent.toMessage(): TodoEventMessage = when (this) {
    is TodoEvent.TodoUpdated -> TodoEventMessage.TodoUpdated(id, title, description)
    is TodoEvent.TodoCompleted -> TodoEventMessage.TodoCompleted(id, doneAt, doneBy)
    else -> throw IllegalArgumentException("Conversion not allowed")
}