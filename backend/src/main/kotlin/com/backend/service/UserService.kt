package com.backend.service

import com.backend.client.database.persistence.entity.IUserEntity
import com.backend.client.database.user.repository.UserRepository
import com.backend.domain.dto.ClientDto
import com.backend.domain.reponses.UsertByIdResponse
import com.backend.domain.dto.user.UserDto
import com.backend.domain.serializable.UserSerializable
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

class UserService(
    private val userRepository: UserRepository
) : IUserService {

    override fun saveUser(userSerializable: UserSerializable): String? {
        var userResponse: String? = ""
        return runBlocking {
            try {
                val clientDto = UserDto(
                    idUser = userSerializable.idUser!!,
                    userName = userSerializable.userName!!,
                    mail = userSerializable.mail!!,
                    password = userSerializable.password!!,
                    name = userSerializable.name!!,
                    lastName = userSerializable.lastName!!,
                    dateOfBirth = userSerializable.dateOfBirth!!,
                    idRole = userSerializable.idRole!!,
                    dateRegister = LocalDateTime.now(),
                    isEnable = userSerializable.isEnable!!,
                )

                val response = async { userRepository.saveUser(clientDto) }
                userResponse = response.await()

                return@runBlocking userResponse
            } catch (ex: Exception) {
                print("ClientService |saveClient| ERROR: $ex")
                return@runBlocking userResponse
            }
        }
    }

    override fun findByIdUser(idClient: Long?): UsertByIdResponse? {
        val userResponse = UsertByIdResponse(null, null)
        val userMutableList = mutableListOf<UserDto?>()

        return runBlocking {
            try {
                val user = userRepository.findByIdUser(idClient)

                if (user == null) {
                    userResponse.error = "The user does not exist"
                    return@runBlocking userResponse
                }
                userMutableList.add(user?.toDto())
                userResponse.user = userMutableList
                return@runBlocking userResponse
            } catch (ex: Exception) {
                print("UserService |findUserById| ERROR: $ex")
                userResponse.error = ex.message
                return@runBlocking userResponse
            }
        }
    }

    override fun findAllUsersByDateRegister(): MutableList<UserDto?>? {
        return runBlocking {
            try {
                val userEntity = userRepository.findAllUsersByDateRegister()

                if (userEntity == null) {
                    return@runBlocking userEntity
                }

                val clientListDto = convertAllUsersToDto(userEntity)
                return@runBlocking clientListDto
            } catch (ex: Exception) {
                print("UserService |findAllUsersByDateRegister| ERROR: $ex")
                return@runBlocking null
            }
        }
    }

    private fun convertAllUsersToDto(userEntity: List<IUserEntity>): MutableList<UserDto?> {
        val userDtoList = mutableListOf<UserDto?>()

        userEntity.forEach {
            userDtoList.add(it?.toDto())
        }

        return userDtoList
    }
}