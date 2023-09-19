package com.qualape.api.core.controllers

import com.qualape.api.commons.models.Food
import com.qualape.api.core.services.AuthenticationService
import com.qualape.api.core.services.FoodService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/food")
class FoodController(
    val foodService: FoodService,
    val authenticationService: AuthenticationService
) {

    @PostMapping("/v1/new")
    fun saveFood(
        @RequestBody @Valid food: Food,
        @RequestParam userToken: UUID
    ): Food {
        return authenticationService.ifUserTokenIsValid(userToken) { session ->
            foodService.saveFoodInDatabase(food, session.userEmail)
        }
    }

    @GetMapping("")
    fun getAllFoods(
        @RequestParam userToken: UUID
    ): List<Food> = foodService.retrieveAllFoods()
}
