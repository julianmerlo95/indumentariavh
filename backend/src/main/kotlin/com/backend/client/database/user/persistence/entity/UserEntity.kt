package com.backend.client.database.persistence.entity

import com.backend.client.database.user.UserTable
import com.backend.domain.dto.user.UserDto
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass

class UserEntity(id: EntityID<Int>) : Entity<Int>(id), IUserEntity {
    companion object : EntityClass<Int, UserEntity>(UserTable)

    override val idUser = id.value
    override var userName by UserTable.userName
    override var mail by UserTable.mail
    override var password by UserTable.password
    override var name by UserTable.name
    override var lastName by UserTable.lastName
    override var dateOfBirth by UserTable.dateOfBirth
    override var dateRegister by UserTable.dateRegister
    override var idRole by UserTable.idRole
    override var isEnable by UserTable.isEnable

    override fun toDto(): UserDto? {
        return UserDto(
            idUser.toString(),
            userName,
            mail,
            password,
            name,
            lastName,
            dateOfBirth,
            idRole,
            dateRegister,
            isEnable
        )
    }
}