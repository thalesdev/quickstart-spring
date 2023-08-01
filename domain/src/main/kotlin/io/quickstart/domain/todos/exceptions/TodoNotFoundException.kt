package io.quickstart.domain.todos.exceptions

import io.quickstart.domain.shared.exceptions.NotFoundException

class TodoNotFoundException(todoId: Int?) : NotFoundException(
    message = "Todo $todoId not found.",
    errorCode = "NOT_FOUND_TODO",
)