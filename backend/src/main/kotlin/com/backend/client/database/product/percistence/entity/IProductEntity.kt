package com.backend.client.database.product.percistence.entity

import com.backend.domain.dto.product.ProductDto
import java.time.LocalDateTime

interface IProductEntity {
    val idProduct: String
    var name: String
    var description: String
    var colour: String
    var waist: String
    var quantity: Int
    var purchasePrice: String
    var salePrice: String
    var isAugmentedReality: Int
    var isEnable: Int
    val dateRegister: LocalDateTime
    fun toDto(): ProductDto?
}