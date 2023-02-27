package com.qualape.api.core.controllers

import com.qualape.api.core.models.Session
import com.qualape.api.core.services.AuthenticationService
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
class LoginController(
    val authenticationService: AuthenticationService
) {

    @PostMapping("/login")
    fun login(
        @RequestParam email: String,
        @RequestParam password: String,
    ): Session = authenticationService.loginUser(email, password)

    @PostMapping("/register")
    fun register(
        @RequestParam email: String,
        @RequestParam password: String,
        @RequestParam name: String,
    ): Session = authenticationService.registerNewUser(email, password, name)
}