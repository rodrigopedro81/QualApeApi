package com.qualape.api.models

import jakarta.persistence.*
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "TB_SESSION")
data class Session(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val token: UUID? = null,
    @Column(nullable = false)
    val userId: String,
    @Column(nullable = false)
    val date: LocalDate = LocalDate.now(),
    @Column(nullable = false)
    val lastValidDay: LocalDate = date.plusDays(7)
)
