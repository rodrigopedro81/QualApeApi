package com.qualape.api.core.services

import com.qualape.api.commons.models.Product
import com.qualape.api.core.data.interactors.UserInteractor
import com.qualape.api.core.data.repositories.ProductJpaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService @Autowired constructor(
    val productJpaRepository: ProductJpaRepository,
    val userInteractor: UserInteractor
) {

    fun retrieveAllProducts(): List<Product> {
        return productJpaRepository.findAll()
    }

    fun saveProductInDatabase(product: Product, email: String): Product {
        return productJpaRepository.save(product).also { productWithNewId ->
            userInteractor.saveProductToOwner(productWithNewId.id, email)
        }
    }
}
