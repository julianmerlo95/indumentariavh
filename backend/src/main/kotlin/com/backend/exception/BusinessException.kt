package com.backend.exception

import io.ktor.http.*

abstract class BusinessException(
    override val message: String,
    override val cause: Throwable? = null
) : RuntimeException(message, cause) {
    abstract val EXCEPTION_CODE: String
    abstract val EXCEPTION_STATUS: HttpStatusCode
}