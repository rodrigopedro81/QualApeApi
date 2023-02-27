package com.qualape.api.core.controllers

import com.qualape.api.core.models.Food
import com.qualape.api.core.services.AuthenticationService
import com.qualape.api.core.services.FoodService
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
        @RequestParam userToken: UUID
    ): Food {
        return authenticationService.ifValidSessionExists(userToken) { session ->
            foodService.saveFoodInDatabase(food, session.userEmail)
        }
    }

    @GetMapping("")
    fun getAllFoods(): List<Food> = foodService.retrieveAllFoods()
}
