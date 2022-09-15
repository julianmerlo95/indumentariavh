package com.backend.service

import com.backend.domain.dto.billDetail.BillDetailDto
import com.backend.domain.dto.billDetail.BillDetailSerializable

interface IBillDetailService {

    suspend fun createBillDetail(idBill: String, listBillDetailSerializable: MutableList<BillDetailSerializable>): String?
    suspend fun findDetailsByIdBill(idBill: String): MutableList<BillDetailDto?>?
}