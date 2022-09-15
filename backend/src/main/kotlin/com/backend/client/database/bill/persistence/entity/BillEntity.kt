package com.backend.client.database.bill.persistence.entity

import com.backend.client.database.bill.BillTable
import com.backend.domain.dto.bill.BillDto
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class BillEntity(id: EntityID<String>) : Entity<String>(id), IBillEntity {
    companion object : EntityClass<String, BillEntity>(BillTable)

    override val idBill = id.value

    override var createdBillDate by BillTable.createdBillDate
    override var totalAmount by BillTable.totalAmount
    override var totalDiscount by BillTable.totalDiscount
    override var cash by BillTable.cash
    override var credit by BillTable.credit
    override var borrowedCash by BillTable.borrowedCash
    override var idBillType by BillTable.idBillType
    override var idUser by BillTable.idUser
    override var idClient by BillTable.idClient
    override var idBillStatus by BillTable.idBillStatus
    override var dateRegister by BillTable.dateRegister
    override var idPaymentMethod by BillTable.idPaymentMethod

    override fun toDto(): BillDto {
        val billDto = BillDto(
            idBill,
            createdBillDate,
            totalAmount,
            totalDiscount,
            cash,
            credit,
            borrowedCash,
            idBillType,
            idUser,
            idClient,
            idBillStatus,
            dateRegister,
            idPaymentMethod
        )
        return billDto
    }
}