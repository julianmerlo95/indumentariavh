package com.backend.client.database.expenses.persistence.entity

import com.backend.client.database.expenses.ExpensesTable
import com.backend.domain.dto.ExpenseDto
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ExpensesEntity(id: EntityID<Int>) : Entity<Int>(id), IExpensesEntity {
    companion object : EntityClass<Int, ExpensesEntity>(ExpensesTable)

    override var createdBillDate by ExpensesTable.createdBillDate
    override var amount by ExpensesTable.amount
    override var idExpenseType by ExpensesTable.idExpenseType
    override var expenseName by ExpensesTable.expenseName
    override var idUser by ExpensesTable.idUser
    override var dateRegister by ExpensesTable.dateRegister

    override fun toDto(): ExpenseDto {
        val extenseDto = ExpenseDto(
            createdBillDate,
            amount,
            idExpenseType,
            expenseName,
            idUser,
            dateRegister
        )
        return extenseDto
    }
}