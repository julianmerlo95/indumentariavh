package com.backend.client.database.billType.persistence.entity

import com.backend.client.database.billType.BillTypeTable
import com.backend.client.database.client.ClientTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class BillTypeEntity(id: EntityID<Int>) : Entity<Int>(id), IBillTypeEntity {
    companion object : EntityClass<Int, BillTypeEntity>(BillTypeTable)

    override val idBillType = id.value
    override var name by ClientTable.name
}