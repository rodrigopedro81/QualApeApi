package com.qualape.api.repositories

import com.qualape.api.models.Food
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface FoodRepository: JpaRepository<Food, Long>