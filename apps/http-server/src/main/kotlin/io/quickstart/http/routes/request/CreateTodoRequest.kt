package io.quickstart.http.routes.request

import io.quickstart.domain.todos.ports.driver.CreateTodoCommand

data class CreateTodoRequest(
    val title: String,
    val description: String
)

fun CreateTodoRequest.toCommand() = CreateTodoCommand(
    title = title,
    description = description
)