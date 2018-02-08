package com.edd.graphql.app.exception

import com.edd.graphql.app.common.Result
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.*
import java.lang.Exception

private val LOG = LoggerFactory.getLogger(GlobalExceptionController::class.java)

@RestControllerAdvice
class GlobalExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handle(e: HttpMessageNotReadableException) =
            Result<Any>(errors = listOf(e.localizedMessage))

    @ExceptionHandler(Exception::class)
    fun handle(e: Exception): Result<*> {
        LOG.error("Unhandled exception", e)
        return Result<Any>(errors = listOf(e.localizedMessage))
    }
}