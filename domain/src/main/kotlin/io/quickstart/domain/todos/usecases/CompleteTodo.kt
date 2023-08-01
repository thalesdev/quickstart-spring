package io.quickstart.domain.todos.usecases

import io.quickstart.domain.todos.entities.TodoEvent
import io.quickstart.domain.todos.ports.driven.NotifyingTodo
import io.quickstart.domain.todos.ports.driven.ReadingTodo
import io.quickstart.domain.todos.ports.driven.UpdateTodoData
import io.quickstart.domain.todos.ports.driven.WritingTodo
import io.quickstart.domain.todos.ports.driver.CompleteTodoCommand
import io.quickstart.domain.todos.ports.driver.CompletingTodo
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class CompleteTodo(
    private val writingTodo: WritingTodo,
    private val readingTodo: ReadingTodo,
    private val notifyingTodo: NotifyingTodo
) : CompletingTodo {
    override suspend fun complete(command: CompleteTodoCommand) {
        readingTodo.getById(command.id)?.let {
            val updateTime = LocalDateTime.now()
            writingTodo.update(
                command.id,
                UpdateTodoData(
                    it.title,
                    it.description,
                    updateTime,
                    command.doneBy
                )
            )?.let {
                notifyingTodo.notify(
                    TodoEvent.TodoCompleted(
                        command.id,
                        updateTime,
                        command.doneBy
                    )
                )
            }
        } ?: throw Exception("Todo not found")
    }
}