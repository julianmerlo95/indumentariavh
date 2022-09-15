package com.backend.domain.dto

import java.time.LocalDateTime

data class ReportDto(
    val idReport: String,
    val from: LocalDateTime,
    val to: LocalDateTime,
    val createdReportDate: LocalDateTime,
    val fieldsReport: String,
    val format: String,
    val idReportType: Int,
    val idProduct: String,
    val idClient: Float,
    val idBill: String,
    val dateRegister: LocalDateTime
)