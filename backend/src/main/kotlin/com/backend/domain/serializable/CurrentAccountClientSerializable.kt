package com.backend.domain.serializable

import kotlinx.serialization.Serializable

@Serializable
data class CurrentAccountClientCreateSerializable(
    val currentAccountClient: CurrentAccountClientCreateListSerializable
)

@Serializable
data class CurrentAccountClientSerializable (
    val idClient: String?,
    val actualMoneyDebt: String?,
    val moneyCredit: String?,
    val moneyDebit: String?,
    val newMoneyDebt: String?,
    val dateRegister: String,
    val userName: String?
)

@Serializable
data class CurrentAccountClientCreateListSerializable (
    val idClient: String?,
    val actualMoneyDebt: String?,
    val moneyCredit: String?,
    val moneyDebit: String?,
    val newMoneyDebt: String?,
    val userName: String
)

@Serializable
data class CurrentAccountClientErrorSerializable(
    val error: String?
)

@Serializable
data class CurrentAccountClientSuccessSerializable(
    val currentAccountClient: String?
)