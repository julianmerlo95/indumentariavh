package com.backend.client.database.product

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNotNull
import org.jetbrains.exposed.sql.javatime.datetime

object ProductTable : IdTable<String>("product") {
    val idProduct = varchar("id_product", 250)
    override val id: Column<EntityID<String>> = idProduct.entityId()

    val name =varchar("name", 100)
    val description = varchar("description", 250).default("no_description")
    val colour = varchar("colour", 250)
    val waist = varchar("waist", 100)
    val quantity = integer("quantity")
    val purchasePrice = varchar("purchase_price", 250)
    val salePrice = varchar("sale_price", 250)
    val isAugmentedReality = integer("is_augmented_reality").default(0)
    val isEnable = integer("is_enable")
    val dateRegister = datetime("date_register")
}