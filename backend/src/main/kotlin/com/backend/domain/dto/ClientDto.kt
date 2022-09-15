package com.backend.domain.dto

import java.time.LocalDateTime

data class ClientDto(
    val idClient: String,
    val name: String,
    val lastName: String,
    val dateOfBirth: String,
    val dni: String,
    val mail: String,
    val dateRegister: LocalDateTime,
    val isEnable: Int,
    val moneyDebt: String
)