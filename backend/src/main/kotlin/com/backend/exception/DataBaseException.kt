package com.backend.exception

import io.ktor.http.*

class DataBaseException(
    override val message: String,
    override val EXCEPTION_CODE: String = "UNPROCESSABLE_ENTITY",
    override val EXCEPTION_STATUS: HttpStatusCode = HttpStatusCode.UnprocessableEntity,
    override val cause: Throwable? = null
) : BusinessException(message, cause)