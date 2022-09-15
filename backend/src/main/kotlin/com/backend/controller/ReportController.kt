package com.backend.controller

import com.backend.routings.GetByIdReport
import com.backend.service.ReportService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.ktor.ext.inject

fun Route.report() {

    val reportService by inject<ReportService>()

    get<GetByIdReport> {
        withContext(Dispatchers.IO) {
            try {
                val idReport = call.parameters["id"]?.toLong()
                val response = reportService.findByIdReport(idReport)
                call.respond(HttpStatusCode.OK, "$response")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

}