package com.backend.client.database.user

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.javatime.datetime

object UserTable : IdTable<Int>("user") {
    val idUser = integer("id_user")
    override val id: Column<EntityID<Int>> = idUser.entityId()

    val userName = varchar("user_name", 45)
    val mail = varchar("mail", 45)
    val password = varchar("password", 45)
    val name = varchar("name", 45)
    val lastName = varchar("last_name", 45)
    val dateOfBirth = varchar("date_of_birth", 100)
    val dateRegister = datetime("date_register")
    val idRole = integer("id_role")
    val isEnable = integer("is_enable")
}