package io.quickstart.domain.todos.exceptions

import io.quickstart.domain.shared.exceptions.InvalidException

class TodoInvalidException : InvalidException(
    message = "Invalid todo.",
    errorCode = "INVALID_TODO",
)