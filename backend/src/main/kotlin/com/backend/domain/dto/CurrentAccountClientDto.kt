package com.backend.domain.dto

import java.time.LocalDateTime

data class CurrentAccountClientDto(
    val idClient: String,
    val actualMoneyDebt: String,
    val moneyCredit: String,
    val moneyDebit: String,
    val newMoneyDebt: String,
    val dateRegister: LocalDateTime,
    val userName: String,
    val createdBillDate: LocalDateTime
)