package com.backend.client.database.currentAccountClient.repository

import com.backend.client.database.currentAccountClient.persistence.entity.ICurrentAccountClientEntity
import com.backend.domain.dto.CurrentAccountClientDto

interface ICurrentAccountClientRepository {

    suspend fun createCurrentAccountClient(currentAccountClientDto: CurrentAccountClientDto): String?
    suspend fun findByIdCurrentAccountClient(idCurrentAccountClient: Int?): ICurrentAccountClientEntity?
    suspend fun findAllCurrentAccountClientByDateRegister(): List<ICurrentAccountClientEntity>
    suspend fun findFirstCurrentAccountClientByDateRegister(): List<ICurrentAccountClientEntity>
    suspend fun findTodayCurrentAccountClientByDateRegister(): List<ICurrentAccountClientEntity>
    suspend fun findCurrentAccountClientByParam(key: String, param: String): List<ICurrentAccountClientEntity>?
}