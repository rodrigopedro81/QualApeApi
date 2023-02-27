package com.qualape.api.core.data.repositories

import com.qualape.api.core.models.Session
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
interface SessionJpaRepository: JpaRepository<Session, UUID> {
    fun findByUserEmail(userEmail: String): Optional<Session>
    fun findByLastValidDay(lastValidDay: LocalDate): List<Session>
}