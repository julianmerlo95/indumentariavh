package com.backend.client.database.product.percistence.entity

import com.backend.client.database.product.ProductTable
import com.backend.domain.dto.product.ProductDto
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ProductEntity(id: EntityID<String>) : Entity<String>(id), IProductEntity {
    companion object : EntityClass<String, ProductEntity>(ProductTable)

    override val idProduct = id.value
    override var name by ProductTable.name
    override var description by ProductTable.description
    override var colour by ProductTable.colour
    override var waist by ProductTable.waist
    override var quantity by ProductTable.quantity
    override var purchasePrice by ProductTable.purchasePrice
    override var salePrice by ProductTable.salePrice
    override var isAugmentedReality by ProductTable.isAugmentedReality
    override var isEnable by ProductTable.isEnable
    override var dateRegister by ProductTable.dateRegister
    override fun toDto(): ProductDto {
        val productDto = ProductDto(
            idProduct,
            name,
            description,
            colour,
            waist,
            quantity,
            purchasePrice,
            salePrice,
            isAugmentedReality,
            isEnable,
            dateRegister
        )
        return productDto
    }
}