package com.backend.exception

import io.ktor.http.*

class NotFoundException(
    override val message: String,
    override val EXCEPTION_CODE: String = "NOT_FOUND",
    override val EXCEPTION_STATUS: HttpStatusCode = HttpStatusCode.NotFound
) : BusinessException(message)