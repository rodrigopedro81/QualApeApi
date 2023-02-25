package com.qualape.api.security

import com.qualape.api.errorHandling.*
import com.qualape.api.models.Session
import com.qualape.api.models.User
import com.qualape.api.repositories.SessionRepository
import com.qualape.api.repositories.UserRepository
import com.qualape.api.security.BlowfishCryptographer.cryptographed
import com.qualape.api.security.BlowfishCryptographer.decryptographed
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class AuthenticationService @Autowired constructor(
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository
) {

    init {
        removeExpiredDates()
    }

    private fun removeExpiredDates() {
        val sessionList = sessionRepository.findByLastValidDay(LocalDate.now())
        for (session in sessionList) {
            sessionRepository.delete(session)
        }
    }

    fun <T>ifTokenIsValidReturn(token: Long, operation: () -> T) : T {
        return if (sessionRepository.existsById(token)) {
            operation.invoke()
        } else {
            throw TokenInvalidException()
        }
    }

    fun loginUser(email:String, password:String): Session {
        val userRequest = userRepository.findById(email)
        if (userRequest.isPresent) {
            val userData = userRequest.get()
            if (password == userData.password.decryptographed()) {
                return createNewSession(userData)
            } else {
                throw WrongPasswordException()
            }
        } else {
            throw EmailInvalidException()
        }
    }

    fun registerNewUser(email: String, password: String, name:String): Session {
        val userExists = userRepository.existsById(email)
        if (userExists) {
            throw UserAlreadyExistsException()
        } else {
            val newUser = User.createNewUser(email, password.cryptographed(), name)
            userRepository.save(newUser)
            return createNewSession(newUser)
        }
    }

    private fun createNewSession(user: User): Session {
        val sessionExists = sessionRepository.findByUserId(user.email).isPresent
        return if (sessionExists) {
            throw SessionAlreadyExistsException()
        } else {
            val newSession = Session(userId = user.email)
            sessionRepository.save(newSession)
        }
    }
}
