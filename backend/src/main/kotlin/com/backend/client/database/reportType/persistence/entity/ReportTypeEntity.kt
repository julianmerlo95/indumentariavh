package com.backend.client.database.reportType.persistence.entity

import com.backend.client.database.userRole.UserRoleTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ReportTypeEntity(id: EntityID<Int>) : Entity<Int>(id), IReportTypeEntity {
    companion object : EntityClass<Int, ReportTypeEntity>(UserRoleTable)

    override val idReportType = id.value
    override var name by UserRoleTable.name
}