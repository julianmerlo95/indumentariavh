package com.backend.client.database.currentAccountClient.persistence.entity

import com.backend.domain.dto.CurrentAccountClientDto
import java.time.LocalDateTime

interface ICurrentAccountClientEntity {
    var idClient: String
    var actualMoneyDebt: String
    var moneyCredit: String
    var moneyDebit: String
    var newMoneyDebt: String
    var dateRegister: LocalDateTime
    var userName: String
    val createdBillDate: LocalDateTime
    fun toDto(): CurrentAccountClientDto?
}