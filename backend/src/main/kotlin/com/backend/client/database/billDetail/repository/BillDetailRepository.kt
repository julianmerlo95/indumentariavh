package com.backend.client.database.billDetail.repository

import com.backend.client.database.billDetail.BillDetailTable
import com.backend.client.database.billDetail.persistence.entity.BillDetailEntity
import com.backend.client.database.billDetail.persistence.entity.IBillDetailEntity
import com.backend.domain.dto.billDetail.BillDetailDto
import com.backend.exception.DataBaseException
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.transactions.transaction

class BillDetailRepository : IBillDetailRepository {

    override suspend fun createBillDetail(billDetailDto: BillDetailDto): String? {
        return transaction {
            try {
                BillDetailEntity.new {
                     idBill = billDetailDto.idBill
                     quantity = billDetailDto.quantity
                     price = billDetailDto.price
                     idProduct = billDetailDto.idProduct
                     colour = billDetailDto.colour
                     waist = billDetailDto.waist
                     dateRegister = billDetailDto.dateRegister
                }
                return@transaction "The billDetail ${billDetailDto} was save correctly in database"
            } catch (ex: Exception) {
                return@transaction "BillDetailRepository PERSISTENCE ERROR: The billDetail ${billDetailDto} was error to saved: ${ex.message}"
            }
        }
    }

    override suspend fun findDetailsByIdBill(idBill: String?): List<IBillDetailEntity>? {
        return transaction {
            try {
                if (idBill != null) {
                    return@transaction BillDetailEntity.find {
                        (BillDetailTable.idBill eq idBill)
                    }.orderBy(BillDetailTable.dateRegister to SortOrder.DESC).toList()
                }
                return@transaction null
            } catch (ex: Exception) {
                throw DataBaseException(message = "BillDetailRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}", cause = ex)
            }
        }
    }
}