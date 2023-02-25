package com.qualape.api.controllers

import com.qualape.api.models.Session
import com.qualape.api.security.AuthenticationService
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