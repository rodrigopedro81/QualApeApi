package com.qualape.api.core.repositories

import com.qualape.api.core.models.Food
import com.qualape.api.core.models.Session
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.UUID

@Repository
interface FoodRepository: JpaRepository<Food, Long> {
    fun findByCategory(category: String): List<Food>
}