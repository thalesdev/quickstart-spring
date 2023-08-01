package io.quickstart.domain.todos.ports.driven

import io.quickstart.domain.todos.entities.Todo
import kotlinx.coroutines.flow.Flow

interface ReadingTodo {
    suspend fun getById(id: Int): Todo?
    suspend fun getAll(): Flow<Todo>
}