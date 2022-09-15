package com.backend.client.database.billDetail.persistence.entity

import com.backend.client.database.billDetail.BillDetailTable
import com.backend.domain.dto.billDetail.BillDetailDto
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class BillDetailEntity(id: EntityID<Int>) : Entity<Int>(id), IBillDetailEntity {
    companion object : EntityClass<Int, BillDetailEntity>(BillDetailTable)

    override var idBill by BillDetailTable.idBill
    override var quantity by BillDetailTable.quantity
    override var price by BillDetailTable.price
    override var idProduct by BillDetailTable.idProduct
    override var colour by BillDetailTable.colour
    override var waist by BillDetailTable.waist
    override var dateRegister by BillDetailTable.dateRegister

    override fun toDto(): BillDetailDto? {
        val billDetailDto = BillDetailDto(
            idBill,
            quantity,
            price,
            idProduct,
            colour,
            waist,
            dateRegister
        )
        return billDetailDto
    }
}