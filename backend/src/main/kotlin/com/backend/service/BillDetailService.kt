package com.backend.service

import com.backend.client.database.billDetail.persistence.entity.IBillDetailEntity
import com.backend.client.database.billDetail.repository.BillDetailRepository
import com.backend.domain.dto.billDetail.BillDetailDto
import com.backend.domain.dto.billDetail.BillDetailSerializable
import com.backend.domain.serializable.ProductUpdateSerializable
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

class BillDetailService(
    private val billDetailRepository: BillDetailRepository,
    private val productService: ProductService
) : IBillDetailService {

    override suspend fun createBillDetail(idBill: String, listBillDetailSerializable: MutableList<BillDetailSerializable>): String? {
        var billDetailResponse: String? = ""
        return runBlocking {
            try {
                listBillDetailSerializable.forEach {
                    if (it != null) {
                        updateQuantityProduct(it)
                        val billDetailDto = BillDetailDto(
                            idBill = idBill,
                            quantity = it.quantity,
                            price = it.price,
                            idProduct = it.idProduct,
                            colour = it.colour,
                            waist = it.waist,
                            dateRegister = LocalDateTime.now()
                        )
                        billDetailRepository.createBillDetail(billDetailDto)
                    }
                    billDetailResponse = "All details saved correctly"
                }
                return@runBlocking billDetailResponse
            } catch (ex: Exception) {
                print("BillDetailService |createBillDetail| ERROR: $ex")
                billDetailResponse = "$ex"
                return@runBlocking billDetailResponse
            }
        }
    }

    override suspend fun findDetailsByIdBill(idBill: String): MutableList<BillDetailDto?>? {
        return runBlocking {
            try {
                val billEntity = billDetailRepository.findDetailsByIdBill(idBill)

                if (billEntity == null) {
                    return@runBlocking billEntity
                }

                val billDetailListDto = convertAllBillDetailsToDto(billEntity)
                return@runBlocking billDetailListDto
            } catch (ex: Exception) {
                print("BillDetailService |findDetailsByIdBill| ERROR: $ex")
                return@runBlocking null
            }
        }
    }

    private fun updateQuantityProduct(it: BillDetailSerializable) {
        val response = productService.findByIdProduct(it.idProduct)
        val newQuantityProduct = (response?.product?.get(0)?.quantity?.minus(it.quantity))
        val productUpdateSerializable = ProductUpdateSerializable(quantity = newQuantityProduct)
        productService.updateProduct(it.idProduct, productUpdateSerializable)
    }

    private fun convertAllBillDetailsToDto(billDetailEntity: List<IBillDetailEntity>): MutableList<BillDetailDto?> {
        val billDetailDtoList = mutableListOf<BillDetailDto?>()

        billDetailEntity.forEach {
            billDetailDtoList.add(it?.toDto())
        }

        return billDetailDtoList
    }
}