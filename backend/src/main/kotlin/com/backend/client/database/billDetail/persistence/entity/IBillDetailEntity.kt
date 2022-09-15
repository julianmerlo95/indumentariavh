package com.backend.client.database.billDetail.persistence.entity

import com.backend.domain.dto.billDetail.BillDetailDto
import java.time.LocalDateTime

interface IBillDetailEntity {
    val idBill: String
    val quantity: Int
    val price: String
    val idProduct: String
    val colour: String
    val waist: String
    val dateRegister: LocalDateTime
    fun toDto(): BillDetailDto?
}