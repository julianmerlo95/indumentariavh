package com.backend.service

import com.backend.domain.reponses.BillByIdParamResponse
import com.backend.domain.reponses.BillByIdResponse
import com.backend.domain.dto.bill.BillDto
import com.backend.domain.dto.billDetail.BillDetailDto
import com.backend.domain.serializable.BillCreateSerializable

interface IBillService {

    suspend fun createBill(billCreateSerializable: BillCreateSerializable): String?
    suspend fun findByIdBill(idBill: String?): BillByIdResponse?
    suspend fun findDetailsByIdBill(idBill: String?): MutableList<BillDetailDto?>?
    suspend fun findAllBillByDateRegister(): MutableList<BillDto?>?
    suspend fun findFirstBillByDateRegister(): MutableList<BillDto?>?
    suspend fun findBillByParam(key: String, param: String): BillByIdParamResponse?
    suspend fun findTodayBillsByDateRegister(): MutableList<BillDto?>?
}