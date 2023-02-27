package com.qualape.api.core.data.repositories

import com.qualape.api.core.models.Food
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FoodJpaRepository: JpaRepository<Food, Long> {
    fun findByCategory(category: String): List<Food>
}