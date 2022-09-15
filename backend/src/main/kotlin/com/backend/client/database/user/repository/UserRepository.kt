package com.backend.client.database.user.repository

import com.backend.client.database.persistence.entity.IUserEntity
import com.backend.client.database.persistence.entity.UserEntity
import com.backend.client.database.user.UserTable
import com.backend.domain.dto.user.UserDto
import com.backend.exception.DataBaseException
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class UserRepository : IUserRepository {

    override suspend fun saveUser(userDto: UserDto): String? {
        return transaction {
            try {
                UserEntity.new(userDto.idUser.toInt()) {
                    userName = userDto.userName
                    mail = userDto.mail
                    password = userDto.password
                    name = userDto.name
                    lastName = userDto.lastName
                    dateOfBirth = userDto.dateOfBirth
                    idRole = userDto.idRole
                    dateRegister = LocalDateTime.now()
                    isEnable = userDto.isEnable
                }
                return@transaction "The user ${userDto.idUser} was save correctly in database"
            } catch (ex: Exception) {
                return@transaction "UserRepository PERSISTENCE ERROR: The client ${userDto.idUser} was error to saved: ${ex.message}"
            }
        }
    }

    override suspend fun findByIdUser(idUser: Long?): IUserEntity? {
        return transaction {
            try {
                if (idUser != null) {
                    return@transaction UserEntity.findById(idUser.toInt())
                }
                return@transaction null
            } catch (ex: Exception) {
                throw DataBaseException(message = "UserRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}", cause = ex)
            }
        }
    }

    override suspend fun findAllUsersByDateRegister(): List<IUserEntity> {
        return transaction {
            try {
                return@transaction UserEntity.find {
                    (UserTable.dateRegister greaterEq "2022-08-01T00:00:00")
                }.orderBy(UserTable.dateRegister to SortOrder.DESC).toList()
            } catch (ex: Exception) {
                throw DataBaseException(
                    message = "UserRepository ERROR SELECT - PERSISTENCE ERROR: ${ex.message}",
                    cause = ex
                )
            }
        }
    }
}