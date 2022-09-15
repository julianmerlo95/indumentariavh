package com.backend.plugins

import com.backend.controller.*
import io.ktor.server.routing.*
import io.ktor.server.locations.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    install(Locations) {
    }

    routing {
        client()
        user()
        report()
        product()
        augmentedReality()
        bill()
        healthCheckController()
        currentAccountClient()
        expenses()
    }
}