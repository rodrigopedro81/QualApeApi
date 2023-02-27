package com.qualape.api.core.models

import jakarta.persistence.*

@Entity
@Table(name = "TB_FOOD")
data class Food(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val category: String,
)
