package com.backend.service

import com.backend.client.database.bill.persistence.entity.IBillEntity
import com.backend.client.database.bill.repository.BillRepository
import com.backend.domain.reponses.BillByIdParamResponse
import com.backend.domain.reponses.BillByIdResponse
import com.backend.domain.dto.bill.BillDto
import com.backend.domain.dto.billDetail.BillDetailDto
import com.backend.domain.serializable.BillCreateSerializable
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

class BillService(
    private val billRepository: BillRepository,
    private val billDetailService: BillDetailService
) : IBillService {

    override suspend fun createBill(billCreateSerializable: BillCreateSerializable): String? {
        var billResponse: String? = ""
        var billDetailResponse: String? = ""

        if (billCreateSerializable != null) {
            return runBlocking {
                try {
                    val billDto = BillDto(
                        idBill = billCreateSerializable.bill.idBill!!,
                        createdBillDate = LocalDateTime.now(),
                        totalAmount = billCreateSerializable.bill.totalAmount!!,
                        totalDiscount = billCreateSerializable.bill.totalDiscount!!,
                        cash = billCreateSerializable.bill.cash!!,
                        credit = billCreateSerializable.bill.credit!!,
                        borrowedCash = billCreateSerializable.bill.borrowedCash!!,
                        idBillType = billCreateSerializable.bill.idBillType!!,
                        idUser = billCreateSerializable.bill.idUser!!,
                        idClient = billCreateSerializable.bill.idClient!!,
                        idBillStatus = billCreateSerializable.bill.idBillStatus!!,
                        dateRegister = LocalDateTime.now(),
                        idPaymentMethod = billCreateSerializable.bill.idPaymentMethod!!
                    )
                    val billRepositoryReponse = async { billRepository.createBill(billDto) }
                    val billDetailServiceResponse = async { billDetailService.createBillDetail(billCreateSerializable.bill.idBill!!, billCreateSerializable.bill.listBillDetail!!) }

                    billResponse = billRepositoryReponse.await()
                    billDetailResponse = billDetailServiceResponse.await()

                    return@runBlocking "BillResponse:$billResponse + billDetaillResponse: $billDetailResponse"

                } catch (ex: Exception) {
                    print("BillService |createBill| ERROR: $ex")
                    return@runBlocking "BillService ERROR: Error message: $ex"
                }
            }
        } else {
            return "BillService ERROR: The body can not be null"
        }
    }

    override suspend fun findByIdBill(idBill: String?): BillByIdResponse? {
        val billResponse = BillByIdResponse(null, null)
        val billMutableList = mutableListOf<BillDto?>()

        if (idBill != null) {
            return runBlocking {
                try {
                    val bill = billRepository.findByIdBill(idBill)

                    if (bill == null) {
                        billResponse.error = "The bill does not exist"
                        return@runBlocking billResponse
                    }
                    billMutableList.add(bill?.toDto())
                    billResponse.bill = billMutableList
                    return@runBlocking billResponse
                } catch (ex: Exception) {
                    print("BillService |findByIdBill| ERROR: $ex")
                    billResponse.error = ex.message
                    return@runBlocking billResponse
                }
            }
        } else {
            billResponse.error = "BillService ERROR: The id can not be null"
            return billResponse
        }
    }

    override suspend fun findDetailsByIdBill(idBill: String?): MutableList<BillDetailDto?>? {

        if (idBill != null) {
            return runBlocking {
                try {
                    val billDetail = billDetailService.findDetailsByIdBill(idBill)

                    if (billDetail == null) {
                        return@runBlocking null
                    }
                    return@runBlocking billDetail
                } catch (ex: Exception) {
                    print("BillService |findByIdBill| ERROR: $ex")
                    return@runBlocking null
                }
            }
        } else {
            return null
        }
    }

    override suspend fun findBillByParam(key: String, param: String): BillByIdParamResponse? {
        val billResponse = BillByIdParamResponse(null, null)

        if (key != null && param != null) {
            return runBlocking {
                try {
                    val clientsEntity = billRepository.findBillByParam(key, param)

                    if (clientsEntity == null || clientsEntity.isEmpty()) {
                        billResponse.error = "The $key: $param does not exist"
                        return@runBlocking billResponse
                    }

                    billResponse.bill = convertAllBillsToDto(clientsEntity)
                    return@runBlocking billResponse


                } catch (ex: Exception) {
                    print("BillService |findBillByParam| ERROR: $ex")
                    billResponse.error = ex.message
                    return@runBlocking billResponse
                }
            }
        } else {
            billResponse.error = "BillService ERROR: The body can not be null"
            return billResponse
        }
    }

    override suspend fun findAllBillByDateRegister(): MutableList<BillDto?>? {
        return runBlocking {
            try {
                val billEntity = billRepository.findAllBillByDateRegister()

                if (billEntity == null) {
                    return@runBlocking billEntity
                }

                val billListDto = convertAllBillsToDto(billEntity)
                return@runBlocking billListDto
            } catch (ex: Exception) {
                print("BillService |findAllBillByDateRegister| ERROR: $ex")
                return@runBlocking null
            }
        }
    }

    override suspend fun findFirstBillByDateRegister(): MutableList<BillDto?>? {
        return runBlocking {
            try {
                val billEntity = billRepository.findFirstBillByDateRegister()

                if (billEntity == null) {
                    return@runBlocking billEntity
                }

                val billListDto = convertAllBillsToDto(billEntity)
                return@runBlocking billListDto
            } catch (ex: Exception) {
                print("BillService |findFirstBillByDateRegister| ERROR: $ex")
                return@runBlocking null
            }
        }
    }

    override suspend fun findTodayBillsByDateRegister(): MutableList<BillDto?>? {
        return runBlocking {
            try {
                val billEntity = billRepository.findTodayBillsByDateRegister()

                if (billEntity == null) {
                    return@runBlocking billEntity
                }

                val billListDto = convertAllBillsToDto(billEntity)
                return@runBlocking billListDto
            } catch (ex: Exception) {
                print("BillService |findTodayBillsByDateRegister| ERROR: $ex")
                return@runBlocking null
            }
        }
    }

    private fun convertAllBillsToDto(billEntity: List<IBillEntity>): MutableList<BillDto?> {
        val billDtoList = mutableListOf<BillDto?>()

        billEntity.forEach {
            billDtoList.add(it?.toDto())
        }

        return billDtoList
    }
}