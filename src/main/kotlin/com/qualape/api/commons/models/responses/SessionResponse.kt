package com.qualape.api.commons.models.responses

import java.time.LocalDate
import java.util.UUID

data class SessionResponse(
    val token: UUID,
    val lastValidDay: LocalDate
)
