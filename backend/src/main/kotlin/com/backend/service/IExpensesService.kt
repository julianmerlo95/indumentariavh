package com.backend.service

import com.backend.domain.dto.ExpenseDto
import com.backend.domain.reponses.ExpenseByIdResponse
import com.backend.domain.serializable.ExpensesCreateSerializable

interface IExpensesService {

    suspend fun createExpense(expenseCreateSerializable: ExpensesCreateSerializable): String?
    suspend fun findByParam(key: String, idUser: String?): ExpenseByIdResponse?
    suspend fun findAllExpenseByDateRegister(): MutableList<ExpenseDto?>?
}