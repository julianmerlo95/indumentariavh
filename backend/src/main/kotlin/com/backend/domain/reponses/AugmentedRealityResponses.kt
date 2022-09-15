package com.backend.domain.reponses

import com.backend.domain.dto.augmentedReality.AugmentedRealityDto

data class AugmentedRealityByIdResponses(
    var augmentedReality: AugmentedRealityDto?,
    var error: String?,
)

data class AugmentedRealityListResponses(
    var augmentedRealityList: MutableList<AugmentedRealityDto?>?,
    var error: String?,
)

data class AugmentedRealityStringResponses(
    var augmentedReality: String?,
    var error: String?,
)