package com.qualape.api.errorHandling

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionHandler {
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "The session is not active")
    @ExceptionHandler(TokenInvalidException::class)
    fun tokenInvalidException(exception: TokenInvalidException) {}

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "The user associated with this email does not exist")
    @ExceptionHandler(EmailInvalidException::class)
    fun emailInvalidException(exception: EmailInvalidException) {}

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "The password is wrong")
    @ExceptionHandler(WrongPasswordException::class)
    fun wrongPasswordException(exception: WrongPasswordException) {}

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "This user already exists")
    @ExceptionHandler(UserAlreadyExistsException::class)
    fun userAlreadyExistsException(exception: UserAlreadyExistsException) {}
}

class TokenInvalidException: Exception()
class EmailInvalidException: Exception()
class WrongPasswordException: Exception()
class UserAlreadyExistsException: Exception()
class SessionAlreadyExistsException: Exception()