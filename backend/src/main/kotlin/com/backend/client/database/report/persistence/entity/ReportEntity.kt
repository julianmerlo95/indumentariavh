package com.backend.client.database.report.persistence.entity

import com.backend.client.database.report.ReportTable
import com.backend.domain.dto.ReportDto
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ReportEntity(id: EntityID<Int>) : Entity<Int>(id), IReportEntity {
    companion object : EntityClass<Int, ReportEntity>(ReportTable)

    override val idReport = id.value
    override val from by ReportTable.from
    override val to by ReportTable.to
    override val createdReportDate by ReportTable.createdReportDate
    override val fieldsReport by ReportTable.fieldsReport
    override val format by ReportTable.format
    override val idReportType by ReportTable.idReportType
    override val idProduct by ReportTable.idProduct
    override val idClient by ReportTable.idClient
    override val idBill by ReportTable.idBill
    override val dateRegister by ReportTable.dateRegister

    override fun toDto(): ReportDto? {
        val reportDto = ReportDto(
            idReport.toString(),
            from,
            to,
            createdReportDate,
            fieldsReport,
            format,
            idReportType,
            idProduct,
            idClient,
            idBill,
            dateRegister
        )
        return reportDto
    }
}