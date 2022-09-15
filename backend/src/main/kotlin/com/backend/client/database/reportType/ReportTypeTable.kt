package com.backend.client.database.reportType

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object ReportTypeTable : IdTable<Int>("report_type") {
    val reportTypeId = com.backend.client.database.userRole.UserRoleTable.integer("id_type")
    override val id: Column<EntityID<Int>> = reportTypeId.entityId()

    val name = ReportTypeTable.varchar("name", 150)
}