package com.backend.controller

import com.backend.domain.Constants
import com.backend.domain.dto.product.ProductDto
import com.backend.domain.serializable.*
import com.backend.routings.*
import com.backend.service.ProductService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.ktor.ext.inject

fun Route.product() {

    val productService by inject<ProductService>()

    route("/api/v1/products/product") {
        post {
            try {
                val productCreateSerializable = call.receive<ProuctCreateSerializable>()
                val response = productService.createProduct(productCreateSerializable)

                if (response?.product != null) {
                    val productSuccessSerializable = ProductSuccessSerializable(response.product)
                    call.respond(productSuccessSerializable)
                }

                val productErrorSerializable = ProductErrorSerializable(response?.error)
                call.respond(productErrorSerializable)
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    route("/api/v1/products/{id}") {
        put {
            try {
                val idProduct = call.parameters["id"]
                val productUpdateSerializable = call.receive<ProductUpdateSerializable>()
                val response = productService.updateProduct(idProduct, productUpdateSerializable)
                call.respond(HttpStatusCode.OK, "$response")
            } catch (ex: Exception) {
                throw ex
            }
        }

        delete {
            try {
                val idProduct = call.parameters["id"]
                val response = productService.deleteProduct(idProduct)
                call.respond(HttpStatusCode.OK, "$response")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetAllByRegisterDateProducts> {
        withContext(Dispatchers.IO) {
            try {
                val response = productService.findAllProductsByDateRegister()

                if (response != null) {
                    val responseSerializable = convertAllProductsToSerializable(response)
                    call.respond(responseSerializable)
                }

                call.respond(HttpStatusCode.OK, "${response}")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetFirstByRegisterDateProducts> {
        withContext(Dispatchers.IO) {
            try {
                val response = productService.findFirstProductsByDateRegister()

                if (response != null) {
                    val responseSerializable = convertAllProductsToSerializable(response)
                    call.respond(responseSerializable)
                }

                call.respond(HttpStatusCode.OK, "${response}")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByIdProduct> {
        withContext(Dispatchers.IO) {
            try {
                val idProduct = call.parameters["id"]
                val response = productService.findByIdProduct(idProduct)
                if (response != null) {
                    val responseSerializable = convertAllProductsToSerializable(response.product)
                    call.respond(responseSerializable)
                }

                val productErrorSerializable = ProductErrorSerializable(response?.error)
                call.respond(productErrorSerializable)
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByNameProduct> {
        withContext(Dispatchers.IO) {
            try {
                val productName = call.parameters["name"]
                val response = productService.findProductByParam(Constants.KEY_NAME, productName)

                if (response?.product != null) {
                    val responseSerializable = convertAllProductsToSerializable(response.product)
                    call.respond(responseSerializable)
                }

                val productErrorSerializable = ProductErrorSerializable(response?.error)
                call.respond(productErrorSerializable)
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByDescripcionProduct> {
        withContext(Dispatchers.IO) {
            try {
                val productName = call.parameters["descripcion"]
                val response = productService.findProductByParam(Constants.KEY_NAME, productName)

                if (response?.product != null) {
                    val responseSerializable = convertAllProductsToSerializable(response.product)
                    call.respond(responseSerializable)
                }

                val productErrorSerializable = ProductErrorSerializable(response?.error)
                call.respond(productErrorSerializable)
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByColourProduct> {
        withContext(Dispatchers.IO) {
            try {
                val productColour = call.parameters["colour"]
                val response = productService.findProductByParam(Constants.KEY_COLOUR, productColour)

                if (response?.product != null) {
                    val responseSerializable = convertAllProductsToSerializable(response.product)
                    call.respond(responseSerializable)
                }

                val productErrorSerializable = ProductErrorSerializable(response?.error)
                call.respond(productErrorSerializable)
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByWaistProduct> {
        withContext(Dispatchers.IO) {
            try {
                val productWaist = call.parameters["waist"]
                val response = productService.findProductByParam(Constants.KEY_WAIST, productWaist)

                if (response?.product != null) {
                    val responseSerializable = convertAllProductsToSerializable(response.product)
                    call.respond(responseSerializable)
                }

                val productErrorSerializable = ProductErrorSerializable(response?.error)
                call.respond(productErrorSerializable)
            } catch (ex: Exception) {
                throw ex
            }
        }
    }
    get<GetByIsEnableProduct> {
        withContext(Dispatchers.IO) {
            try {
                val productIsEnable = call.parameters["isEnable"]
                val response = productService.findProductByParam(Constants.KEY_IS_ENABLE, productIsEnable)

                if (response?.product != null) {
                    val responseSerializable = convertAllProductsToSerializable(response.product)
                    call.respond(responseSerializable)
                }

                val productErrorSerializable = ProductErrorSerializable(response?.error)
                call.respond(productErrorSerializable)
            } catch (ex: Exception) {
                throw ex
            }
        }
    }
}

private fun convertAllProductsToSerializable(productsDto: MutableList<ProductDto?>?): MutableList<ProductSerializable?> {
    val productSerializableList = mutableListOf<ProductSerializable?>()

    productsDto?.forEach {
        productSerializableList.add(
            ProductSerializable(
                it?.idProduct,
                it?.name,
                it?.description,
                it?.colour,
                it?.waist,
                it?.quantity,
                it?.purchasePrice,
                it?.salePrice,
                it?.isAugmentedReality,
                it?.isEnable
            )
        )
    }

    return productSerializableList
}