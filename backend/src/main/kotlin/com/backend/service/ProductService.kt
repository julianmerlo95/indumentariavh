package com.backend.service

import com.backend.client.database.product.percistence.entity.IProductEntity
import com.backend.client.database.product.repository.ProductRepository
import com.backend.domain.reponses.ProductByIdResponses
import com.backend.domain.reponses.ProductStringResponses
import com.backend.domain.dto.product.ProductDto
import com.backend.domain.reponses.ProductByParamResponse
import com.backend.domain.serializable.ProductUpdateSerializable
import com.backend.domain.serializable.ProuctCreateSerializable
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

class ProductService(
    private val productRepository: ProductRepository
) : IProductService {

    override fun createProduct(prouctCreateSerializable: ProuctCreateSerializable): ProductStringResponses? {
        val productResponse = ProductStringResponses(null, null)

        return runBlocking {
            try {
                val productDto = ProductDto(
                    idProduct = prouctCreateSerializable.product.idProduct!!,
                    name = prouctCreateSerializable.product.name!!,
                    description = prouctCreateSerializable.product.description!!,
                    colour = prouctCreateSerializable.product.colour!!,
                    waist = prouctCreateSerializable.product.waist!!,
                    quantity = prouctCreateSerializable.product.quantity!!,
                    purchasePrice = prouctCreateSerializable.product.purchasePrice!!,
                    salePrice = prouctCreateSerializable.product.salePrice!!,
                    isAugmentedReality = prouctCreateSerializable.product.isAugmentedReality!!,
                    isEnable = prouctCreateSerializable.product.isEnable!!,
                    dateRegister = LocalDateTime.now()
                )

                val response = async { productRepository.createProduct(productDto) }
                productResponse.product = response.await()

                return@runBlocking productResponse
            } catch (ex: Exception) {
                print("ClientService |saveClient| ERROR: $ex")
                productResponse.error = ex.message
                return@runBlocking productResponse
            }
        }
    }

    override fun updateProduct(idProduct: String?, productUpdateSerializable: ProductUpdateSerializable
    ): ProductStringResponses? {
        val productResponse = ProductStringResponses(null, null)

        return runBlocking {
            try {
                val productEntity = productRepository.findByIdProduct(idProduct)

                if (productEntity == null) {
                    productResponse.error = "The client does not exist"
                    return@runBlocking productResponse
                }

                val productDto = ProductDto(
                    idProduct = productEntity.idProduct,
                    name = if (productUpdateSerializable.name != null) productUpdateSerializable?.name else productEntity.name,
                    description = if (productUpdateSerializable.description != null) productUpdateSerializable.description else productEntity.description,
                    colour = if (productUpdateSerializable.colour != null) productUpdateSerializable.colour else productEntity.colour,
                    waist = if (productUpdateSerializable.waist != null) productUpdateSerializable.waist else productEntity.waist,
                    quantity = if (productUpdateSerializable.quantity != null) productUpdateSerializable.quantity else productEntity.quantity,
                    purchasePrice = if (productUpdateSerializable.purchasePrice != null) productUpdateSerializable.purchasePrice else productEntity.purchasePrice,
                    salePrice = if (productUpdateSerializable.salePrice != null) productUpdateSerializable.salePrice else productEntity.salePrice,
                    isAugmentedReality = if (productUpdateSerializable.isAugmentedReality != null) productUpdateSerializable.isAugmentedReality else productEntity.isAugmentedReality,
                    isEnable = if (productUpdateSerializable.isEnable != null) productUpdateSerializable.isEnable else productEntity.isEnable,
                    dateRegister = productEntity.dateRegister
                )

                productResponse.product = productRepository.updateProduct(productEntity, productDto)
                return@runBlocking productResponse
            } catch (ex: Exception) {
                print("ProductService |updateProduct| ERROR: $ex")
                productResponse.error = ex.message
                return@runBlocking productResponse
            }
        }
    }

    override fun deleteProduct(idProduct: String?): ProductStringResponses? {
        val productResponse = ProductStringResponses(null, null)

        return runBlocking {
            try {
                val productEntity = productRepository.findByIdProduct(idProduct)

                if (productEntity == null) {
                    productResponse.error = "The product does not exist"
                    return@runBlocking productResponse
                }

                productResponse.product = productRepository.deleteProduct(productEntity)
                return@runBlocking productResponse
            } catch (ex: Exception) {
                print("ProductService |deleteProduct| ERROR: $ex")
                productResponse.error = ex.message
                return@runBlocking productResponse
            }
        }
    }

    override fun findAllProductsByDateRegister(): MutableList<ProductDto?>? {
        return runBlocking {
            try {
                val productEntity = productRepository.findAllProductsByDateRegister()

                if (productEntity == null) {
                    return@runBlocking productEntity
                }

                val clientListDto = convertAllProductsToDto(productEntity)
                return@runBlocking clientListDto
            } catch (ex: Exception) {
                print("ProductService |findAllProductsByDateRegister| ERROR: $ex")
                return@runBlocking null
            }
        }
    }

    override fun findFirstProductsByDateRegister(): MutableList<ProductDto?>? {
        return runBlocking {
            try {
                val productEntity = productRepository.findFirstProductsByDateRegister()

                if (productEntity == null) {
                    return@runBlocking productEntity
                }

                val clientListDto = convertAllProductsToDto(productEntity)
                return@runBlocking clientListDto
            } catch (ex: Exception) {
                print("ProductService |findAllProductsByDateRegister| ERROR: $ex")
                return@runBlocking null
            }
        }
    }

    override fun findByIdProduct(idProduct: String?): ProductByIdResponses? {
        val productResponse = ProductByIdResponses(null, null)
        val productMutableList = mutableListOf<ProductDto?>()

        return runBlocking {
            try {
                val product = productRepository.findByIdProduct(idProduct)

                if (product == null) {
                    productResponse.error = "The product does not exist"
                    return@runBlocking productResponse
                }
                productMutableList.add(product?.toDto())
                productResponse.product = productMutableList
                return@runBlocking productResponse
            } catch (ex: Exception) {
                print("ProductService |findByIdProduct| ERROR: $ex")
                productResponse.error = ex.message
                return@runBlocking productResponse
            }
        }
    }

    override fun findProductByParam(key: String, param: String?): ProductByParamResponse? {
        val productResponse = ProductByParamResponse(null, null)

        return runBlocking {
            try {
                val productEntity = productRepository.findProductByParam(key, param.toString())

                if (productEntity == null || productEntity.isEmpty()) {
                    productResponse.error = "The $key: $param does not exist"
                    return@runBlocking productResponse
                }

                productResponse.product = convertAllProductsToDto(productEntity)
                return@runBlocking productResponse
            } catch (ex: Exception) {
                print("ClientService |findClientByParam| ERROR: $ex")
                productResponse.error = ex.message
                return@runBlocking productResponse
            }
        }
    }

    private fun convertAllProductsToDto(productEntity: List<IProductEntity>): MutableList<ProductDto?> {
        val productDtoList = mutableListOf<ProductDto?>()

        productEntity.forEach {
            productDtoList.add(it?.toDto())
        }

        return productDtoList
    }
}