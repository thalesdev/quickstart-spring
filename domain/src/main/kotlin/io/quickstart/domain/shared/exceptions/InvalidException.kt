package io.quickstart.domain.shared.exceptions

import io.quickstart.domain.shared.enums.ApplicationExceptionType

open class InvalidException(
    message: String,
    errorCode: String,
    cause: Throwable? = null,
) : ApplicationException(
    ApplicationExceptionType.INVALID,
    message,
    errorCode,
    cause
)