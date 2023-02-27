package com.qualape.api.core.data.interactors

import com.qualape.api.core.data.repositories.UserJpaRepository
import com.qualape.api.core.errorHandling.SessionExceptions
import com.qualape.api.core.models.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

interface UserInteractor {
    fun updateUserByEmail(email: String, update: (User) -> User): User
}

@Component
class UserInteractorImpl @Autowired constructor(
    val repository: UserJpaRepository
): UserInteractor {

    override fun updateUserByEmail(email: String, update: (User) -> User): User {
        val userRequest = repository.findById(email)
        if (userRequest.isPresent) {
            val userData = userRequest.get()
            val newUser = update.invoke(userData)
            return repository.save(newUser)
        } else {
            throw SessionExceptions.UserDoesNotExistsException()
        }
    }
}
