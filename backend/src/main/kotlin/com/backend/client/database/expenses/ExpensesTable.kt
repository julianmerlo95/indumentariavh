package com.backend.client.database.expenses

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.javatime.datetime

object ExpensesTable : IdTable<Int>("expenses") {

    val idExpensesTable = ExpensesTable.integer("id_expenses")
    override val id: Column<EntityID<Int>> = idExpensesTable.entityId()

    val createdBillDate = datetime("create_bill_date")
    val amount = ExpensesTable.varchar("amount", 250)
    val idExpenseType = ExpensesTable.integer("id_expense_type")
    val expenseName = ExpensesTable.varchar("expense_name", 250)
    val idUser = ExpensesTable.integer("id_user")
    val dateRegister = datetime("date_register")
}