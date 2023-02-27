package com.qualape.api.core.services

import com.qualape.api.core.repositories.JobRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.qualape.api.core.models.Job
import com.qualape.api.core.models.Session
import com.qualape.api.core.repositories.UserRepository

@Service
class JobService @Autowired constructor(
    val jobRepository: JobRepository,
    val userRepository: UserRepository
) {

    fun retrieveAllJobs(): List<Job> {
        return jobRepository.findAll()
    }

    fun saveJobInDatabase(job: Job, session: Session): Job {
        return jobRepository.save(job)
    }
}
