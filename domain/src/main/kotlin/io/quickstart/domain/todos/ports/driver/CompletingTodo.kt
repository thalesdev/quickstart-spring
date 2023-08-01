package io.quickstart.domain.todos.ports.driver

interface CompletingTodo {
    suspend fun complete(command: CompleteTodoCommand)
}

data class CompleteTodoCommand(
    val id: Int,
    val doneBy: Int
)