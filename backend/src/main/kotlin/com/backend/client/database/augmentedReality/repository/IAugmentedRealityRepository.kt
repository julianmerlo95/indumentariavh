package com.backend.client.database.augmentedReality.repository

import com.backend.client.database.augmentedReality.persistence.entity.IAugmentedRealityEntity
import com.backend.domain.dto.augmentedReality.AugmentedRealityDto

interface IAugmentedRealityRepository {

    suspend fun createAugmentedRealityByProduct(idAugmentedReality: AugmentedRealityDto): String?
    suspend fun findByIdAugmentedReality(idAugmentedReality: Long?): IAugmentedRealityEntity?
    suspend fun findByIdProductAugmentedReality(idAugmentedReality: String?): List<IAugmentedRealityEntity>?
}