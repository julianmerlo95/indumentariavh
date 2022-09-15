package com.backend.domain.serializable

import kotlinx.serialization.Serializable

@Serializable
data class ExpensesCreateSerializable(
    val expense: ExpenseSerializable
)

@Serializable
data class ExpenseSerializable(
    val idUser: Int?,
    val idExpenseType: Int?,
    val expenseName: String?,
    val amount: String?,
    val createdBillDate: String,
    val dateRegister: String
)

@Serializable
data class ExpensesErrorSerializable(
    val error: String?
)

@Serializable
data class ExpensesSuccessSerializable(
    val expense: String?
)