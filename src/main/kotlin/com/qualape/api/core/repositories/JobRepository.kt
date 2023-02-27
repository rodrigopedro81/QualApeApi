package com.qualape.api.core.repositories

import com.qualape.api.core.models.Job
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JobRepository: JpaRepository<Job, Long>