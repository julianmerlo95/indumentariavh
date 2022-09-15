package com.backend.service

import com.backend.client.database.client.persistence.entity.IClientEntity
import com.backend.client.database.client.repository.ClientRepository
import com.backend.domain.dto.ClientDto
import com.backend.domain.reponses.ClientByIdResponse
import com.backend.domain.reponses.ClientByParamResponse
import com.backend.domain.reponses.ClientStringResponse
import com.backend.domain.serializable.client.ClientCreateSerializable
import com.backend.domain.serializable.client.ClientUpdateSerializable
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

class ClientService(
    private val clientRepository: ClientRepository
) : IClientService {

    override fun createClient(clientSerializable: ClientCreateSerializable): ClientStringResponse? {
        val clientResponse = ClientStringResponse(null, null)

        if (clientSerializable != null) {
            return runBlocking {
                try {
                    val clientDto = ClientDto(
                        idClient = clientSerializable.client.dni!!,
                        name = clientSerializable.client.name!!,
                        lastName = clientSerializable.client.lastName!!,
                        dateOfBirth = clientSerializable.client.dateOfBirth!!,
                        dni = clientSerializable.client.dni,
                        mail = clientSerializable.client.mail!!,
                        dateRegister = LocalDateTime.now(),
                        isEnable = clientSerializable.client.isEnable!!,
                        moneyDebt = clientSerializable.client.moneyDebt!!
                    )

                    val response = async { clientRepository.createClient(clientDto) }
                    clientResponse.client = response.await()

                    return@runBlocking clientResponse


                } catch (ex: Exception) {
                    print("ClientService |saveClient| ERROR: $ex")
                    clientResponse.error = ex.message
                    return@runBlocking clientResponse
                }
            }
        } else {
            clientResponse.error = "ClientService ERROR: The body can not be null"
            return clientResponse
        }
    }

    override fun updateClient(idClient: Long?, clientUpdateSerializable: ClientUpdateSerializable): ClientStringResponse? {
        val clientResponse = ClientStringResponse(null, null)

        if (idClient != null && clientUpdateSerializable != null) {
            return runBlocking {
                try {
                    val clientEntity = clientRepository.findByIdClient(idClient)

                    if (clientEntity == null) {
                        clientResponse.error = "The client does not exist"
                        return@runBlocking clientResponse
                    }

                    val clientDto = ClientDto(
                        idClient = clientEntity?.dni!!, // Not update
                        name = if (clientUpdateSerializable.name != null) clientUpdateSerializable.name else clientEntity.name,
                        lastName = if (clientUpdateSerializable.lastName != null) clientUpdateSerializable.lastName else clientEntity.lastName,
                        dateOfBirth = if (clientUpdateSerializable.dateOfBirth != null) clientUpdateSerializable.dateOfBirth else clientEntity.dateOfBirth,
                        dni = clientEntity?.dni!!, // Not update
                        mail = if (clientUpdateSerializable.mail != null) clientUpdateSerializable.mail else clientEntity.mail,
                        dateRegister = clientEntity.dateRegister, // Not update
                        isEnable = if (clientUpdateSerializable.isEnable != null) clientUpdateSerializable.isEnable else clientEntity.isEnable,
                        moneyDebt = if (clientUpdateSerializable.moneyDebt != null) clientUpdateSerializable.moneyDebt else clientEntity.moneyDebt
                    )

                    clientResponse.client = clientRepository.updateClient(clientEntity, clientDto)
                    return@runBlocking clientResponse

                } catch (ex: Exception) {
                    print("ClientService |updateClient| ERROR: $ex")
                    clientResponse.error = ex.message
                    return@runBlocking clientResponse
                }
            }
        } else {
            clientResponse.error = "ClientService ERROR: The id and body can not be null"
            return clientResponse
        }
    }

    override fun deleteClient(idClient: Long?): ClientStringResponse? {
        val clientResponse = ClientStringResponse(null, null)

        if (idClient != null) {
            return runBlocking {
                try {
                    val clientEntity = clientRepository.findByIdClient(idClient)

                    if (clientEntity == null) {
                        clientResponse.error = "The client does not exist"
                        return@runBlocking clientResponse
                    }

                    clientResponse.client = clientRepository.deleteClient(clientEntity)
                    return@runBlocking clientResponse

                } catch (ex: Exception) {
                    print("ClientService |updateClient| ERROR: $ex")
                    clientResponse.error = ex.message
                    return@runBlocking clientResponse
                }
            }
        } else {
            clientResponse.error = "ClientService ERROR: The id can not be null"
            return clientResponse
        }
    }

    override fun findAllClientByDateRegister(): MutableList<ClientDto?>? {
        return runBlocking {
            try {
                val clientsEntity = clientRepository.findAllClientByDateRegister()

                if (clientsEntity == null) {
                    return@runBlocking clientsEntity
                }

                val clientListDto = convertAllClientsToDto(clientsEntity)
                return@runBlocking clientListDto
            } catch (ex: Exception) {
                print("ClientService |findAllClientByDateRegister| ERROR: $ex")
                return@runBlocking null
            }
        }
    }

    override fun findByIdClient(idClient: Long?): ClientByIdResponse? {
        val clientResponse = ClientByIdResponse(null, null)
        val clientMutableList = mutableListOf<ClientDto?>()

        if (idClient != null) {
            return runBlocking {
                try {
                    val client = clientRepository.findByIdClient(idClient)

                    if (client == null) {
                        clientResponse.error = "The client does not exist"
                        return@runBlocking clientResponse
                    }

                    clientMutableList.add(client?.toDto())
                    clientResponse.client = clientMutableList
                    return@runBlocking clientResponse

                } catch (ex: Exception) {
                    print("ClientService |findClientById| ERROR: $ex")
                    clientResponse.error = ex.message
                    return@runBlocking clientResponse
                }

            }
        } else {
            clientResponse.error = "ClientService ERROR: The id can not be null"
            return clientResponse
        }
    }

    override fun findClientByParam(key: String, param: String?): ClientByParamResponse? {
        val clientResponse = ClientByParamResponse(null, null)

        return runBlocking {
            try {

                val clientsEntity = clientRepository.findClientByParam(key, param.toString())

                if (clientsEntity == null || clientsEntity.isEmpty()) {
                    clientResponse.error = "The $key: $param does not exist"
                    return@runBlocking clientResponse
                }

                clientResponse.client = convertAllClientsToDto(clientsEntity)
                return@runBlocking clientResponse
            } catch (ex: Exception) {
                print("ClientService |findClientByParam| ERROR: $ex")
                clientResponse.error = ex.message
                return@runBlocking clientResponse
            }
        }
    }

    private fun convertAllClientsToDto(clientsEntity: List<IClientEntity>): MutableList<ClientDto?> {
        val clientDtoList = mutableListOf<ClientDto?>()

        clientsEntity.forEach {
            clientDtoList.add(it?.toDto())
        }

        return clientDtoList
    }
}