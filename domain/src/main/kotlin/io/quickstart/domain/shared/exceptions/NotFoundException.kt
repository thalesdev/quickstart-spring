package io.quickstart.domain.shared.exceptions

import io.quickstart.domain.shared.enums.ApplicationExceptionType

open class NotFoundException(
    message: String,
    errorCode: String,
    cause: Throwable? = null,
) : ApplicationException(
    ApplicationExceptionType.NOT_FOUND,
    message,
    errorCode,
    cause
)