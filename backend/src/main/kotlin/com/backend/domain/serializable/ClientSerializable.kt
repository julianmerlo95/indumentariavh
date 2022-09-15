package com.backend.domain.serializable.client

import kotlinx.serialization.Serializable

@Serializable
data class ClientSerializable(
    val idClient: String?,
    val name: String?,
    val lastName: String?,
    val dateOfBirth: String?,
    val dni: String?,
    val mail: String?,
    val isEnable: Int?,
    val moneyDebt: String?
)

@Serializable
data class ClientCreateSerializable(
    val client: ClientSerializable
)

@Serializable
data class ClientUpdateSerializable(
    val name: String? = null,
    val lastName: String? = null,
    val dateOfBirth: String? = null,
    val mail: String? = null,
    val isEnable: Int? = null,
    val moneyDebt: String? = null
)

@Serializable
data class ClientErrorSerializable(
    val error: String?
)

@Serializable
data class ClientSuccessSerializable(
    val client: String?
)