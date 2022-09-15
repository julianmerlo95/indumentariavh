package com.backend.client.database.currentAccountClient

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.javatime.datetime

object CurrentAccountClientTable : IdTable<Int>("current_account_client") {

    val idCurrentAccountClientTable = CurrentAccountClientTable.integer("id_current_account_client")
    override val id: Column<EntityID<Int>> = idCurrentAccountClientTable.entityId()

    val idClient = CurrentAccountClientTable.varchar("id_client", 250)
    val actualMoneyDebt = CurrentAccountClientTable.varchar("actual_money_debt", 250)
    val moneyCredit = CurrentAccountClientTable.varchar("money_credit", 250)
    val moneyDebit = CurrentAccountClientTable.varchar("money_debit", 250)
    val newMoneyDebt = CurrentAccountClientTable.varchar("new_money_debt", 250)
    val dateRegister = datetime("date_register")
    val userName = CurrentAccountClientTable.varchar("user_name", 250)
    val createdBillDate = datetime("create_bill_date")
}