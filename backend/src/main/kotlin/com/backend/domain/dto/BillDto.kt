package com.backend.domain.dto.bill

import java.time.LocalDateTime

data class BillDto (
    val idBill: String,
    val createdBillDate: LocalDateTime,
    val totalAmount: String,
    val totalDiscount: String,
    val cash: String,
    val credit: String,
    val borrowedCash: String,
    val idBillType: Int,
    val idUser: Int,
    val idClient: Int,
    val idBillStatus: Int,
    val dateRegister: LocalDateTime,
    val idPaymentMethod: Int
)