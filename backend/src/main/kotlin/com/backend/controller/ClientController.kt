package com.backend.controller

import com.backend.domain.Constants
import com.backend.domain.dto.ClientDto
import com.backend.domain.serializable.client.*
import com.backend.routings.*
import com.backend.service.ClientService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import org.koin.ktor.ext.inject
import kotlinx.coroutines.withContext


fun Route.client() {

    val clientService by inject<ClientService>()

    route("/api/v1/clients/client") {
        post {
            try {
                val clientCreateSerializable = call.receive<ClientCreateSerializable>()
                val response = clientService.createClient(clientCreateSerializable)
                call.respond(HttpStatusCode.OK, "$response")

                if (response?.client != null) {
                    val clientSuccessSerializable = ClientSuccessSerializable(response.client)
                    call.respond(clientSuccessSerializable)
                }

                val clientErrorSerializable = ClientErrorSerializable(response?.error)
                call.respond(clientErrorSerializable)

            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    route("/api/v1/clients/{id}") {
        put {
            try {
                val idClient = call.parameters["id"]?.toLong()
                val ClientUpdateSerializable = call.receive<ClientUpdateSerializable>()
                val response = clientService.updateClient(idClient, ClientUpdateSerializable)

                if (response?.client != null) {
                    val responseSerializable = ClientSuccessSerializable(response.client)
                    call.respond(responseSerializable)
                }

                val clientErrorSerializable = ClientErrorSerializable(response?.error)
                call.respond(HttpStatusCode.OK, clientErrorSerializable)
            } catch (ex: Exception) {
                throw ex
            }
        }

        delete {
            try {
                val idClient = call.parameters["id"]?.toLong()
                val response = clientService.deleteClient(idClient)
                call.respond(HttpStatusCode.OK, "$response")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetAllByRegisterDateClient> {
        withContext(Dispatchers.IO) {
            try {
                val response = clientService.findAllClientByDateRegister()!!

                if (response != null) {
                    val responseSerializable = convertAllClientsToSerializable(response)
                    call.respond(responseSerializable)
                }

                call.respond(HttpStatusCode.OK, "${response}")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByIdClient> {
        withContext(Dispatchers.IO) {
            try {
                val idClient = call.parameters["id"]?.toLong()
                val response = clientService.findByIdClient(idClient)!!

                if (response?.client != null) {
                    val responseSerializable = convertAllClientsToSerializable(response.client)
                    call.respond(responseSerializable)
                }

                val clientErrorSerializable = ClientErrorSerializable(response.error)
                call.respond(clientErrorSerializable)
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByDniClient> {
        withContext(Dispatchers.IO) {
            try {
                val clientDni = call.parameters["dni"]
                val response = clientService.findClientByParam(Constants.KEY_DNI, clientDni)

                if (response?.client != null) {
                    val responseSerializable = convertAllClientsToSerializable(response.client)
                    call.respond(responseSerializable)
                }

                val clientErrorSerializable = ClientErrorSerializable(response?.error)
                call.respond(clientErrorSerializable)
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByNameClient> {
        withContext(Dispatchers.IO) {
            try {
                val clientName = call.parameters["name"]
                val response = clientService.findClientByParam(Constants.KEY_NAME, clientName)

                if (response?.client != null) {
                    val responseSerializable = convertAllClientsToSerializable(response.client)
                    call.respond(responseSerializable)
                }

                val clientErrorSerializable = ClientErrorSerializable(response?.error)
                call.respond(clientErrorSerializable)
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByLastNameClient> {
        withContext(Dispatchers.IO) {
            try {
                val clientLastName = call.parameters["lastName"]
                val response = clientService.findClientByParam(Constants.KEY_LAST_NAME, clientLastName)

                if (response?.client != null) {
                    val responseSerializable = convertAllClientsToSerializable(response.client)
                    call.respond(responseSerializable)
                }

                val clientErrorSerializable = ClientErrorSerializable(response?.error)
                call.respond(clientErrorSerializable)
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByMailClient> {
        withContext(Dispatchers.IO) {
            try {
                val clientMail = call.parameters["mail"]
                val response = clientService.findClientByParam(Constants.KEY_MAIL, clientMail)

                if (response?.client != null) {
                    val responseSerializable = convertAllClientsToSerializable(response.client)
                    call.respond(responseSerializable)
                }

                val clientErrorSerializable = ClientErrorSerializable(response?.error)
                call.respond(clientErrorSerializable)
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByDateOfBirthClient> {
        withContext(Dispatchers.IO) {
            try {
                val clientDateOfBirth = call.parameters["dateOfBirth"]
                val response = clientService.findClientByParam(Constants.KEY_DATE_OF_BRITH, clientDateOfBirth)

                if (response?.client != null) {
                    val responseSerializable = convertAllClientsToSerializable(response.client)
                    call.respond(responseSerializable)
                }

                val clientErrorSerializable = ClientErrorSerializable(response?.error)
                call.respond(clientErrorSerializable)
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByIsEnableClient> {
        withContext(Dispatchers.IO) {
            try {
                val clientIsEnable = call.parameters["isEnable"]
                val response = clientService.findClientByParam(Constants.KEY_IS_ENABLE, clientIsEnable)
                call.respond(HttpStatusCode.OK, "$response")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }
}

private fun convertAllClientsToSerializable(clientsDto: MutableList<ClientDto?>?): MutableList<ClientSerializable?> {
    val clientSerializableList = mutableListOf<ClientSerializable?>()

    clientsDto?.forEach {
        clientSerializableList.add(
            ClientSerializable(
                it?.idClient,
                it?.name,
                it?.lastName,
                it?.dateOfBirth,
                it?.dni,
                it?.mail,
                it?.isEnable,
                it?.moneyDebt
            )
        )
    }

    return clientSerializableList
}