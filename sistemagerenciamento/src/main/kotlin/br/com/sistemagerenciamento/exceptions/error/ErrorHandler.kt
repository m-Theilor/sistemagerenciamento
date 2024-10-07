package br.com.sistemagerenciamento.exceptions.error

import br.com.sistemagerenciamento.exceptions.ProjetoNaoEncontradoException
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler




private val LOGGER = KotlinLogging.logger {}

@ControllerAdvice
class ErrorHandler() {

    @ExceptionHandler(Exception::class)
    fun handlerException(ex: Exception): ResponseEntity<ErrorResponse> {
        return ex.toServerResponse()
    }

}

private fun Throwable.toResponse(): Pair<HttpStatus, ErrorResponse> =
    when (this) {
        is ProjetoNaoEncontradoException -> toResponse(
            id = this.projetoId,
            statusCode = HttpStatus.NOT_FOUND,
        )

        else -> {
            toResponse(
                statusCode = HttpStatus.BAD_REQUEST,
            )
        }
    }


private fun Throwable.toResponse(
    id: Int? = null,
    statusCode: HttpStatus,
    message: String = this.message ?: "",
): Pair<HttpStatus, ErrorResponse> {
    val response = ErrorResponse(
        id = id,
        message = message,
    )

    val fullMessage = "${statusCode.value()} [${this.javaClass.simpleName}] $message"
    if (statusCode.is5xxServerError) {
        LOGGER.error(this) {fullMessage}
    } else {
        LOGGER.warn {fullMessage}
    }

    return statusCode to response
}

fun Throwable.toServerResponse(): ResponseEntity<ErrorResponse> {
    val (statusCode, response) = toResponse()
    return ResponseEntity.status(statusCode).body(response)
}