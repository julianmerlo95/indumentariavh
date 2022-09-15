package com.backend.client.database.client.repository

import com.backend.client.database.client.persistence.entity.IClientEntity
import com.backend.domain.dto.ClientDto

interface IClientRepository {

    suspend fun createClient(clientDto: ClientDto): String?
    suspend fun updateClient(clientEntity: IClientEntity, clientDto: ClientDto): String?
    suspend fun deleteClient(clientEntity: IClientEntity): String?
    suspend fun findByIdClient(idClient: Long?): IClientEntity?
    suspend fun findAllClientByDateRegister(): List<IClientEntity>
    suspend fun findClientByParam(key: String, param: String): List<IClientEntity>?
}