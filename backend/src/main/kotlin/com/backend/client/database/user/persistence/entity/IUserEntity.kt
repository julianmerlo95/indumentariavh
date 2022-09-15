package com.backend.client.database.persistence.entity

import com.backend.domain.dto.user.UserDto
import java.time.LocalDateTime

interface IUserEntity {
    val idUser: Int
    val userName: String
    val mail: String
    val password: String
    val name: String
    val lastName: String
    val dateOfBirth: String
    val dateRegister: LocalDateTime
    val idRole: Int
    var isEnable: Int
    fun toDto(): UserDto?
}