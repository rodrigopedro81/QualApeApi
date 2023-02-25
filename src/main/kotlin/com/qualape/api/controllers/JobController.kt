package com.qualape.api.controllers

import com.qualape.api.models.Job
import com.qualape.api.services.JobService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/job")
class JobController(
    val jobService: JobService
) {

    @PostMapping("/new")
    fun saveJob(@RequestBody job: Job): Job {
        return jobService.saveNewJob(job)
    }

    @GetMapping("")
    fun getAllJobs() : List<Job> = jobService.retrieveAllJobs()
}