package com.backend.domain.dto.user

import java.time.LocalDateTime

data class UserDto(
    val idUser: String,
    val userName: String,
    val mail: String,
    val password: String,
    val name: String,
    val lastName: String,
    val dateOfBirth: String,
    val idRole: Int,
    val dateRegister: LocalDateTime,
    val isEnable: Int,
)

