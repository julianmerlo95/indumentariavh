package com.backend.client.database.augmentedReality.repository

import com.backend.client.database.augmentedReality.AugmentedRealityTable
import com.backend.client.database.augmentedReality.persistence.entity.AugmentedRealityEntity
import com.backend.client.database.augmentedReality.persistence.entity.IAugmentedRealityEntity
import com.backend.domain.dto.augmentedReality.AugmentedRealityDto
import com.backend.exception.DataBaseException
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greaterEq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

class AugmentedRealityRepository : IAugmentedRealityRepository {

    override suspend fun createAugmentedRealityByProduct(idAugmentedReality: AugmentedRealityDto): String? {
        return transaction {
            try {
                AugmentedRealityEntity.new(idAugmentedReality.idAugmentedReality.toInt()) {
                    imageUrl = idAugmentedReality.imageUrl
                    idProduct = idAugmentedReality.idProduct
                    dateRegister = idAugmentedReality.dateRegister
                }
                return@transaction "The augmentedReality ${idAugmentedReality.idAugmentedReality} was save correctly in database"
            } catch (ex: Exception) {
                return@transaction "AugmentedRealityRepository PERSISTENCE ERROR: The augmentedReality ${idAugmentedReality.idAugmentedReality} was error to saved: ${ex.message}"
            }
        }
    }

    override suspend fun findByIdAugmentedReality(idAugmentedReality: Long?): IAugmentedRealityEntity? {
        return transaction {
            try {
                if (idAugmentedReality != null) {
                    return@transaction AugmentedRealityEntity.findById(idAugmentedReality.toInt())
                }
                return@transaction null
            } catch (ex: Exception) {
                throw DataBaseException(message = "AugmentedRealityRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}", cause = ex)
            }
        }
    }

    override suspend fun findByIdProductAugmentedReality(idAugmentedReality: String?): List<IAugmentedRealityEntity>? {
        var query: Op<Boolean> = (AugmentedRealityTable.dateRegister greaterEq "2022-08-01T00:00:00")

        return transaction {
            try {

                if (idAugmentedReality != null) {
                    query = query and (AugmentedRealityTable.idProduct eq idAugmentedReality)
                } else {
                    return@transaction null
                }

                return@transaction AugmentedRealityEntity.find { query }.orderBy(AugmentedRealityTable.dateRegister to SortOrder.DESC).toList()

            } catch (ex: Exception) {
                throw DataBaseException(message = "AugmentedRealityRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}", cause = ex)
            }
        }
    }
}