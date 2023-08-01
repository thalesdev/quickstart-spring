package io.quickstart.domain.todos.ports.driver

import io.quickstart.domain.todos.entities.Todo

interface GettingTodo {
    suspend fun getById(id: Int): Todo
}