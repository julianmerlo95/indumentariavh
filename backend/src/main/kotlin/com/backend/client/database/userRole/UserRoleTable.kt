package com.backend.client.database.userRole

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column


object UserRoleTable : IdTable<Int>("user_role") {
    val idRole = integer("id_role")
    override val id: Column<EntityID<Int>> = idRole.entityId()

    val name = varchar("name", 150)
}