package com.qualape.api.core.services

import com.qualape.api.core.errorHandling.SessionExceptions
import com.qualape.api.core.models.Product
import com.qualape.api.core.models.Session
import com.qualape.api.core.models.User
import com.qualape.api.core.repositories.ProductRepository
import com.qualape.api.core.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService @Autowired constructor(
    val productRepository: ProductRepository,
    val userRepository: UserRepository
) {

    fun retrieveAllProducts(): List<Product> {
        return productRepository.findAll()
    }

    fun saveProductInDatabase(product: Product, session: Session): Product {
        val userRequest = userRepository.findById(session.userEmail)
        return if (userRequest.isPresent) {
            val userData = userRequest.get()
            productRepository.save(product).also {
                userData.apply { productIds.add(it.id!!) }
                userRepository.save(userData)
            }
        } else {
            throw SessionExceptions.UserDoesNotExistsException()
        }
    }
}
