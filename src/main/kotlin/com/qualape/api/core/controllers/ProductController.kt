package com.qualape.api.core.controllers

import com.qualape.api.core.models.Product
import com.qualape.api.core.models.User
import com.qualape.api.core.services.AuthenticationService
import com.qualape.api.core.services.ProductService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/product")
class ProductController(
    val productService: ProductService,
    val authenticationService: AuthenticationService
) {

    @PostMapping("/new")
    fun saveProduct(
        @RequestBody @Valid product: Product,
        @RequestParam userToken: UUID
    ): Product {
        return authenticationService.ifValidSessionExists(userToken) { session ->
            productService.saveProductInDatabase(product, session.userEmail)
        }
    }

    @GetMapping("")
    fun getAllProducts() : List<Product> = productService.retrieveAllProducts()
}