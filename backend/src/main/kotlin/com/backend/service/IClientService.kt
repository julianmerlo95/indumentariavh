package com.backend.service

import com.backend.domain.dto.ClientDto
import com.backend.domain.reponses.ClientByIdResponse
import com.backend.domain.reponses.ClientByParamResponse
import com.backend.domain.reponses.ClientStringResponse
import com.backend.domain.serializable.client.ClientCreateSerializable
import com.backend.domain.serializable.client.ClientUpdateSerializable

interface IClientService {

    fun createClient(clientSerializable: ClientCreateSerializable): ClientStringResponse?
    fun updateClient(idClient: Long?, clientUpdateSerializable: ClientUpdateSerializable): ClientStringResponse?
    fun deleteClient(idClient: Long?): ClientStringResponse?
    fun findAllClientByDateRegister(): MutableList<ClientDto?>?
    fun findByIdClient(idClient: Long?): ClientByIdResponse?
    fun findClientByParam(key: String, param: String?): ClientByParamResponse?
}