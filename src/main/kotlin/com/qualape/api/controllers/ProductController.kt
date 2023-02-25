package com.qualape.api.controllers

import com.qualape.api.models.Product
import com.qualape.api.services.ProductService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/product")
class ProductController(
    val productService: ProductService
) {

    @PostMapping("/new")
    fun saveProduct(@RequestBody product: Product): Product {
        return productService.saveNewProduct(product)
    }

    @GetMapping("")
    fun getAllProducts() : List<Product> = productService.retrieveAllProducts()
}