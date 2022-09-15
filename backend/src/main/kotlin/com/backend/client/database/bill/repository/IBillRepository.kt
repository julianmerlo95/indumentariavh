package com.backend.client.database.bill.repository

import com.backend.client.database.bill.persistence.entity.IBillEntity
import com.backend.domain.dto.bill.BillDto

interface IBillRepository {

    suspend fun findBillByParam(key: String, param: String): List<IBillEntity>?
    suspend fun createBill(billDto: BillDto): String?
    suspend fun findByIdBill(idBill: String?): IBillEntity?
    suspend fun findAllBillByDateRegister(): List<IBillEntity>
    suspend fun findFirstBillByDateRegister(): List<IBillEntity>
    suspend fun findTodayBillsByDateRegister(): List<IBillEntity>
}