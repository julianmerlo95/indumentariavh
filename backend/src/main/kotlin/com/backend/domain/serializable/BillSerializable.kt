package com.backend.domain.serializable

import com.backend.domain.dto.billDetail.BillDetailSerializable
import kotlinx.serialization.Serializable

@Serializable
data class BillCreateSerializable(
    val bill: BillCreateListSerializable
)

@Serializable
data class BillSerializable (
    val idBill: String?,
    val totalAmount: String?,
    val totalDiscount: String?,
    val cash: String?,
    val credit: String?,
    val borrowedCash: String?,
    val idBillType: Int?,
    val idUser: Int?,
    val idBillStatus: Int?,
    val idClient: Int?,
    val idPaymentMethod: Int?,
    val dateRegister: String
    //val listBillDetail: MutableList<BillDetailSerializable>?
)

@Serializable
data class BillCreateListSerializable (
    val idBill: String?,
    val totalAmount: String?,
    val totalDiscount: String?,
    val cash: String?,
    val credit: String?,
    val borrowedCash: String?,
    val idBillType: Int?,
    val idUser: Int?,
    val idBillStatus: Int?,
    val idClient: Int?,
    val idPaymentMethod: Int?,
    val listBillDetail: MutableList<BillDetailSerializable>?
)

@Serializable
data class BillErrorSerializable(
    val error: String?
)

@Serializable
data class BillSuccessSerializable(
    val bill: String?
)