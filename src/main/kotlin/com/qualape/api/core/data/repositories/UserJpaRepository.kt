package com.qualape.api.core.data.repositories

import com.qualape.api.core.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJpaRepository: JpaRepository<User, String>
