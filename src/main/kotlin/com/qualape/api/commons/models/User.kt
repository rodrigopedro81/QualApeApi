package com.qualape.api.commons.models

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
    val foodIds: ArrayList<Long>,
    @Column(nullable = true)
    val productIds: ArrayList<Long>,
    @Column(nullable = true)
    val jobIds: ArrayList<Long>,
) {
    companion object {
        fun createNewUser(email: String, password: String, name: String): User {
            return User(
                email = email,
                password = password,
                name = name,
                foodIds = arrayListOf(),
                productIds = arrayListOf(),
                jobIds = arrayListOf(),
            )
        }
    }
}
