package io.quickstart.domain.todos.entities

import java.time.LocalDateTime

data class Todo(
    val id: Int,
    val title: String,
    val description: String,
    val doneAt: LocalDateTime?,
    val doneBy: Int?
)