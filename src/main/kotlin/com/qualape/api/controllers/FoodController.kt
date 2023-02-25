package com.qualape.api.controllers

import com.qualape.api.models.Food
import com.qualape.api.security.AuthenticationService
import com.qualape.api.services.FoodService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/food")
class FoodController(
    val foodService: FoodService,
    val authenticationService: AuthenticationService
) {

    @PostMapping("/new")
    fun saveFood(
        @RequestBody @Valid food: Food,
        @RequestParam sessionToken: Long
    ): Food {
        return authenticationService.ifTokenIsValidReturn(sessionToken) {
            foodService.saveNewFood(food)
        }
    }

    @GetMapping("")
    fun getAllFoods(): List<Food> = foodService.retrieveAllFoods()
}
