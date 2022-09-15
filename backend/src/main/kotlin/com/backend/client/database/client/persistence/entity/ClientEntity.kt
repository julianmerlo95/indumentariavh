package com.backend.client.database.client.persistence.entity

import com.backend.client.database.client.ClientTable
import com.backend.domain.dto.ClientDto
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass

class ClientEntity(id: EntityID<Long>) : Entity<Long>(id), IClientEntity {
    companion object : EntityClass<Long, ClientEntity>(ClientTable)

    override val idClient = id.value
    override var name by ClientTable.name
    override var lastName by ClientTable.lastName
    override var dateOfBirth by ClientTable.dateOfBirth
    override var dni by ClientTable.dni
    override var mail by ClientTable.mail
    override var dateRegister by ClientTable.dateRegister
    override var isEnable by ClientTable.isEnable
    override var moneyDebt by ClientTable.moneyDebt

    override fun toDto(): ClientDto? {
        val clientDto = ClientDto(
            idClient.toString(),
            name,
            lastName,
            dateOfBirth,
            dni,
            mail,
            dateRegister,
            isEnable,
            moneyDebt
        )
        return clientDto
    }
}