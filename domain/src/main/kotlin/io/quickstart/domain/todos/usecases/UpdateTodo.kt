package io.quickstart.domain.todos.usecases

import io.quickstart.domain.todos.entities.Todo
import io.quickstart.domain.todos.entities.TodoEvent
import io.quickstart.domain.todos.exceptions.TodoNotFoundException
import io.quickstart.domain.todos.ports.driven.NotifyingTodo
import io.quickstart.domain.todos.ports.driven.ReadingTodo
import io.quickstart.domain.todos.ports.driven.UpdateTodoData
import io.quickstart.domain.todos.ports.driven.WritingTodo
import io.quickstart.domain.todos.ports.driver.UpdateTodoCommand
import io.quickstart.domain.todos.ports.driver.UpdatingTodo
import org.springframework.stereotype.Component

@Component
class UpdateTodo(
    private val writingTodo: WritingTodo,
    private val readingTodo: ReadingTodo,
    private val notifyingTodo: NotifyingTodo
) : UpdatingTodo {
    override suspend fun update(command: UpdateTodoCommand): Todo =
        readingTodo.getById(command.id)?.let {
            writingTodo.update(
                command.id,
                UpdateTodoData(
                    command.title,
                    command.description,
                    it.doneAt,
                    it.doneBy
                )
            )?.also { todo ->
                notifyingTodo.notify(
                    TodoEvent.TodoUpdated(
                        todo.id,
                        todo.title,
                        todo.description
                    )
                )
            }
        } ?: throw TodoNotFoundException(command.id)
}