package com.backend.controller

import com.backend.domain.dto.ClientDto
import com.backend.domain.dto.user.UserDto
import com.backend.domain.serializable.UserErrorSerializable
import com.backend.domain.serializable.UserSerializable
import com.backend.domain.serializable.client.ClientErrorSerializable
import com.backend.domain.serializable.client.ClientSerializable
import com.backend.routings.GetAllByRegisterDateUsers
import com.backend.routings.GetByIdUser
import com.backend.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.ktor.ext.inject

fun Route.user() {

    val userService by inject<UserService>()

    route("/api/v1/users/user") {
        post {
            try {
                val userSerializable = call.receive<UserSerializable>()
                val response = userService.saveUser(userSerializable)
                call.respond(HttpStatusCode.OK, "$response")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetByIdUser> {
        withContext(Dispatchers.IO) {
            try {
                val idUser = call.parameters["id"]?.toLong()
                val response = userService.findByIdUser(idUser)

                if (response?.user != null) {
                    val responseSerializable = convertAllUsersToSerializable(response?.user)
                    call.respond(responseSerializable)
                }
                val userErrorSerializable = UserErrorSerializable(response?.error)
                call.respond(userErrorSerializable)
            } catch (ex: Exception) {
                throw ex
            }
        }
    }

    get<GetAllByRegisterDateUsers> {
        withContext(Dispatchers.IO) {
            try {
                val response = userService.findAllUsersByDateRegister()
                call.respond(HttpStatusCode.OK, "$response")
            } catch (ex: Exception) {
                throw ex
            }
        }
    }
}

private fun convertAllUsersToSerializable(userDto: MutableList<UserDto?>?): MutableList<UserSerializable?> {
    val userSerializableList = mutableListOf<UserSerializable?>()

    userDto?.forEach {
        userSerializableList.add(
            UserSerializable(
                it?.idUser,
                it?.userName,
                it?.mail,
                it?.password,
                it?.name,
                it?.lastName,
                it?.dateOfBirth,
                it?.idRole,
                it?.isEnable
            )
        )
    }

    return userSerializableList
}