package com.qualape.api.core.data.interactors

import com.qualape.api.core.data.repositories.SessionJpaRepository
import com.qualape.api.commons.errorHandling.SessionExceptions
import com.qualape.api.commons.models.Session
import com.qualape.api.commons.ktx.getByCustomOrThrow
import com.qualape.api.commons.ktx.getByIdOrThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

interface SessionInteractor {

    fun getSession(userEmail: String): Session
    fun getSession(token: UUID): Session
    fun saveNewSession(userEmail: String): Session
    fun updateSession(session: Session): Session
    fun deleteSession(token: UUID)
    fun deleteSession(userEmail: String)
    fun sessionExists(userEmail: String): Boolean
}

@Component
class SessionInteractorImpl @Autowired constructor(
    val repository: SessionJpaRepository
) : SessionInteractor {

    override fun getSession(userEmail: String): Session {
        return repository.getByCustomOrThrow(
            get = { repository.findByUserEmail(userEmail) },
            exception = SessionExceptions.TokenInvalidException()
        )
    }

    override fun getSession(token: UUID): Session {
        return repository.getByIdOrThrow(
            id = token,
            exception = SessionExceptions.TokenInvalidException()
        )
    }

    override fun saveNewSession(userEmail: String): Session {
        val newSession = Session(token = UUID.randomUUID(), userEmail = userEmail)
        return repository.save(newSession)
    }

    override fun updateSession(session: Session): Session {
        return repository.save(session)
    }

    override fun deleteSession(token: UUID) {
        repository.deleteById(token)
    }

    override fun deleteSession(userEmail: String) {
        repository.deleteByUserEmail(userEmail)
    }

    override fun sessionExists(userEmail: String): Boolean = repository.existsByUserEmail(userEmail)

}
