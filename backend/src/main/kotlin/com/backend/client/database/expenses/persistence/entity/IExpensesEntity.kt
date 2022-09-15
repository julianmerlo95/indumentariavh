package com.backend.client.database.expenses.persistence.entity

import com.backend.domain.dto.ExpenseDto
import java.time.LocalDateTime

interface IExpensesEntity {
    val amount: String
    val idUser: Int
    val expenseName: String
    val idExpenseType: Int
    val createdBillDate: LocalDateTime
    val dateRegister: LocalDateTime
    fun toDto(): ExpenseDto
}

