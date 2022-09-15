package com.backend.service

import com.backend.domain.reponses.ProductByIdResponses
import com.backend.domain.reponses.ProductStringResponses
import com.backend.domain.dto.product.ProductDto
import com.backend.domain.reponses.ProductByParamResponse
import com.backend.domain.serializable.ProductUpdateSerializable
import com.backend.domain.serializable.ProuctCreateSerializable


interface IProductService {

    fun createProduct(rouctCreateSerializable: ProuctCreateSerializable): ProductStringResponses?
    fun updateProduct(idProduct: String?, productUpdateSerializable: ProductUpdateSerializable): ProductStringResponses?
    fun deleteProduct(idProduct: String?): ProductStringResponses?
    fun findByIdProduct(idProduct: String?): ProductByIdResponses?
    fun findAllProductsByDateRegister(): MutableList<ProductDto?>?
    fun findFirstProductsByDateRegister(): MutableList<ProductDto?>?
    fun findProductByParam(key: String, param: String?): ProductByParamResponse?
}