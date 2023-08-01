package io.quickstart.domain.shared.exceptions

import io.quickstart.domain.shared.enums.ApplicationExceptionType

abstract class ApplicationException(
    val type: ApplicationExceptionType,
    override val message: String,
    val errorCode: String,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause) {
    var data: Any? = null
}