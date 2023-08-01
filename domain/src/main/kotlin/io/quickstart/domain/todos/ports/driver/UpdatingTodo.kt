package io.quickstart.domain.todos.ports.driver

import io.quickstart.domain.todos.entities.Todo

interface UpdatingTodo {
    suspend fun update(command: UpdateTodoCommand): Todo
}

data class UpdateTodoCommand(
    val id: Int,
    val title: String,
    val description: String
)