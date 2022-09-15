package com.backend.client.database.client.repository

import com.backend.client.database.client.ClientTable
import com.backend.client.database.client.persistence.entity.ClientEntity
import com.backend.client.database.client.persistence.entity.IClientEntity
import com.backend.domain.dto.ClientDto
import com.backend.exception.DataBaseException
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greaterEq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

class ClientRepository : IClientRepository {

    override suspend fun createClient(clientDto: ClientDto): String? {
        return transaction {
            try {
                ClientEntity.new(clientDto.idClient.toLong()) {
                    name = clientDto.name
                    lastName = clientDto.lastName
                    dateOfBirth = clientDto.dateOfBirth
                    dni = clientDto.dni
                    mail = clientDto.mail
                    dateRegister = clientDto.dateRegister
                    isEnable = clientDto.isEnable
                    moneyDebt = clientDto.moneyDebt
                }
                return@transaction "The client ${clientDto.idClient} was save correctly in database"
            } catch (ex: Exception) {
                return@transaction "ClientRepository PERSISTENCE ERROR: The client ${clientDto.idClient} was error to saved: ${ex.message}"
            }
        }
    }

    override suspend fun updateClient(clientEntity: IClientEntity, clientDto: ClientDto): String? {
        return transaction {
            clientEntity.name = clientDto.name
            clientEntity.lastName = clientDto.lastName
            clientEntity.dateOfBirth = clientDto.dateOfBirth
            clientEntity.mail = clientDto.mail
            clientEntity.dateRegister = clientDto.dateRegister
            clientEntity.isEnable = clientDto.isEnable
            clientEntity.moneyDebt = clientDto.moneyDebt
            return@transaction "The client ${clientEntity.idClient} was updated correctly in database"
        }
    }

    override suspend fun deleteClient(clientEntity: IClientEntity): String? {
        return transaction {
            clientEntity.isEnable = if (clientEntity.isEnable == 1) 0 else 1
            return@transaction "The client ${clientEntity.idClient} was disable correctly in database"
        }
    }

    override suspend fun findByIdClient(idClient: Long?): IClientEntity? {
        return transaction {
            try {
                if (idClient != null) {
                    return@transaction ClientEntity.findById(idClient)
                }
                return@transaction null
            } catch (ex: Exception) {
                throw DataBaseException(message = "ClientRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}", cause = ex)
            }
        }
    }

    override suspend fun findAllClientByDateRegister(): List<IClientEntity> {
        return transaction {
            try {
                return@transaction ClientEntity.find {
                    (ClientTable.dateRegister greaterEq "2022-08-01T00:00:00")
                }.orderBy(ClientTable.dateRegister to SortOrder.DESC).toList()
            } catch (ex: Exception) {
                throw DataBaseException(message = "ClientRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}", cause = ex)
            }
        }
    }

    override suspend fun findClientByParam(key: String, param: String): List<IClientEntity>? {
        var query: Op<Boolean> = (ClientTable.dateRegister greaterEq "2022-08-01T00:00:00")

        return transaction {
            try {
                if (param != null) {
                    if (key == "dni") {
                        query = query and (ClientTable.dni eq param)
                    } else if (key == "mail") {
                        query = query and (ClientTable.mail eq param)
                    } else if (key == "name") {
                        query = query and (ClientTable.name eq param)
                    } else if (key == "lastName") {
                        query = query and (ClientTable.lastName eq param)
                    } else if (key == "dateOfBirth") {
                        query = query and (ClientTable.dateOfBirth eq param)
                    } else if (key == "isEnable") {
                        query = query and (ClientTable.isEnable eq param.toInt())
                    } else {
                        return@transaction null
                    }
                }

                return@transaction ClientEntity.find { query }.orderBy(ClientTable.dateRegister to SortOrder.DESC).toList()

            } catch (ex: Exception) {
                throw DataBaseException(message = "ClientRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}", cause = ex)
            }
        }
    }
}