package com.qualape.api.core.controllers

import com.qualape.api.core.models.Job
import com.qualape.api.core.services.AuthenticationService
import com.qualape.api.core.services.JobService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/job")
class JobController(
    val jobService: JobService,
    val authenticationService: AuthenticationService
) {

    @PostMapping("/new")
    fun saveJob(
        @RequestBody @Valid job: Job,
        @RequestParam userToken: UUID
    ): Job {
        return authenticationService.ifValidSessionExists(userToken) { session ->
            jobService.saveJobInDatabase(job, session)
        }
    }

    @GetMapping("")
    fun getAllJobs() : List<Job> = jobService.retrieveAllJobs()
}