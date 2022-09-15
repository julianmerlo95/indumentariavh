package com.backend.client.database.billDetail

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNotNull
import org.jetbrains.exposed.sql.javatime.datetime

object BillDetailTable : IdTable<Int>("bill_detail") {
    val idBillDetail = BillDetailTable.integer("id_detail")
    override val id: Column<EntityID<Int>> = idBillDetail.entityId()

    val idBill = BillDetailTable.varchar("id_bill", 250)
    val quantity = BillDetailTable.integer("quantity")
    val price = BillDetailTable.varchar("price", 250)
    val idProduct = BillDetailTable.varchar("id_product", 250)
    val colour = BillDetailTable.varchar("colour", 100).default("no_colour")
    val waist = BillDetailTable.varchar("waist", 100).default("no_waist")
    val dateRegister = datetime("date_register")
}
