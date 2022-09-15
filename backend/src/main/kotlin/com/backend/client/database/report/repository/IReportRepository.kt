package com.backend.client.database.report.repository

import com.backend.client.database.report.persistence.entity.IReportEntity

interface IReportRepository {

    suspend fun findByIdReport(idReport: Long?): IReportEntity?
}