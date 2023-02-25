package com.qualape.api.services

import com.qualape.api.models.Food
import com.qualape.api.repositories.FoodRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FoodService @Autowired constructor(val foodRepository: FoodRepository) {

    fun retrieveAllFoods(): List<Food> {
        return foodRepository.findAll()
    }

    fun saveNewFood(food: Food): Food {
        return foodRepository.save(food)
    }
}
