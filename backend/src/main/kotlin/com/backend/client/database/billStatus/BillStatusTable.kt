package com.backend.client.database.billStatus

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNotNull

object BillStatusTable : IdTable<Int>("bill_status") {
    val idBillStatus = BillStatusTable.integer("id_status")
    override val id: Column<EntityID<Int>> = idBillStatus.entityId()

    val name = BillStatusTable.varchar("name", 100)
}