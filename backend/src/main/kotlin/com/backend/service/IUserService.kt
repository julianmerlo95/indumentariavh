package com.backend.service

import com.backend.domain.reponses.UsertByIdResponse
import com.backend.domain.dto.user.UserDto
import com.backend.domain.serializable.UserSerializable

interface IUserService {

    fun saveUser(userSerializable: UserSerializable): String?
    fun findByIdUser(idUser: Long?): UsertByIdResponse?
    fun findAllUsersByDateRegister(): MutableList<UserDto?>?
}