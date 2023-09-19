package com.qualape.api.commons.ktx

import org.springframework.data.jpa.repository.JpaRepository
import java.lang.Exception
import java.util.*

fun<T, R : Any> JpaRepository<T, R>.getByIdOrThrow(id: R, exception: Exception): T {
    val request = findById(id)
    if (request.isPresent) return request.get() else throw exception
}

fun<T, R> JpaRepository<T, R>.getByCustomOrThrow(get: () -> Optional<T>, exception: Exception): T {
    val request = get()
    if (request.isPresent) return request.get() else throw exception
}

fun <T, X>withRequest(request: Optional<T>, ifExists: (T) -> X, ifDoesNotExist: () -> X): X {
    return if (request.isPresent) {
        ifExists.invoke(request.get())
    } else {
        ifDoesNotExist.invoke()
    }
}
