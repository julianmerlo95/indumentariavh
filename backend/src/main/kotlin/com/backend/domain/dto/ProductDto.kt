package com.backend.domain.dto.product

import java.time.LocalDateTime

data class ProductDto(
    val idProduct: String,
    val name: String,
    val description: String,
    val colour: String,
    val waist: String,
    val quantity: Int,
    val purchasePrice: String,
    val salePrice: String,
    val isAugmentedReality: Int,
    val isEnable: Int,
    val dateRegister: LocalDateTime
)
