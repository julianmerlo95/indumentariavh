package com.backend.domain.reponses

import com.backend.domain.dto.ClientDto

data class ClientByIdResponse(
    var client: MutableList<ClientDto?>?,
    var error: String?,
)

data class ClientByParamResponse(
    var client: MutableList<ClientDto?>?,
    var error: String?,
)

data class ClientStringResponse(
    var client: String?,
    var error: String?,
)