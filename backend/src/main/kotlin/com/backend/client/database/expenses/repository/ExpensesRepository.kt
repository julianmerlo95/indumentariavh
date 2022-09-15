package com.backend.client.database.expenses.repository

import com.backend.client.database.bill.BillTable
import com.backend.client.database.bill.persistence.entity.BillEntity
import com.backend.client.database.expenses.ExpensesTable
import com.backend.client.database.expenses.persistence.entity.ExpensesEntity
import com.backend.client.database.expenses.persistence.entity.IExpensesEntity
import com.backend.domain.dto.ExpenseDto
import com.backend.exception.DataBaseException
import com.backend.util.Mapper
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greaterEq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

class ExpensesRepository : IExpensesRepository {
    override suspend fun createExpense(expensesDto: ExpenseDto): String? {
        return transaction {
            try {
                ExpensesEntity.new {
                    idUser = expensesDto.idUser
                    expenseName = expensesDto.expenseName
                    idExpenseType = expensesDto.idExpenseType
                    amount = expensesDto.amount
                    createdBillDate = expensesDto.createdBillDate
                    dateRegister = expensesDto.dateRegister
                }
                return@transaction "The ${expensesDto.idUser} was save correctly in database"
            } catch (ex: Exception) {
                return@transaction "ExpensesRepository PERSISTENCE ERROR: The debt ${expensesDto.idUser} was error to saved: ${ex.message}"
            }
        }
    }


    override suspend fun findByParam(key: String, param: String): List<IExpensesEntity>? {
        var query: Op<Boolean> = (ExpensesTable.dateRegister greaterEq "2022-08-01T00:00:00")

        return transaction {
            try {

                if (key == "idUser") {
                    query = query and (ExpensesTable.idUser eq param.toInt())
                } else if (key == "fromDate") {
                    query = query and (ExpensesTable.createdBillDate eq Mapper.mapperDate(param))
                } else {
                    return@transaction null
                }

                return@transaction ExpensesEntity.find { query }.orderBy(ExpensesTable.dateRegister to SortOrder.DESC).toList()

            } catch (ex: Exception) {
                throw DataBaseException(message = "BillRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}", cause = ex)
            }
        }
    }

    override suspend fun findAllExpensesByDateRegister(): List<IExpensesEntity> {
        return transaction {
            try {
                return@transaction ExpensesEntity.find {
                    (ExpensesTable.dateRegister greaterEq "2022-08-01T00:00:00")
                }.orderBy(ExpensesTable.dateRegister to SortOrder.DESC).toList()
            } catch (ex: Exception) {
                throw DataBaseException(message = "ExpensesRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}", cause = ex)
            }
        }
    }
}