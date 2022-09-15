package com.backend.controller

import com.backend.domain.Constants
import com.backend.domain.dto.ExpenseDto
import com.backend.domain.serializable.*
import com.backend.routings.*
import com.backend.service.ExpensesService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.ktor.ext.inject

fun Route.expenses() {

    val expensesService by inject<ExpensesService>()

    route("/api/v1/expenses/expense") {
        post {
            try {
                val expenseCreateSerializable = call.receive<ExpensesCreateSerializable>()
                val response = expensesService.createExpense(expenseCreateSerializable)
                call.respond(HttpStatusCode.OK, "$response")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetAllByRegisterDateExpenses> {
        withContext(Dispatchers.IO) {
            try {
                val response = expensesService.findAllExpenseByDateRegister()

                if (response != null) {
                    val responseSerializable = expenseSuccessSerializable(response)
                    call.respond(responseSerializable)
                }

                call.respond(HttpStatusCode.OK, "${response}")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByIdUserExpenses> {
        withContext(Dispatchers.IO) {
            try {
                val idUserExpense = call.parameters["idUser"]

                if (idUserExpense != null) {
                    val response = expensesService.findByParam(Constants.KEY_ID_USER, idUserExpense)
                    val responseSerializable = expenseSuccessSerializable(response?.expense)
                    call.respond(responseSerializable)
                } else {
                    call.respond(HttpStatusCode.OK, "The idUser cannot be empty o null")
                }
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByFromDateExpenses> {
        withContext(Dispatchers.IO) {
            try {
                val idUserExpense = call.parameters["fromDate"]

                if (idUserExpense != null) {
                    val response = expensesService.findByParam(Constants.KEY_FROM_DATE, idUserExpense)
                    val responseSerializable = expenseSuccessSerializable(response?.expense)
                    call.respond(responseSerializable)
                } else {
                    call.respond(HttpStatusCode.OK, "The fromDate cannot be empty o null")
                }
            } catch (ex: Exception) {
                throw ex
            }
        }
    }
}

private fun expenseSuccessSerializable(expensesDto: MutableList<ExpenseDto?>?): MutableList<ExpenseSerializable?> {
    val expenseSerializableList = mutableListOf<ExpenseSerializable?>()

    expensesDto?.forEach {
        expenseSerializableList.add(
            ExpenseSerializable(
                it?.idUser,
                it?.idExpenseType,
                it?.expenseName,
                it?.amount,
                it?.dateRegister.toString(),
                it?.createdBillDate.toString()
            )
        )
    }
    return expenseSerializableList
}
