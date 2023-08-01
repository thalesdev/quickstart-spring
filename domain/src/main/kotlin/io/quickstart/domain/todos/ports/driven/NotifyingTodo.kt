package io.quickstart.domain.todos.ports.driven

import io.quickstart.domain.todos.entities.TodoEvent

interface NotifyingTodo {
    suspend fun notify(todo: TodoEvent)
}