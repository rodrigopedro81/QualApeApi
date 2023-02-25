package com.qualape.api.repositories

import com.qualape.api.models.Job
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JobRepository: JpaRepository<Job, Long>