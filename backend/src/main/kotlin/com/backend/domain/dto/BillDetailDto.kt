package com.backend.domain.dto.billDetail

import java.time.LocalDateTime
import kotlinx.serialization.Serializable

data class BillDetailDto(
    val idBill: String,
    val quantity: Int,
    val price: String,
    val idProduct: String,
    val colour: String,
    val waist: String,
    val dateRegister: LocalDateTime
)

@Serializable
data class BillDetailSerializable(
    val idBill: Int,
    val quantity: Int,
    val price: String,
    val idProduct: String,
    val colour: String,
    val waist: String
)