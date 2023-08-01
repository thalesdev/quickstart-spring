package io.quickstart.domain.todos.ports.driven

import io.quickstart.domain.todos.entities.Todo
import java.time.LocalDateTime

interface WritingTodo {
    suspend fun create(data: CreateTodoData): Todo?

    suspend fun update(id: Int, data: UpdateTodoData): Todo?
}

data class CreateTodoData(
    val title: String,
    val description: String
)


data class UpdateTodoData(
    val title: String,
    val description: String,
    val doneAt: LocalDateTime?,
    val doneBy: Int?
)