package com.backend

import com.backend.client.database.DatabaseClient
import com.backend.module.ModuleLoader
import com.backend.plugings.configureSerialization
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.backend.plugins.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.CORS

fun main() {
    DatabaseClient.initDB()

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(CORS) {
            allowMethod(HttpMethod.Options)
            allowMethod(HttpMethod.Post)
            allowMethod(HttpMethod.Put)
            allowMethod(HttpMethod.Get)
            allowHeader(HttpHeaders.AccessControlAllowOrigin)
            allowHeader(HttpHeaders.ContentType)
            anyHost()
        }
        ModuleLoader.init()
        configureSecurity()
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}

