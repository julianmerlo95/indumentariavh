package com.backend.client.database.augmentedReality.persistence.entity

import com.backend.domain.dto.augmentedReality.AugmentedRealityDto
import java.time.LocalDateTime

interface IAugmentedRealityEntity {
    val idAugmentedReality: Int
    val imageUrl: String
    val idProduct: String
    val dateRegister: LocalDateTime
    fun toDto(): AugmentedRealityDto?
}