package com.qualape.api.services

import com.qualape.api.repositories.JobRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.qualape.api.models.Job

@Service
class JobService @Autowired constructor(val jobRepository: JobRepository) {

    fun retrieveAllJobs(): List<Job> {
        return jobRepository.findAll()
    }

    fun saveNewJob(job: Job): Job {
        return jobRepository.save(job)
    }
}
