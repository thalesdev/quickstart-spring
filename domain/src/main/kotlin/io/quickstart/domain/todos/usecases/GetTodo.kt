package io.quickstart.domain.todos.usecases

import io.quickstart.domain.todos.entities.Todo
import io.quickstart.domain.todos.exceptions.TodoNotFoundException
import io.quickstart.domain.todos.ports.driven.ReadingTodo
import io.quickstart.domain.todos.ports.driver.GettingTodo
import org.springframework.stereotype.Component

@Component
class GetTodo(
    private val readingTodo: ReadingTodo
) : GettingTodo {
    override suspend fun getById(id: Int): Todo =
        readingTodo.getById(id) ?: throw TodoNotFoundException(id)
}