package com.backend.service

import com.backend.domain.reponses.AugmentedRealityByIdResponses
import com.backend.domain.reponses.AugmentedRealityListResponses
import com.backend.domain.reponses.AugmentedRealityStringResponses
import com.backend.domain.dto.augmentedReality.AugmentedRealitySerializable

interface IAugmentedRealityService {

    suspend fun createAugmentedRealityByProduct(augmentedRealitySerializable: AugmentedRealitySerializable): AugmentedRealityStringResponses?
    fun findByIdAugmentedReality(idAugmentedReality: Long?): AugmentedRealityByIdResponses?
    fun findByIdProductAugmentedReality(idAugmentedReality: String?): AugmentedRealityListResponses?
}