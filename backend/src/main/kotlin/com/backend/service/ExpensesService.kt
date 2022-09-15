package com.backend.service

import com.backend.client.database.expenses.persistence.entity.IExpensesEntity
import com.backend.client.database.expenses.repository.ExpensesRepository
import com.backend.domain.dto.ExpenseDto
import com.backend.domain.reponses.ExpenseByIdResponse
import com.backend.domain.reponses.ExpensesStringResponses
import com.backend.domain.serializable.ExpensesCreateSerializable
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

class ExpensesService(private val expensesRepository: ExpensesRepository,) : IExpensesService {

    override suspend fun createExpense(expenseCreateSerializable: ExpensesCreateSerializable): String? {
        val expensesResponse = ExpensesStringResponses(null, null)

        return runBlocking {
            try {
                val expenseDto = ExpenseDto(
                    idUser = expenseCreateSerializable.expense.idUser!!,
                    expenseName = expenseCreateSerializable.expense.expenseName!!,
                    idExpenseType = expenseCreateSerializable.expense.idExpenseType!!,
                    amount = expenseCreateSerializable.expense.amount!!,
                    createdBillDate = LocalDateTime.now(),
                    dateRegister = LocalDateTime.now()
                )

                val response = async { expensesRepository.createExpense(expenseDto) }
                expensesResponse.expense = response.await()

                return@runBlocking expensesResponse.expense
            } catch (ex: Exception) {
                print("ExpensesService |createExpense| ERROR: $ex")
                expensesResponse.error = ex.message
                return@runBlocking expensesResponse.error
            }
        }
    }

    override suspend fun findByParam(key: String, idUser: String?): ExpenseByIdResponse? {
        val expenseResponse = ExpenseByIdResponse(null, null)

        if (idUser != null) {
            return runBlocking {
                try {
                    val expenseEntity = expensesRepository.findByParam(key, idUser)

                    if (expenseEntity == null) {
                        expenseResponse.error = "The $idUser does not exist"
                        return@runBlocking expenseResponse
                    }

                    expenseResponse.expense = convertAllExpensesToDto(expenseEntity)
                    return@runBlocking expenseResponse


                } catch (ex: Exception) {
                    print("ExpensesService |findByIdUser| ERROR: $ex")
                    expenseResponse.error = ex.message
                    return@runBlocking expenseResponse
                }
            }
        } else {
            expenseResponse.error = "BillService ERROR: The body can not be null"
            return expenseResponse
        }
    }

    override suspend fun findAllExpenseByDateRegister(): MutableList<ExpenseDto?>? {
        return runBlocking {
            try {
                val expensesEntity = expensesRepository.findAllExpensesByDateRegister()

                if (expensesEntity == null) {
                    return@runBlocking expensesEntity
                }

                val expensesListDto = convertAllExpensesToDto(expensesEntity)
                return@runBlocking expensesListDto
            } catch (ex: Exception) {
                print("ExpensesService |findAllExpenseByDateRegister| ERROR: $ex")
                return@runBlocking null
            }
        }
    }

    private fun convertAllExpensesToDto(expensesEntity: List<IExpensesEntity>): MutableList<ExpenseDto?> {
        val expensesDtoList = mutableListOf<ExpenseDto?>()

        expensesEntity.forEach {
            expensesDtoList.add(it?.toDto())
        }

        return expensesDtoList
    }
}