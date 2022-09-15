package com.backend.client.database.product.repository

import com.backend.client.database.product.ProductTable
import com.backend.client.database.product.percistence.entity.IProductEntity
import com.backend.client.database.product.percistence.entity.ProductEntity
import com.backend.domain.dto.product.ProductDto
import com.backend.exception.DataBaseException
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greaterEq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

class ProductRepository : IProductRepository {

    override suspend fun createProduct(productDto: ProductDto): String? {
        return transaction {
            try {
                ProductEntity.new(productDto.idProduct) {
                    name = productDto.name
                    description = productDto.description
                    colour = productDto.colour
                    waist = productDto.waist
                    quantity = productDto.quantity
                    purchasePrice = productDto.purchasePrice
                    salePrice = productDto.salePrice
                    isAugmentedReality = productDto.isAugmentedReality
                    isEnable = productDto.isEnable
                    dateRegister = productDto.dateRegister
                }
                return@transaction "The product ${productDto.idProduct} was save correctly in database"
            } catch (ex: Exception) {
                return@transaction "ClientRepository PERSISTENCE ERROR: The product ${productDto.idProduct} was error to saved: ${ex.message}"
            }
        }
    }

    override suspend fun updateProduct(productEntity: IProductEntity, productDto: ProductDto): String? {
        return transaction {
            productEntity.name = productDto.name
            productEntity.description = productDto.description
            productEntity.colour = productDto.colour
            productEntity.waist = productDto.waist
            productEntity.quantity = productDto.quantity
            productEntity.purchasePrice = productDto.purchasePrice
            productEntity.salePrice = productDto.salePrice
            productEntity.isAugmentedReality = productDto.isAugmentedReality
            productEntity.isEnable = productDto.isEnable
            return@transaction "The client ${productEntity.idProduct} was updated correctly in database"
        }
    }

    override suspend fun deleteProduct(productEntity: IProductEntity): String? {
        return transaction {
            productEntity.isEnable = if (productEntity.isEnable == 1) 0 else 1
            return@transaction "The product ${productEntity.idProduct} was disable correctly in database"
        }
    }

    override suspend fun findByIdProduct(idProduct: String?): IProductEntity? {
        return transaction {
            try {
                if (idProduct != null) {
                    return@transaction ProductEntity.findById(idProduct)
                }
                return@transaction null
            } catch (ex: Exception) {
                throw DataBaseException(message = "ProductRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}", cause = ex)
            }
        }
    }

    override suspend fun findAllProductsByDateRegister(): List<IProductEntity> {
        return transaction {
            try {
                return@transaction ProductEntity.find {
                    (ProductTable.dateRegister greaterEq "2022-08-01T00:00:00")
                }.orderBy(ProductTable.dateRegister to SortOrder.DESC).toList()
            } catch (ex: Exception) {
                throw DataBaseException(
                    message = "ProductRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}",
                    cause = ex
                )
            }
        }
    }

    override suspend fun findFirstProductsByDateRegister(): List<IProductEntity> {
        return transaction {
            try {
                return@transaction ProductEntity.find {
                    (ProductTable.dateRegister greaterEq "2022-08-01T00:00:00")
                }.orderBy(ProductTable.dateRegister to SortOrder.DESC).limit(1).toList()
            } catch (ex: Exception) {
                throw DataBaseException(
                    message = "ProductRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}",
                    cause = ex
                )
            }
        }
    }

    override suspend fun findProductByParam(key: String, param: String?): List<IProductEntity>? {
        var query: Op<Boolean> = (ProductTable.dateRegister greaterEq "2022-08-01T00:00:00")

        return transaction {
            try {
                if (param != null) {
                    if (key == "name") {
                        query = query and (ProductTable.name eq param)
                    } else if (key == "description") {
                        query = query and (ProductTable.description eq param)
                    } else if (key == "colour") {
                        query = query and (ProductTable.colour eq param)
                    } else if (key == "waist") {
                        query = query and (ProductTable.waist eq param)
                    } else if (key == "isEnable") {
                        query = query and (ProductTable.isEnable eq param.toInt())
                    } else {
                        return@transaction null
                    }
                }

                return@transaction ProductEntity.find { query }.orderBy(ProductTable.dateRegister to SortOrder.DESC)
                    .toList()

            } catch (ex: Exception) {
                throw DataBaseException(
                    message = "ProductRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}",
                    cause = ex
                )
            }
        }
    }
}