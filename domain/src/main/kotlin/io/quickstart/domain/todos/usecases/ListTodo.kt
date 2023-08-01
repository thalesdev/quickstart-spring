package io.quickstart.domain.todos.usecases

import io.quickstart.domain.todos.entities.Todo
import io.quickstart.domain.todos.ports.driven.ReadingTodo
import io.quickstart.domain.todos.ports.driver.ListingTodo
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Component

@Component
class ListTodo(
    private val readingTodo: ReadingTodo
) : ListingTodo {
    override suspend fun getAll(): List<Todo> =
        readingTodo.getAll().toList()
}