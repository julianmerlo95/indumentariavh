package com.backend.client.database.product.repository

import com.backend.client.database.product.percistence.entity.IProductEntity
import com.backend.domain.dto.product.ProductDto

interface IProductRepository {

    suspend fun createProduct(productDto: ProductDto): String?
    suspend fun deleteProduct(productEntity: IProductEntity): String?
    suspend fun updateProduct(productEntity: IProductEntity, productDto: ProductDto): String?
    suspend fun findByIdProduct(idProduct: String?): IProductEntity?
    suspend fun findAllProductsByDateRegister(): List<IProductEntity>
    suspend fun findFirstProductsByDateRegister(): List<IProductEntity>
    suspend fun findProductByParam(key: String, param: String?): List<IProductEntity>?

}