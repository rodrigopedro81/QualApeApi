package com.qualape.api.commons.errorHandling

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionHandler {
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "This session no longer exists, log in again")
    @ExceptionHandler(SessionExceptions.TokenInvalidException::class)
    fun tokenInvalidException(exception: SessionExceptions.TokenInvalidException) {
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "The password is wrong")
    @ExceptionHandler(SessionExceptions.WrongPasswordException::class)
    fun wrongPasswordException(exception: SessionExceptions.WrongPasswordException) {
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "This user already exists")
    @ExceptionHandler(SessionExceptions.UserAlreadyExistsException::class)
    fun userAlreadyExistsException(exception: SessionExceptions.UserAlreadyExistsException) {
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "This user does not exists")
    @ExceptionHandler(SessionExceptions.UserDoesNotExistsException::class)
    fun userDoesNotExistsException(exception: SessionExceptions.UserDoesNotExistsException) {
    }
}
