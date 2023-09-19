package com.qualape.api.core.controllers

import com.qualape.api.commons.models.Product
import com.qualape.api.core.services.AuthenticationService
import com.qualape.api.core.services.ProductService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/product")
class ProductController(
    val productService: ProductService,
    val authenticationService: AuthenticationService
) {

    @PostMapping("/v1/new")
    fun saveProduct(
        @RequestBody @Valid product: Product,
        @RequestParam userToken: UUID
    ): Product {
        return authenticationService.ifUserTokenIsValid(userToken) { session ->
            productService.saveProductInDatabase(product, session.userEmail)
        }
    }

    @GetMapping("")
    fun getAllProducts(
        @RequestParam userToken: UUID
    ) : List<Product> = productService.retrieveAllProducts()
}