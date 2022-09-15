package com.backend.service

import com.backend.client.database.currentAccountClient.persistence.entity.ICurrentAccountClientEntity
import com.backend.client.database.currentAccountClient.repository.CurrentAccountClientRepository
import com.backend.domain.dto.CurrentAccountClientDto
import com.backend.domain.reponses.CurrentAccountClientByIdParamResponse
import com.backend.domain.reponses.CurrentAccountClientByIdResponse
import com.backend.domain.reponses.CurrentAccountClientStringResponse
import com.backend.domain.serializable.CurrentAccountClientCreateListSerializable
import com.backend.domain.serializable.CurrentAccountClientCreateSerializable
import com.backend.domain.serializable.client.ClientUpdateSerializable
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

class CurrentAccountClientService(
    private val currentAccountClient: CurrentAccountClientRepository,
    private val clientService: ClientService
) : ICurrentAccountClientService {

    override suspend fun createCurrentAccountClient(currentAccountClientCreateSerializable: CurrentAccountClientCreateSerializable): String? {
        val currentAccountClientStringResponse = CurrentAccountClientStringResponse(null, null)

        return runBlocking {
            try {
                val currentAccountClientDto = CurrentAccountClientDto(
                    idClient = currentAccountClientCreateSerializable.currentAccountClient.idClient!!,
                    actualMoneyDebt = currentAccountClientCreateSerializable.currentAccountClient.actualMoneyDebt!!,
                    moneyCredit = currentAccountClientCreateSerializable.currentAccountClient.moneyCredit!!,
                    moneyDebit = currentAccountClientCreateSerializable.currentAccountClient.moneyDebit!!,
                    newMoneyDebt = currentAccountClientCreateSerializable.currentAccountClient.newMoneyDebt!!,
                    dateRegister = LocalDateTime.now(),
                    userName = currentAccountClientCreateSerializable.currentAccountClient.userName!!,
                    createdBillDate = LocalDateTime.now()
                )
                val response = async { currentAccountClient.createCurrentAccountClient(currentAccountClientDto) }
                currentAccountClientStringResponse.currentAccountClient = response.await()
                updateAmountDebt(currentAccountClientCreateSerializable.currentAccountClient)

                return@runBlocking currentAccountClientStringResponse.currentAccountClient
            } catch (ex: Exception) {
                print("CurrentAccountClientService |createCurrentAccountClient| ERROR: $ex")
                currentAccountClientStringResponse.error = ex.message
                return@runBlocking currentAccountClientStringResponse.error
            }
        }
    }

    override suspend fun findByIdCurrentAccountClient(idCurrentAccountClient: Int?): CurrentAccountClientByIdResponse? {
        val currentAccountClientByIdResponse = CurrentAccountClientByIdResponse(null, null)
        val currentAccountMutableList = mutableListOf<CurrentAccountClientDto?>()

        return runBlocking {
            try {
                val currentAccount = currentAccountClient.findByIdCurrentAccountClient(idCurrentAccountClient)

                if (currentAccount == null) {
                    currentAccountClientByIdResponse.error = "The client does not exist"
                    return@runBlocking currentAccountClientByIdResponse
                }

                currentAccountMutableList.add(currentAccount?.toDto())
                currentAccountClientByIdResponse.currentAccountClient = currentAccountMutableList
                return@runBlocking currentAccountClientByIdResponse
            } catch (ex: Exception) {
                print("CurrentAccountClientService |findByIdCurrentAccountClient| ERROR: $ex")
                currentAccountClientByIdResponse.error = ex.message
                return@runBlocking currentAccountClientByIdResponse
            }
        }
    }

    override suspend fun findAllCurrentAccountClientByDateRegister(): MutableList<CurrentAccountClientDto?>? {
        return runBlocking {
            try {
                val currentAccountClientEntity = currentAccountClient.findAllCurrentAccountClientByDateRegister()

                if (currentAccountClientEntity == null) {
                    return@runBlocking currentAccountClientEntity
                }

                val currentAccountClientListDto = convertAllCurrentAccountClientToDto(currentAccountClientEntity)
                return@runBlocking currentAccountClientListDto
            } catch (ex: Exception) {
                print("CurrentAccountClientService |findAllCurrentAccountClientByDateRegister| ERROR: $ex")
                return@runBlocking null
            }
        }
    }

    override suspend fun findFirstCurrentAccountClientByDateRegister(): MutableList<CurrentAccountClientDto?>? {
        return runBlocking {
            try {
                val currentAccountClientEntity = currentAccountClient.findFirstCurrentAccountClientByDateRegister()

                if (currentAccountClientEntity == null) {
                    return@runBlocking currentAccountClientEntity
                }

                val currentAccountClientListDto = convertAllCurrentAccountClientToDto(currentAccountClientEntity)
                return@runBlocking currentAccountClientListDto
            } catch (ex: Exception) {
                print("CurrentAccountClientService |findFirstCurrentAccountClientByDateRegister| ERROR: $ex")
                return@runBlocking null
            }
        }
    }

    override suspend fun findCurrentAccountClientByParam(key: String, param: String): CurrentAccountClientByIdParamResponse? {
            val currentAccountClientByIdParamResponse = CurrentAccountClientByIdParamResponse(null, null)

            return runBlocking {
                try {
                    val clientsEntity = currentAccountClient.findCurrentAccountClientByParam(key, param)

                    if (clientsEntity == null || clientsEntity.isEmpty()) {
                        currentAccountClientByIdParamResponse.error = "The $key: $param does not exist"
                        return@runBlocking currentAccountClientByIdParamResponse
                    }

                    currentAccountClientByIdParamResponse.currentAccountClient = convertAllCurrentAccountClientToDto(clientsEntity)
                    return@runBlocking currentAccountClientByIdParamResponse
                } catch (ex: Exception) {
                    print("BillService |findBillByParam| ERROR: $ex")
                    currentAccountClientByIdParamResponse.error = ex.message
                    return@runBlocking currentAccountClientByIdParamResponse
                }
            }
    }

    override suspend fun findTodayCurrentAccountClientByDateRegister(): MutableList<CurrentAccountClientDto?>? {
        return runBlocking {
            try {
                val billEntity = currentAccountClient.findTodayCurrentAccountClientByDateRegister()

                if (billEntity == null) {
                    return@runBlocking billEntity
                }

                val billListDto = convertAllCurrentAccountClientToDto(billEntity)
                return@runBlocking billListDto
            } catch (ex: Exception) {
                print("BillService |findTodayBillsByDateRegister| ERROR: $ex")
                return@runBlocking null
            }
        }
    }

    private fun convertAllCurrentAccountClientToDto(currentAccountClientToDtoEntity: List<ICurrentAccountClientEntity>): MutableList<CurrentAccountClientDto?> {
        val currentAccountClientDtoList = mutableListOf<CurrentAccountClientDto?>()

        currentAccountClientToDtoEntity.forEach {
            currentAccountClientDtoList.add(it?.toDto())
        }

        return currentAccountClientDtoList
    }

    private fun updateAmountDebt(currentAccountClientCreateListSerializable: CurrentAccountClientCreateListSerializable) {
        val idClientUpdate = currentAccountClientCreateListSerializable.idClient?.toLong()
        val clientUpdateSerializable = ClientUpdateSerializable(moneyDebt = currentAccountClientCreateListSerializable.newMoneyDebt)
        clientService.updateClient(idClientUpdate, clientUpdateSerializable)
    }
}

