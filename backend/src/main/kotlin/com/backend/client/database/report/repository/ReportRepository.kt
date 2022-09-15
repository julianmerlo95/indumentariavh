package com.backend.client.database.report.repository

import com.backend.client.database.report.persistence.entity.IReportEntity
import com.backend.client.database.report.persistence.entity.ReportEntity
import com.backend.exception.DataBaseException
import org.jetbrains.exposed.sql.transactions.transaction

class ReportRepository : IReportRepository {

    override suspend fun findByIdReport(idReport: Long?): IReportEntity? {
        return transaction {
            try {
                if (idReport != null) {
                    return@transaction ReportEntity.findById(idReport.toInt())
                }
                return@transaction null
            } catch (ex: Exception) {
                throw DataBaseException(message = "ReportRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}", cause = ex)
            }
        }
    }

}