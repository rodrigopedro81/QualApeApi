package com.qualape.api.security

import com.qualape.api.errorHandling.*
import com.qualape.api.models.Session
import com.qualape.api.models.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Repository
interface SessionRepository: JpaRepository<Session, Long> {
    fun findByUserId(userId: String): Optional<Session>
    fun findByLastValidDay(lastValidDay: LocalDate): List<Session>
}

@Repository
interface UserRepository: JpaRepository<User, String>

@Service
class AuthenticationService@Autowired constructor(
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
            if (password == userData.password) {
                return createNewSession(userData)
            } else {
                throw WrongPasswordException()
            }
        } else {
            throw EmailInvalidException()
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

    fun registerNewUser(email: String, password: String, name:String): Session {
        val userExists = userRepository.existsById(email)
        if (userExists) {
            throw UserAlreadyExistsException()
        } else {
            val newUser = User.createNewUser(email, password, name)
            userRepository.save(newUser)
            val newSession = Session(userId = newUser.email)
            return sessionRepository.save(newSession)
        }
    }
}



