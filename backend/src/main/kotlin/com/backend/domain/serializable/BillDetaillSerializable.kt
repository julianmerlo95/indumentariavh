package com.backend.domain.serializable

import kotlinx.serialization.Serializable

@Serializable
data class BillDetaillSerializable (
    val idBill: String?,
    val quantity: Int?,
    val price: String?,
    val idProduct: String?,
    val colour: String?,
    val waist: String?,
    val dateRegister: String?
)