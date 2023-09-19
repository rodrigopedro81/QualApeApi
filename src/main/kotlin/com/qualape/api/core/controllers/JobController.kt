package com.qualape.api.core.controllers

import com.qualape.api.commons.models.Job
import com.qualape.api.core.services.AuthenticationService
import com.qualape.api.core.services.JobService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/job")
class JobController(
    val jobService: JobService,
    val authenticationService: AuthenticationService
) {

    @PostMapping("/v1/new")
    fun saveJob(
        @RequestBody @Valid job: Job,
        @RequestParam userToken: UUID
    ): Job {
        return authenticationService.ifUserTokenIsValid(userToken) { session ->
            jobService.saveJobInDatabase(job, session.userEmail)
        }
    }

    @GetMapping("")
    fun getAllJobs(
        @RequestParam userToken: UUID
    ) : List<Job> = jobService.retrieveAllJobs()
}