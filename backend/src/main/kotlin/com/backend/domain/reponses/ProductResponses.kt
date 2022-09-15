package com.backend.domain.reponses

import com.backend.domain.dto.product.ProductDto

data class ProductByIdResponses(
    var product: MutableList<ProductDto?>?,
    var error: String?,
)

data class ProductByParamResponse(
    var product: MutableList<ProductDto?>?,
    var error: String?,
)

data class ProductStringResponses(
    var product: String?,
    var error: String?,
)