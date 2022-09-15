package com.backend.client.database.currentAccountClient.persistence.entity

import com.backend.client.database.currentAccountClient.CurrentAccountClientTable
import com.backend.domain.dto.CurrentAccountClientDto
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CurrentAccountClientEntity(id: EntityID<Int>) : Entity<Int>(id), ICurrentAccountClientEntity {
    companion object : EntityClass<Int, CurrentAccountClientEntity>(CurrentAccountClientTable)

    override var idClient by CurrentAccountClientTable.idClient
    override var actualMoneyDebt by CurrentAccountClientTable.actualMoneyDebt
    override var moneyCredit by CurrentAccountClientTable.moneyCredit
    override var moneyDebit by CurrentAccountClientTable.moneyDebit
    override var newMoneyDebt by CurrentAccountClientTable.newMoneyDebt
    override var dateRegister by CurrentAccountClientTable.dateRegister
    override var userName by CurrentAccountClientTable.userName
    override var createdBillDate by CurrentAccountClientTable.createdBillDate

    override fun toDto(): CurrentAccountClientDto {
        val currentAccountClientDto = CurrentAccountClientDto(
            idClient,
            actualMoneyDebt,
            moneyCredit,
            moneyDebit,
            newMoneyDebt,
            dateRegister,
            userName,
            createdBillDate
        )
        return currentAccountClientDto
    }
}