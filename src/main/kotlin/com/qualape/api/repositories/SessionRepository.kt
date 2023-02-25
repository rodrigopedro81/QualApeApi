package com.qualape.api.repositories

import com.qualape.api.models.Session
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
interface SessionRepository: JpaRepository<Session, Long> {
    fun findByUserId(userId: String): Optional<Session>
    fun findByLastValidDay(lastValidDay: LocalDate): List<Session>
}