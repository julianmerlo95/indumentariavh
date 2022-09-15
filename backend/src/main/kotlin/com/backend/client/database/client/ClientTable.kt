package com.backend.client.database.client

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNotNull
import org.jetbrains.exposed.sql.javatime.datetime

object ClientTable : IdTable<Long>("client") {
    val idClient = long("id_client")
    override val id: Column<EntityID<Long>> = idClient.entityId()

    val name = varchar("name", 100)
    val lastName = varchar("last_name", 100)
    val dateOfBirth = varchar("date_of_birth", 100).default("no_date_of_birth")
    val dni = varchar("dni", 100)
    val mail = varchar("mail", 100).default("no_mail")
    val dateRegister = datetime("date_register")
    val isEnable = integer("is_enable")
    val moneyDebt = varchar("money_debt", 250)
}