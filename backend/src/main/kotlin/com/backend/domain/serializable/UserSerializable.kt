package com.backend.domain.serializable

import kotlinx.serialization.Serializable

@Serializable
data class UserSerializable(
    val idUser: String?,
    val userName: String?,
    val mail: String?,
    val password: String?,
    val name: String?,
    val lastName: String?,
    val dateOfBirth: String?,
    val idRole: Int?,
    val isEnable: Int? = 1
)

@Serializable
data class UserErrorSerializable(
    val error: String?
)

@Serializable
data class UserSuccessSerializable(
    val error: String?
)