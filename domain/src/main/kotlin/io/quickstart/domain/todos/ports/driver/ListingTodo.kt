package io.quickstart.domain.todos.ports.driver

import io.quickstart.domain.todos.entities.Todo

interface ListingTodo {
    suspend fun getAll(): List<Todo>
}