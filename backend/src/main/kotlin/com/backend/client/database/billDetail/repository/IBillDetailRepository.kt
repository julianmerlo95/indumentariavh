package com.backend.client.database.billDetail.repository

import com.backend.client.database.billDetail.persistence.entity.IBillDetailEntity
import com.backend.domain.dto.billDetail.BillDetailDto

interface IBillDetailRepository {

    suspend fun createBillDetail(billDetailDto: BillDetailDto): String?
    suspend fun findDetailsByIdBill(idBill: String?): List<IBillDetailEntity>?
}