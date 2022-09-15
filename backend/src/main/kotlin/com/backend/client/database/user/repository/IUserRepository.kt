package com.backend.client.database.user.repository

import com.backend.client.database.persistence.entity.IUserEntity
import com.backend.domain.dto.user.UserDto

interface IUserRepository {

    suspend fun saveUser(userDto: UserDto): String?
    suspend fun findByIdUser(idUser: Long?): IUserEntity?
    suspend fun findAllUsersByDateRegister(): List<IUserEntity>
}