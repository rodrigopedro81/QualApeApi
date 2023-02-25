package com.qualape.api.models

import jakarta.persistence.*

@Entity
@Table(name = "TB_USER")
data class User(
    @Id
    @Column(nullable = false)
    val email: String,
    @Column(nullable = false)
    val password: String,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = true)
    val foodIds: List<Long>,
    @Column(nullable = true)
    val productIds: List<Long>,
    @Column(nullable = true)
    val jobIds: List<Long>,
) {
    companion object {
        fun createNewUser(email: String, password: String, name: String): User {
            return User(
                email = email,
                password = password,
                name = name,
                foodIds = listOf(),
                productIds = listOf(),
                jobIds = listOf(),
            )
        }
    }
}
