package com.backend.client.database.expenses.repository

import com.backend.client.database.expenses.persistence.entity.IExpensesEntity
import com.backend.domain.dto.ExpenseDto

interface IExpensesRepository {

    suspend fun createExpense(expensesDto: ExpenseDto): String?
    suspend fun findAllExpensesByDateRegister(): List<IExpensesEntity>
    suspend fun findByParam(key: String, param: String): List<IExpensesEntity>?
}