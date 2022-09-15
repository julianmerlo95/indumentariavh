package com.backend.client.database.billType

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNotNull

object BillTypeTable : IdTable<Int>("bill_type") {
    val idBillType = BillTypeTable.integer("id_type")
    override val id: Column<EntityID<Int>> = idBillType.entityId()

    val name = BillTypeTable.varchar("name", 100)
}