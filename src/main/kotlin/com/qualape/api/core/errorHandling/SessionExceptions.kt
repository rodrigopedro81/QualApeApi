package com.qualape.api.core.errorHandling

sealed class SessionExceptions: Exception() {
    class WrongPasswordException: SessionExceptions()
    class UserAlreadyExistsException: SessionExceptions()
    class SessionAlreadyExistsException: SessionExceptions()
    class TokenInvalidException: SessionExceptions()
    class TokenExpiredException: SessionExceptions()
    class UserDoesNotExistsException: SessionExceptions()
}