package com.qualape.api.core.services

import com.qualape.api.core.models.Food
import com.qualape.api.core.models.Session
import com.qualape.api.core.repositories.FoodRepository
import com.qualape.api.core.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FoodService @Autowired constructor(
    val foodRepository: FoodRepository,
    val userRepository: UserRepository
) {

    fun retrieveAllFoods(): List<Food> {
        return foodRepository.findAll()
    }

    fun saveFoodInDatabase(food: Food, session: Session): Food {
        return foodRepository.save(food)
    }
}
