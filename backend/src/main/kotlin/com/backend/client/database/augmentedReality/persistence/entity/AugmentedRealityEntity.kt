package com.backend.client.database.augmentedReality.persistence.entity

import com.backend.client.database.augmentedReality.AugmentedRealityTable
import com.backend.domain.dto.augmentedReality.AugmentedRealityDto
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AugmentedRealityEntity(id: EntityID<Int>) : Entity<Int>(id), IAugmentedRealityEntity {
    companion object : EntityClass<Int, AugmentedRealityEntity>(AugmentedRealityTable)

    override val idAugmentedReality = id.value
    override var imageUrl by AugmentedRealityTable.imageUrl
    override var idProduct by AugmentedRealityTable.idProduct
    override var dateRegister by AugmentedRealityTable.dateRegister

    override fun toDto(): AugmentedRealityDto? {
        return AugmentedRealityDto(
            idAugmentedReality.toString(),
            imageUrl,
            idProduct,
            dateRegister,
        )
    }
}