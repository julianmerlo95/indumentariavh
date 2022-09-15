package com.backend.service

import com.backend.client.database.client.repository.ClientRepository
import com.backend.client.database.report.repository.ReportRepository
import com.backend.domain.reponses.ClientByIdResponse
import com.backend.domain.reponses.ReportByIdResponse
import kotlinx.coroutines.runBlocking

class ReportService(
    private val reportRepository: ReportRepository
) : IReportService {

    override fun findByIdReport(idReport: Long?): ReportByIdResponse? {
        val reportResponse = ReportByIdResponse(null, null)

        return runBlocking {
            try {
                val report = reportRepository.findByIdReport(idReport)

                if (report == null) {
                    reportResponse.error = "The report does not exist"
                    return@runBlocking reportResponse
                }

                reportResponse.report = report?.toDto()
                return@runBlocking reportResponse
            } catch (ex: Exception) {
                print("ReportService |findReportById| ERROR: $ex")
                reportResponse.error = ex.message
                return@runBlocking reportResponse
            }
        }
    }
}