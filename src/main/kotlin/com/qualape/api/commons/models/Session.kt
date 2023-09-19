package com.qualape.api.commons.models

import com.qualape.api.commons.models.responses.SessionResponse
import jakarta.persistence.*
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "TB_SESSION")
data class Session(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val token: UUID,
    @Column(nullable = false)
    val userEmail: String,
    @Column(nullable = false)
    val date: LocalDate = LocalDate.now(),
    @Column(nullable = false)
    val lastValidDay: LocalDate = date.plusDays(7)
) {
    fun isValid(): Boolean = lastValidDay >= LocalDate.now()

    fun toSessionResponse() = SessionResponse(token, lastValidDay)
}
