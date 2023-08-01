package io.quickstart.domain.todos.ports.driver

import io.quickstart.domain.todos.entities.Todo

interface CreatingTodo {
    suspend fun create(todo: CreateTodoCommand): Todo
}

data class CreateTodoCommand(
    val title: String,
    val description: String
)
