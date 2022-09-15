package com.backend.client.database.augmentedReality

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNotNull
import org.jetbrains.exposed.sql.javatime.datetime

object AugmentedRealityTable : IdTable<Int>("augmented_reality") {
    val idAugmentedReality = AugmentedRealityTable.integer("id_augmented_reality")
    override val id: Column<EntityID<Int>> = idAugmentedReality.entityId()

    val imageUrl = AugmentedRealityTable.varchar("image_url", 250)
    val idProduct = AugmentedRealityTable.varchar("id_product", 250)
    val dateRegister = datetime("date_register")
}