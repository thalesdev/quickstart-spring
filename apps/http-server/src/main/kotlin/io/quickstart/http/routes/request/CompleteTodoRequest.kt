package io.quickstart.http.routes.request

import io.quickstart.domain.todos.ports.driver.CompleteTodoCommand

data class CompleteTodoRequest(
    val doneBy: Int
)


fun CompleteTodoRequest.toCommand(id: Int) = CompleteTodoCommand(
    id,
    doneBy
)