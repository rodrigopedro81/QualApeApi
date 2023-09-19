package com.qualape.api.core.data.repositories

import com.qualape.api.commons.models.Session
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.UUID
import java.util.Optional

@Repository
interface SessionJpaRepository: JpaRepository<Session, UUID> {
    fun findByUserEmail(userEmail: String): Optional<Session>
    fun deleteByUserEmail(userEmail: String)
    fun findByLastValidDay(lastValidDay: LocalDate): List<Session>
    fun existsByUserEmail(userEmail: String): Boolean
}