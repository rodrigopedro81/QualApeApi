package com.qualape.api.core.services

import com.qualape.api.core.models.Food
import com.qualape.api.core.data.interactors.UserInteractor
import com.qualape.api.core.data.repositories.FoodJpaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FoodService @Autowired constructor(
    val foodJpaRepository: FoodJpaRepository,
    val userInteractor: UserInteractor
) {

    fun retrieveAllFoods(): List<Food> {
        return foodJpaRepository.findAll()
    }

    fun saveFoodInDatabase(food: Food, userEmail: String): Food {
        userInteractor.updateUserByEmail(userEmail) {
            it.apply { foodIds.add(food.id) }
        }
        return foodJpaRepository.save(food)
    }
}
