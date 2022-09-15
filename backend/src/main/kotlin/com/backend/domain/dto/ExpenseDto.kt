package com.backend.domain.dto

import java.time.LocalDateTime

data class ExpenseDto (
    val createdBillDate: LocalDateTime,
    val amount: String,
    val idExpenseType: Int,
    val expenseName: String,
    val idUser: Int,
    val dateRegister: LocalDateTime,
)