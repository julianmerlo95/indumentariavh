package com.backend.service

import com.backend.client.database.augmentedReality.persistence.entity.IAugmentedRealityEntity
import com.backend.client.database.augmentedReality.repository.AugmentedRealityRepository
import com.backend.domain.reponses.AugmentedRealityByIdResponses
import com.backend.domain.reponses.AugmentedRealityListResponses
import com.backend.domain.reponses.AugmentedRealityStringResponses
import com.backend.domain.dto.augmentedReality.AugmentedRealityDto
import com.backend.domain.dto.augmentedReality.AugmentedRealitySerializable
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

class AugmentedRealityService(
    private val augmentedRealityRepository: AugmentedRealityRepository
) : IAugmentedRealityService {

    override suspend fun createAugmentedRealityByProduct(augmentedRealitySerializable: AugmentedRealitySerializable): AugmentedRealityStringResponses? {
        val augmentedRealityResponse = AugmentedRealityStringResponses(null, null)

        return runBlocking {
            try {
                val augmentedRealityDto = AugmentedRealityDto(
                    idAugmentedReality = augmentedRealitySerializable.idAugmentedReality,
                    imageUrl = augmentedRealitySerializable.imageUrl,
                    idProduct = augmentedRealitySerializable.idProduct,
                    dateRegister = LocalDateTime.now()
                )
                val augmentedRealityRepositoryReponse = async { augmentedRealityRepository.createAugmentedRealityByProduct(augmentedRealityDto) }
                augmentedRealityResponse.augmentedReality = augmentedRealityRepositoryReponse.await()

                return@runBlocking augmentedRealityResponse
            } catch (ex: Exception) {
                print("ClientService |saveClient| ERROR: $ex")
                return@runBlocking augmentedRealityResponse
            }
        }
    }

    override fun findByIdAugmentedReality(idAugmentedReality: Long?): AugmentedRealityByIdResponses? {
        val augmentedRealityResponse = AugmentedRealityByIdResponses(null, null)

        return runBlocking {
            try {
                val augmentedReality = augmentedRealityRepository.findByIdAugmentedReality(idAugmentedReality)

                if (augmentedReality == null) {
                    augmentedRealityResponse.error = "The id augmentedReality does not exist"
                    return@runBlocking augmentedRealityResponse
                }

                augmentedRealityResponse.augmentedReality = augmentedReality?.toDto()
                return@runBlocking augmentedRealityResponse
            } catch (ex: Exception) {
                print("AugmentedRealityService |findByIdAugmentedReality| ERROR: $ex")
                augmentedRealityResponse.error = ex.message
                return@runBlocking augmentedRealityResponse
            }
        }
    }

    override fun findByIdProductAugmentedReality(idAugmentedReality: String?): AugmentedRealityListResponses? {
        val augmentedRealityResponse = AugmentedRealityListResponses(null, null)

        return runBlocking {
            try {
                val augmentedRealityEntity = augmentedRealityRepository.findByIdProductAugmentedReality(idAugmentedReality)

                if (augmentedRealityEntity == null || augmentedRealityEntity.isEmpty()) {
                    augmentedRealityResponse.error = "The idProduct augmentedReality does not exist"
                    return@runBlocking augmentedRealityResponse
                }

                val augmentedRealityListDto = convertAllClientsToDto(augmentedRealityEntity)
                augmentedRealityResponse.augmentedRealityList = augmentedRealityListDto

                return@runBlocking augmentedRealityResponse
            } catch (ex: Exception) {
                print("AugmentedRealityService |findByIdProductAugmentedReality| ERROR: $ex")
                augmentedRealityResponse.error = ex.message
                return@runBlocking augmentedRealityResponse
            }
        }
    }

    private fun convertAllClientsToDto(augmentedRealityEntity: List<IAugmentedRealityEntity>?): MutableList<AugmentedRealityDto?> {
        val augmentedRealityList = mutableListOf<AugmentedRealityDto?>()

        augmentedRealityEntity?.forEach {
            augmentedRealityList.add(it?.toDto())
        }

        return augmentedRealityList
    }
}