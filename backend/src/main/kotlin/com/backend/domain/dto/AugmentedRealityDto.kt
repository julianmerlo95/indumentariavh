package com.backend.domain.dto.augmentedReality

import java.time.LocalDateTime
import kotlinx.serialization.Serializable

data class AugmentedRealityDto(
    val idAugmentedReality: String,
    val imageUrl: String,
    val idProduct: String,
    val dateRegister: LocalDateTime
)

@Serializable
data class AugmentedRealitySerializable(
    val idAugmentedReality: String,
    val imageUrl: String,
    val idProduct: String
)