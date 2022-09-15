package com.backend.controller

import com.backend.domain.dto.augmentedReality.AugmentedRealitySerializable
import com.backend.routings.GetByIdAugmentedReality
import com.backend.routings.GetByIdProductAugmentedReality
import com.backend.service.AugmentedRealityService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.ktor.ext.inject

fun Route.augmentedReality() {

    val augmentedRealityService by inject<AugmentedRealityService>()

    route("/api/v1/augmented-reality") {
        post {
            try {
                val augmentedRealitySerializable = call.receive<AugmentedRealitySerializable>()
                val response = augmentedRealityService.createAugmentedRealityByProduct(augmentedRealitySerializable)
                call.respond(HttpStatusCode.OK, "$response")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByIdAugmentedReality> {
        withContext(Dispatchers.IO) {
            try {
                val idAugmentedReality = call.parameters["id"]?.toLong()
                val response = augmentedRealityService.findByIdAugmentedReality(idAugmentedReality)
                call.respond(HttpStatusCode.OK, "$response")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByIdProductAugmentedReality> {
        withContext(Dispatchers.IO) {
            try {
                val idAugmentedReality = call.parameters["idProduct"]
                val response = augmentedRealityService.findByIdProductAugmentedReality(idAugmentedReality)
                call.respond(HttpStatusCode.OK, "$response")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }


}