package com.backend.domain.serializable

import kotlinx.serialization.Serializable

@Serializable
data class ProuctCreateSerializable(
    val product: ProductSerializable
)

@Serializable
data class ProductSerializable(
    val idProduct: String?,
    val name: String?,
    val description: String?,
    val colour: String?,
    val waist: String?,
    val quantity: Int?,
    val purchasePrice: String?,
    val salePrice: String?,
    val isAugmentedReality: Int?,
    val isEnable: Int?
)

@Serializable
data class ProductUpdateSerializable(
    val name: String? = null,
    val description: String? = null,
    val colour: String? = null,
    val waist: String? = null,
    val quantity: Int? = null,
    val purchasePrice: String? = null,
    val salePrice: String? = null,
    val isAugmentedReality: Int? = null,
    val isEnable: Int? = null
)

@Serializable
data class ProductErrorSerializable(
    val error: String?
)

@Serializable
data class ProductSuccessSerializable(
    val product: String?
)