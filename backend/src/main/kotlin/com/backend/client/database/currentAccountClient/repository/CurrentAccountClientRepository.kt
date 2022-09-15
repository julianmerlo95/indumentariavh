package com.backend.client.database.currentAccountClient.repository

import com.backend.client.database.currentAccountClient.CurrentAccountClientTable
import com.backend.client.database.currentAccountClient.persistence.entity.CurrentAccountClientEntity
import com.backend.client.database.currentAccountClient.persistence.entity.ICurrentAccountClientEntity
import com.backend.domain.dto.CurrentAccountClientDto
import com.backend.exception.DataBaseException
import com.backend.util.Mapper
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greaterEq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class CurrentAccountClientRepository : ICurrentAccountClientRepository {

    override suspend fun createCurrentAccountClient(currentAccountClientDto: CurrentAccountClientDto): String? {
        return transaction {
            try {
                CurrentAccountClientEntity.new {
                    idClient = currentAccountClientDto.idClient
                    actualMoneyDebt = currentAccountClientDto.actualMoneyDebt
                    moneyCredit = currentAccountClientDto.moneyCredit
                    moneyDebit = currentAccountClientDto.moneyDebit
                    newMoneyDebt = currentAccountClientDto.newMoneyDebt
                    dateRegister = currentAccountClientDto.dateRegister
                    userName = currentAccountClientDto.userName
                    createdBillDate = currentAccountClientDto.createdBillDate
                }
                return@transaction "The debt ${currentAccountClientDto.idClient} was save correctly in database"
            } catch (ex: Exception) {
                return@transaction "CurrentAccountClientRepository PERSISTENCE ERROR: The debt ${currentAccountClientDto.idClient} was error to saved: ${ex.message}"
            }
        }
    }

    override suspend fun findByIdCurrentAccountClient(idCurrentAccountClient: Int?): ICurrentAccountClientEntity? {
        return transaction {
            try {
                if (idCurrentAccountClient != null) {
                    return@transaction CurrentAccountClientEntity.findById(idCurrentAccountClient)
                }
                return@transaction null
            } catch (ex: Exception) {
                throw DataBaseException(message = "CurrentAccountClientRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}", cause = ex)
            }
        }
    }

    override suspend fun findAllCurrentAccountClientByDateRegister(): List<ICurrentAccountClientEntity> {
        return transaction {
            try {
                return@transaction CurrentAccountClientEntity.find {
                    (CurrentAccountClientTable.dateRegister greaterEq "2022-08-01T00:00:00")
                }.orderBy(CurrentAccountClientTable.dateRegister to SortOrder.DESC).toList()
            } catch (ex: Exception) {
                throw DataBaseException(message = "CurrentAccountClientRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}", cause = ex)
            }
        }
    }

    override suspend fun findTodayCurrentAccountClientByDateRegister(): List<ICurrentAccountClientEntity> {
        return transaction {
            try {
                val today = LocalDateTime.now().toString()

                return@transaction CurrentAccountClientEntity.find {
                    (CurrentAccountClientTable.createdBillDate greaterEq "2022-08-01T00:00:00")
                    (CurrentAccountClientTable.createdBillDate eq Mapper.mapperDate(today.toString().substring(0, 10)))
                }.orderBy(CurrentAccountClientTable.createdBillDate to SortOrder.DESC).toList()
            } catch (ex: Exception) {
                throw DataBaseException(message = "BillRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}", cause = ex)
            }
        }
    }

    override suspend fun findFirstCurrentAccountClientByDateRegister(): List<ICurrentAccountClientEntity> {
        return transaction {
            try {
                return@transaction CurrentAccountClientEntity.find {
                    (CurrentAccountClientTable.dateRegister greaterEq "2022-08-01T00:00:00")
                }.orderBy(CurrentAccountClientTable.dateRegister to SortOrder.DESC).limit(1).toList()
            } catch (ex: Exception) {
                throw DataBaseException(message = "CurrentAccountClientRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}", cause = ex)
            }
        }
    }

    override suspend fun findCurrentAccountClientByParam(key: String, param: String): List<ICurrentAccountClientEntity>? {
        var query: Op<Boolean> = (CurrentAccountClientTable.dateRegister greaterEq "2022-08-01T00:00:00")

        return transaction {
            try {

                if (key == "idClient") {
                    query = query and (CurrentAccountClientTable.idClient eq param)
                } else if (key == "fromDate") {
                    query = query and (CurrentAccountClientTable.createdBillDate eq Mapper.mapperDate(param))
                } else {
                    return@transaction null
                }

                return@transaction CurrentAccountClientEntity.find { query }.orderBy(CurrentAccountClientTable.dateRegister to SortOrder.DESC).toList()

            } catch (ex: Exception) {
                throw DataBaseException(message = "CurrentAccountClientRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}", cause = ex)
            }
        }
    }
}