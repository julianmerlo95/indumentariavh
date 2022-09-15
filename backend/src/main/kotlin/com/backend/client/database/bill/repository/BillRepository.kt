package com.backend.client.database.bill.repository

import com.backend.client.database.bill.BillTable
import com.backend.client.database.bill.persistence.entity.BillEntity
import com.backend.client.database.bill.persistence.entity.IBillEntity
import com.backend.domain.dto.bill.BillDto
import com.backend.exception.DataBaseException
import com.backend.util.Mapper.mapperDate
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greaterEq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class BillRepository : IBillRepository {

    override suspend fun createBill(billDto: BillDto): String? {
        return transaction {
            try {
                BillEntity.new(billDto.idBill) {
                    createdBillDate = billDto.createdBillDate
                    totalAmount = billDto.totalAmount
                    totalDiscount = billDto.totalDiscount
                    cash = billDto.cash
                    credit = billDto.credit
                    borrowedCash = billDto.borrowedCash
                    idBillType = billDto.idBillType
                    idUser = billDto.idUser
                    idClient = billDto.idClient
                    idBillStatus = billDto.idBillStatus
                    dateRegister = billDto.dateRegister
                    idPaymentMethod = billDto.idPaymentMethod
                }
                return@transaction "The client ${billDto.idBill} was save correctly in database"
            } catch (ex: Exception) {
                return@transaction "BillRepository PERSISTENCE ERROR: The bill ${billDto.idBill} was error to saved: ${ex.message}"
            }
        }
    }

    override suspend fun findByIdBill(idBill: String?): IBillEntity? {
        return transaction {
            try {
                if (idBill != null) {
                    return@transaction BillEntity.findById(idBill)
                }
                return@transaction null
            } catch (ex: Exception) {
                throw DataBaseException(message = "BillRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}", cause = ex)
            }
        }
    }

    override suspend fun findAllBillByDateRegister(): List<IBillEntity> {
        return transaction {
            try {
                return@transaction BillEntity.find {
                    (BillTable.dateRegister greaterEq "2022-08-01T00:00:00")
                }.orderBy(BillTable.dateRegister to SortOrder.DESC).toList()
            } catch (ex: Exception) {
                throw DataBaseException(message = "BillRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}", cause = ex)
            }
        }
    }

    override suspend fun findFirstBillByDateRegister(): List<IBillEntity> {
        return transaction {
            try {
                return@transaction BillEntity.find {
                    (BillTable.dateRegister greaterEq "2022-08-01T00:00:00")
                }.orderBy(BillTable.dateRegister to SortOrder.DESC).limit(2).toList()
            } catch (ex: Exception) {
                throw DataBaseException(message = "BillRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}", cause = ex)
            }
        }
    }

    override suspend fun findTodayBillsByDateRegister(): List<IBillEntity> {
        return transaction {
            try {
                val today = LocalDateTime.now().toString()

                return@transaction BillEntity.find {
                    (BillTable.createdBillDate greaterEq "2022-08-01T00:00:00")
                    (BillTable.createdBillDate eq mapperDate(today.toString().substring(0, 10)))
                }.orderBy(BillTable.createdBillDate to SortOrder.DESC).toList()
            } catch (ex: Exception) {
                throw DataBaseException(message = "BillRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}", cause = ex)
            }
        }
    }

    override suspend fun findBillByParam(key: String, param: String): List<IBillEntity>? {
        var query: Op<Boolean> = (BillTable.dateRegister greaterEq "2022-08-01T00:00:00")

        return transaction {
            try {

                if (key == "idUser") {
                    query = query and (BillTable.idUser eq param.toInt())
                } else if (key == "fromDate") {
                    query = query and (BillTable.createdBillDate eq mapperDate(param))
                } else if (key == "idClient") {
                    query = query and (BillTable.idClient eq param.toInt())
                } else if (key == "idBillStatus") {
                    query = query and (BillTable.idBillStatus eq param.toInt())
                } else {
                    return@transaction null
                }

                return@transaction BillEntity.find { query }.orderBy(BillTable.dateRegister to SortOrder.DESC).toList()

            } catch (ex: Exception) {
                throw DataBaseException(message = "BillRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}", cause = ex)
            }
        }
    }
}
