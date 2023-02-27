package com.qualape.api.core.data.repositories

import com.qualape.api.core.models.Job
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JobJpaRepository: JpaRepository<Job, Long>