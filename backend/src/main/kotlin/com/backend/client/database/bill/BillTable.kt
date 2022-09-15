package com.backend.client.database.bill

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNotNull
import org.jetbrains.exposed.sql.javatime.datetime

object BillTable : IdTable<String>("bill") {

    val idBill = BillTable.varchar("id_bill", 250)
    override val id: Column<EntityID<String>> = idBill.entityId()

    val createdBillDate = datetime("create_bill_date")
    val totalAmount = BillTable.varchar("total_amount", 250)
    val totalDiscount = BillTable.varchar("total_discount", 250).default("0")
    val cash = BillTable.varchar("cash", 250)
    val credit = BillTable.varchar("credit", 250)
    val borrowedCash = BillTable.varchar("borrowed_cash", 250)
    val idBillType = BillTable.integer("id_bill_type").default(2)
    val idUser = BillTable.integer("id_user").default(2)
    val idClient = BillTable.integer("id_client").default(1)
    val idBillStatus = BillTable.integer("id_bill_status").default(1)
    val dateRegister = datetime("date_register")
    val idPaymentMethod = BillTable.integer("id_payment_method").default(1)
}