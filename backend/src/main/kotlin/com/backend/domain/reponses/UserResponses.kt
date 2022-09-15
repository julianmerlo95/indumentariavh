package com.backend.domain.reponses

import com.backend.domain.dto.user.UserDto

data class UsertByIdResponse(
    var user: MutableList<UserDto?>?,
    var error: String?,
)