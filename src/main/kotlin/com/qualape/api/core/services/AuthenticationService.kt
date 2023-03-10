package com.qualape.api.core.services

import com.qualape.api.core.errorHandling.SessionExceptions
//import com.qualape.api.utils.OnRegisterEmailBuilder
//import com.qualape.api.core.repositories.EmailService
import com.qualape.api.core.models.Session
import com.qualape.api.core.models.User
import com.qualape.api.core.data.repositories.SessionJpaRepository
import com.qualape.api.core.data.repositories.UserJpaRepository
import com.qualape.api.utils.Cryptographer.cryptographed
import com.qualape.api.utils.Cryptographer.decryptographed
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class AuthenticationService @Autowired constructor(
    private val sessionJpaRepository: SessionJpaRepository,
    private val userJpaRepository: UserJpaRepository,
//    private val emailService: EmailService
) {

    private fun createNewSession(user: User): Session {
        val sessionExists = sessionJpaRepository.findByUserEmail(user.email).isPresent
        return if (sessionExists) {
            throw SessionExceptions.SessionAlreadyExistsException()
        } else {
            val newSession = Session(userEmail = user.email)
            sessionJpaRepository.save(newSession)
        }
    }

    private fun removeExpiredSession(token: UUID) {
        sessionJpaRepository.deleteById(token)
    }

    fun <T> ifValidSessionExists(token: UUID, onValidSessionFound: (Session) -> T) : T {
        val sessionRequest = sessionJpaRepository.findById(token)
        return if (sessionRequest.isPresent) {
            val sessionData = sessionRequest.get()
            if (sessionData.lastValidDay >= LocalDate.now()) {
                onValidSessionFound.invoke(sessionData)
            } else {
                removeExpiredSession(token)
                throw SessionExceptions.TokenExpiredException()
            }
        } else {
            throw SessionExceptions.TokenInvalidException()
        }
    }

    fun loginUser(email:String, password:String): Session {
        val userRequest = userJpaRepository.findById(email)
        if (userRequest.isPresent) {
            val userData = userRequest.get()
            if (password == userData.password.decryptographed()) {
                return createNewSession(userData)
            } else {
                throw SessionExceptions.WrongPasswordException()
            }
        } else {
            throw SessionExceptions.UserDoesNotExistsException()
        }
    }

    fun registerNewUser(email: String, password: String, name:String): Session {
        val userExists = userJpaRepository.existsById(email)
        if (userExists) {
            throw SessionExceptions.UserAlreadyExistsException()
        } else {
            val newUser = User.createNewUser(email, password.cryptographed(), name)
            userJpaRepository.save(newUser)
//            val newEmail = OnRegisterEmailBuilder().buildEmail(name)
//            emailService.send(email, newEmail)
            return createNewSession(newUser)
        }
    }
}
