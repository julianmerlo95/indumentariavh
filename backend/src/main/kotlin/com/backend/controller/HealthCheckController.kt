package com.backend.controller

import com.backend.routings.GetHealthCheck
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.healthCheckController() {

    get<GetHealthCheck> {
        val response = "The application is running correctly"
        call.respond(response)
    }
}