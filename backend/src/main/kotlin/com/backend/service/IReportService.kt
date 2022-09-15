package com.backend.service

import com.backend.domain.reponses.ReportByIdResponse

interface IReportService {

    fun findByIdReport(idReport: Long?): ReportByIdResponse?
}