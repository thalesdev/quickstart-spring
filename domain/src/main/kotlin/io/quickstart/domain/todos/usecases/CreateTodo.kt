package io.quickstart.domain.todos.usecases

import io.quickstart.domain.todos.entities.Todo
import io.quickstart.domain.todos.ports.driven.CreateTodoData
import io.quickstart.domain.todos.ports.driven.WritingTodo
import io.quickstart.domain.todos.ports.driver.CreateTodoCommand
import io.quickstart.domain.todos.ports.driver.CreatingTodo
import org.springframework.stereotype.Component

@Component
class CreateTodo(
    private val writingTodo: WritingTodo
) : CreatingTodo {
    override suspend fun create(todo: CreateTodoCommand): Todo =
        writingTodo.create(
            CreateTodoData(
                todo.title,
                todo.description
            )
        ) ?: throw Exception("Todo not created")
}