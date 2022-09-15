package com.backend.client.database.billStatus.persistence.entity

import com.backend.client.database.billType.BillTypeTable
import com.backend.client.database.client.ClientTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class BillStatusEntity(id: EntityID<Int>) : Entity<Int>(id), IBillStatusEntity {
    companion object : EntityClass<Int, BillStatusEntity>(BillTypeTable)

    override val idBillStatus = id.value
    override var name by ClientTable.name
}