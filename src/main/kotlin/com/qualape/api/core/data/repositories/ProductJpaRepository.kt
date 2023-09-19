package com.qualape.api.core.data.repositories

import com.qualape.api.commons.models.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductJpaRepository: JpaRepository<Product, Long>