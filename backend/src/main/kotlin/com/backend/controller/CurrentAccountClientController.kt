package com.backend.controller

import com.backend.domain.Constants
import com.backend.domain.dto.CurrentAccountClientDto
import com.backend.domain.serializable.CurrentAccountClientCreateSerializable
import com.backend.domain.serializable.CurrentAccountClientSerializable
import com.backend.routings.*
import com.backend.service.CurrentAccountClientService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.ktor.ext.inject

fun Route.currentAccountClient() {

    val currentAccountClient by inject<CurrentAccountClientService>()

    route("/api/v1/current-account-client") {
        post {
            try {
                val currentAccountClientCreateSerializable = call.receive<CurrentAccountClientCreateSerializable>()
                val response = currentAccountClient.createCurrentAccountClient(currentAccountClientCreateSerializable)
                call.respond(HttpStatusCode.OK, "$response")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetAllByRegisterDateCurrentAccountClient> {
        withContext(Dispatchers.IO) {
            try {
                val response = currentAccountClient.findAllCurrentAccountClientByDateRegister()

                if (response != null) {
                    val responseSerializable = convertAllCurrentAccountClientsToSerializable(response)
                    call.respond(responseSerializable)
                }
                call.respond(HttpStatusCode.OK, "$response")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetFirstByRegisterDateCurrentAccountClient> {
        withContext(Dispatchers.IO) {
            try {
                val response = currentAccountClient.findFirstCurrentAccountClientByDateRegister()

                if (response != null) {
                    val responseSerializable = convertAllCurrentAccountClientsToSerializable(response)
                    call.respond(responseSerializable)
                }
                call.respond(HttpStatusCode.OK, "$response")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByIdCurrentAccountClient> {
        withContext(Dispatchers.IO) {
            try {
                val idCurrentAccountClient = call.parameters["id"]
                val response = currentAccountClient.findByIdCurrentAccountClient(idCurrentAccountClient?.toInt())

                if (response != null) {
                    val responseSerializable = convertAllCurrentAccountClientsToSerializable(response?.currentAccountClient)
                    call.respond(responseSerializable)
                }

                call.respond(HttpStatusCode.OK, "$response")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByIdClientCurrentAccountClient> {
        withContext(Dispatchers.IO) {
            try {
                val idClientCurrentAccountClient = call.parameters["idClient"]

                if (idClientCurrentAccountClient != null) {
                    val response = currentAccountClient.findCurrentAccountClientByParam(Constants.KEY_ID_CLIENT, idClientCurrentAccountClient)
                    val responseSerializable = convertAllCurrentAccountClientsToSerializable(response?.currentAccountClient)
                    call.respond(responseSerializable)
                } else {
                    call.respond(HttpStatusCode.OK, "The idClient cannot be empty o null")
                }
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByFromDateClientCurrentAccountClient> {
        withContext(Dispatchers.IO) {
            try {
                val fromDateCurrentAccountClient = call.parameters["fromDate"].toString()

                if (fromDateCurrentAccountClient != null) {
                    val response = currentAccountClient.findCurrentAccountClientByParam(Constants.KEY_FROM_DATE, fromDateCurrentAccountClient)
                    print(response)
                    val responseSerializable = convertAllCurrentAccountClientsToSerializable(response?.currentAccountClient)
                    call.respond(responseSerializable)
                } else {
                    call.respond(HttpStatusCode.OK, "The idClient cannot be empty o null")
                }
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByTodayDateCurrentAccountClient> {
        withContext(Dispatchers.IO) {
            try {
                val response = currentAccountClient.findTodayCurrentAccountClientByDateRegister()
                val responseSerializable = convertAllCurrentAccountClientsToSerializable(response)
                call.respond(responseSerializable)
            } catch (ex: Exception) {
                throw ex
            }
        }
    }
}

private fun convertAllCurrentAccountClientsToSerializable(currentAccountClientDto: MutableList<CurrentAccountClientDto?>?): MutableList<CurrentAccountClientSerializable?> {
    val currentAccountClientSerializableList = mutableListOf<CurrentAccountClientSerializable?>()

    currentAccountClientDto?.forEach {
        currentAccountClientSerializableList.add(
            CurrentAccountClientSerializable(
                it?.idClient,
                it?.actualMoneyDebt,
                it?.moneyCredit,
                it?.moneyDebit,
                it?.newMoneyDebt,
                it?.dateRegister.toString(),
                it?.userName
            )
        )
    }

    return currentAccountClientSerializableList
}