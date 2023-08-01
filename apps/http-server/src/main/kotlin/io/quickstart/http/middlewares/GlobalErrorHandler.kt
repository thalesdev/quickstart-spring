package io.quickstart.http.middlewares

import io.quickstart.domain.shared.enums.ApplicationExceptionType
import io.quickstart.domain.shared.exceptions.ApplicationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@RestControllerAdvice
class GlobalErrorHandler {

    @ExceptionHandler(ApplicationException::class)
    fun handler(ex: ApplicationException, exchange: ServerWebExchange): Mono<ResponseEntity<ErrorResponse>> =
        when (ex.type) {
            ApplicationExceptionType.NOT_FOUND -> ex.buildResponseEntity(HttpStatus.NOT_FOUND)
            ApplicationExceptionType.INVALID -> ex.buildResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY)
        }
}


data class ErrorResponse(
    val code: String,
    val message: String,
    val data: Any?
)


fun ErrorResponse.buildResponseEntity(status: HttpStatus): Mono<ResponseEntity<ErrorResponse>> =
    Mono.just(
        ResponseEntity
            .status(status)
            .body(this)
    )


fun ApplicationException.buildResponseEntity(status: HttpStatus) =
    ErrorResponse(
        code = errorCode,
        message = message,
        data = data
    ).buildResponseEntity(status)