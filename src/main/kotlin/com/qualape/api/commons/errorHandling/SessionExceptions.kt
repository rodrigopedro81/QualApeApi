package com.qualape.api.commons.errorHandling

sealed class SessionExceptions: Exception() {
    class WrongPasswordException: SessionExceptions()
    class UserAlreadyExistsException: SessionExceptions()
    class TokenInvalidException: SessionExceptions()
    class TokenExpiredException: SessionExceptions()
    class UserDoesNotExistsException: SessionExceptions()
}