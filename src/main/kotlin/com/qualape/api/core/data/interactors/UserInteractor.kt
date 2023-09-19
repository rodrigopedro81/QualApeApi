package com.qualape.api.core.data.interactors

import com.qualape.api.core.data.repositories.UserJpaRepository
import com.qualape.api.commons.errorHandling.SessionExceptions
import com.qualape.api.commons.models.User
import com.qualape.api.commons.ktx.getByIdOrThrow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

interface UserInteractor {
    fun updateUser(userEmail: String, updateFunction: (User) -> User): User
    fun saveFoodToOwner(foodId: Long, userEmail: String)
    fun saveProductToOwner(productId: Long, userEmail: String)
    fun saveJobToOwner(jobId: Long, userEmail: String)
    fun getUser(userEmail: String): User
    fun getUserRegisteredFoodIds(userEmail: String): List<Long>
    fun getUserRegisteredProductIds(userEmail: String): List<Long>
    fun getUserRegisteredJobIds(userEmail: String): List<Long>
    fun userExists(userEmail: String): Boolean
    fun registerNewUser(user: User)
}

@Component
class UserInteractorImpl @Autowired constructor(
    val repository: UserJpaRepository
) : UserInteractor {

    override fun updateUser(userEmail: String, updateFunction: (User) -> User): User {
        val user = getUser(userEmail)
        val updatedUser = updateFunction.invoke(user)
        return repository.save(updatedUser)
    }

    override fun registerNewUser(user: User) {
        val userExists = userExists(user.email)
        if (userExists) {
            throw SessionExceptions.UserAlreadyExistsException()
        } else {
            repository.save(user)
        }
    }

    override fun saveFoodToOwner(foodId: Long, userEmail: String) {
        updateUser(userEmail) { user ->
            user.apply { foodIds.add(foodId) }
        }
    }

    override fun saveProductToOwner(productId: Long, userEmail: String) {
        updateUser(userEmail) { user ->
            user.apply { productIds.add(productId) }
        }
    }

    override fun saveJobToOwner(jobId: Long, userEmail: String) {
        updateUser(userEmail) { user ->
            user.apply { jobIds.add(jobId) }
        }
    }

    override fun getUser(userEmail: String): User =
        repository.getByIdOrThrow(
            id = userEmail,
            exception = SessionExceptions.UserDoesNotExistsException()
        )

    override fun getUserRegisteredFoodIds(userEmail: String): List<Long> = getUser(userEmail).foodIds

    override fun getUserRegisteredProductIds(userEmail: String): List<Long> = getUser(userEmail).productIds

    override fun getUserRegisteredJobIds(userEmail: String): List<Long> = getUser(userEmail).jobIds

    override fun userExists(userEmail: String): Boolean = repository.existsById(userEmail)
}
