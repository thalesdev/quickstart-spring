package io.quickstart.domain.todos.entities

import java.time.LocalDateTime

interface TodoEvent {
    data class TodoUpdated(
        val id: Int,
        val title: String,
        val description: String
    ) : TodoEvent

    data class TodoCompleted(
        val id: Int,
        val doneAt: LocalDateTime,
        val doneBy: Int
    ) : TodoEvent
}