package com.qualape.api.core.services

import com.qualape.api.core.data.interactors.SessionInteractor
import com.qualape.api.core.data.interactors.UserInteractor
import com.qualape.api.commons.errorHandling.SessionExceptions
import com.qualape.api.commons.models.Session
import com.qualape.api.commons.models.User
import com.qualape.api.commons.cryptography.Cryptographer.cryptographed
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class AuthenticationService @Autowired constructor(
    private val sessionInteractor: SessionInteractor,
    private val userInteractor: UserInteractor
) {

    private fun createNewSession(user: User): Session {
        return if (sessionInteractor.sessionExists(user.email)) {
            val session = sessionInteractor.getSession(user.email)
            val newSession = session.copy(lastValidDay = LocalDate.now().plusDays(7))
            sessionInteractor.updateSession(newSession)
        } else {
            sessionInteractor.saveNewSession(user.email)
        }
    }

    private fun removeExpiredSession(token: UUID) {
        sessionInteractor.deleteSession(token)
    }

    private fun removeExpiredSession(userEmail: String) {
        sessionInteractor.deleteSession(userEmail)
    }

    fun <T> ifUserTokenIsValid(token: UUID, onValidSessionFound: (Session) -> T): T {
        val session = sessionInteractor.getSession(token)
        return if (session.isValid()) {
            onValidSessionFound.invoke(session)
        } else {
            removeExpiredSession(token)
            throw SessionExceptions.TokenExpiredException()
        }
    }

    fun loginUser(email: String, password: String): Session {
        val user = userInteractor.getUser(email)
        if (password.cryptographed() == user.password) {
            return createNewSession(user)
        } else {
            throw SessionExceptions.WrongPasswordException()
        }
    }

    fun registerNewUser(email: String, password: String, name: String): Session {
        val newUser = User.createNewUser(email, password.cryptographed(), name)
        userInteractor.registerNewUser(newUser)
        return createNewSession(newUser)
    }
}
