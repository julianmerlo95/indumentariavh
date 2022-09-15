package com.backend.service

import com.backend.domain.dto.CurrentAccountClientDto
import com.backend.domain.reponses.CurrentAccountClientByIdParamResponse
import com.backend.domain.reponses.CurrentAccountClientByIdResponse
import com.backend.domain.serializable.CurrentAccountClientCreateSerializable

interface ICurrentAccountClientService {

    suspend fun createCurrentAccountClient(currentAccountClientCreateSerializable: CurrentAccountClientCreateSerializable): String?
    suspend fun findByIdCurrentAccountClient(idCurrentAccountClient: Int?): CurrentAccountClientByIdResponse?
    suspend fun findAllCurrentAccountClientByDateRegister(): MutableList<CurrentAccountClientDto?>?
    suspend fun findFirstCurrentAccountClientByDateRegister(): MutableList<CurrentAccountClientDto?>?
    suspend fun findCurrentAccountClientByParam(key: String, param: String): CurrentAccountClientByIdParamResponse?
    suspend fun findTodayCurrentAccountClientByDateRegister(): MutableList<CurrentAccountClientDto?>?
}