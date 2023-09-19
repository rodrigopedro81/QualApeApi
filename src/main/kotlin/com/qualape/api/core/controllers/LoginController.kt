package com.qualape.api.core.controllers

import com.qualape.api.commons.models.Session
import com.qualape.api.commons.models.responses.SessionResponse
import com.qualape.api.core.services.AuthenticationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class LoginController(
    val authenticationService: AuthenticationService
) {

    @PostMapping("/v1/login")
    fun login(
        @RequestParam email: String,
        @RequestParam password: String
    ): SessionResponse = authenticationService.loginUser(email, password).toSessionResponse()

    @PostMapping("/v1/register")
    fun register(
        @RequestParam email: String,
        @RequestParam password: String,
        @RequestParam name: String
    ): SessionResponse = authenticationService.registerNewUser(email, password, name).toSessionResponse()
}
