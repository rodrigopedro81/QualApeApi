package com.qualape.api.core.services

import com.qualape.api.core.models.Job
import com.qualape.api.core.data.interactors.UserInteractor
import com.qualape.api.core.data.repositories.JobJpaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class JobService @Autowired constructor(
    val jobJpaRepository: JobJpaRepository,
    val userInteractor: UserInteractor
) {

    fun retrieveAllJobs(): List<Job> {
        return jobJpaRepository.findAll()
    }

    fun saveJobInDatabase(job: Job, email: String): Job {
        userInteractor.updateUserByEmail(email) {
            it.apply { jobIds.add(job.id) }
        }
        return jobJpaRepository.save(job)
    }
}
