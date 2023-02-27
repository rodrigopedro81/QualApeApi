package com.qualape.api.core.errorHandling

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionHandler {
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "This user associated with this session key is no longer logged in")
    @ExceptionHandler(SessionExceptions.TokenInvalidException::class)
    fun tokenInvalidException(exception: SessionExceptions.TokenInvalidException) {}

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "The user associated with this email does not exist")
    @ExceptionHandler(SessionExceptions.UserDoesNotExistsException::class)
    fun emailInvalidException(exception: SessionExceptions.UserDoesNotExistsException) {}

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "The password is wrong")
    @ExceptionHandler(SessionExceptions.WrongPasswordException::class)
    fun wrongPasswordException(exception: SessionExceptions.WrongPasswordException) {}

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "This user already exists")
    @ExceptionHandler(SessionExceptions.UserAlreadyExistsException::class)
    fun userAlreadyExistsException(exception: SessionExceptions.UserAlreadyExistsException) {}

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "This user is already logged in")
    @ExceptionHandler(SessionExceptions.SessionAlreadyExistsException::class)
    fun sessionAlreadyExistsException(exception: SessionExceptions.SessionAlreadyExistsException) {}
}
