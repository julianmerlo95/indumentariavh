package com.backend.domain.reponses

import com.backend.domain.dto.CurrentAccountClientDto

data class CurrentAccountClientByIdResponse(
    var currentAccountClient: MutableList<CurrentAccountClientDto?>?,
    var error: String?,
)

data class CurrentAccountClientByIdParamResponse(
    var currentAccountClient: MutableList<CurrentAccountClientDto?>?,
    var error: String?,
)

data class CurrentAccountClientStringResponse(
    var currentAccountClient: String?,
    var error: String?,
)