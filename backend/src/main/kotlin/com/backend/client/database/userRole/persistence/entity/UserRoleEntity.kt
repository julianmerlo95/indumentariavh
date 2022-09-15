package com.backend.client.database.userRole.persistence.entity

import com.backend.client.database.userRole.UserRoleTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserRoleEntity(id: EntityID<Int>) : Entity<Int>(id), IUserRoleEntity {
    companion object : EntityClass<Int, UserRoleEntity>(UserRoleTable)

    override val idRole = id.value
    override var name by UserRoleTable.name
}