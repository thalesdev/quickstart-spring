package io.quickstart.http.routes.request

import io.quickstart.domain.todos.ports.driver.UpdateTodoCommand


data class UpdateTodoRequest(
    val title: String,
    val description: String
)

fun UpdateTodoRequest.toCommand(id: Int) = UpdateTodoCommand(
    id,
    title,
    description
)
