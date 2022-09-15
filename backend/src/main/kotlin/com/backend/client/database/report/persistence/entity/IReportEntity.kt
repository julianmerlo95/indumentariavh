package com.backend.client.database.report.persistence.entity

import com.backend.domain.dto.ReportDto
import java.time.LocalDateTime

interface IReportEntity {
    val idReport: Int
    val from: LocalDateTime
    val to: LocalDateTime
    val createdReportDate: LocalDateTime
    val fieldsReport: String
    val format: String
    val idReportType: Int
    val idProduct: String
    val idClient: Float
    val idBill: String
    val dateRegister: LocalDateTime
    fun toDto(): ReportDto?
}