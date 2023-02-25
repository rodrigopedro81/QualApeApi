package com.qualape.api.services

import com.qualape.api.models.Product
import com.qualape.api.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService @Autowired constructor(val productRepository: ProductRepository) {

    fun retrieveAllProducts(): List<Product> {
        return productRepository.findAll()
    }

    fun saveNewProduct(product: Product): Product {
        return productRepository.save(product)
    }
}
