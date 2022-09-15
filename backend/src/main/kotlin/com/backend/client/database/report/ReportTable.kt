package com.backend.client.database.report

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNotNull
import org.jetbrains.exposed.sql.javatime.datetime

object ReportTable : IdTable<Int>("report") {
    val idReport = integer("id_report")
    override val id: Column<EntityID<Int>> = idReport.entityId()

    val from = datetime("form")
    val to = datetime("to")
    val createdReportDate = datetime("created_report_date")
    val fieldsReport = varchar("fields", 250)
    val format = varchar("format", 250)
    val idReportType = integer("id_report_type")
    val idProduct = varchar("id_product", 250)
    val idClient = float("id_client")
    val idBill = varchar("id_bill", 255)
    val dateRegister = datetime("date_register")
}