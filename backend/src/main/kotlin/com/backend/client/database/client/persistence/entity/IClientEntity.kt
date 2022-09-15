package com.backend.client.database.client.persistence.entity

import com.backend.domain.dto.ClientDto
import java.time.LocalDateTime

interface IClientEntity {
    val idClient: Long
    var name: String
    var lastName: String
    var dateOfBirth: String
    var dni: String
    var mail: String
    var dateRegister: LocalDateTime
    var isEnable: Int
    var moneyDebt: String
    fun toDto(): ClientDto?
}