package com.backend.domain.reponses

import com.backend.domain.dto.bill.BillDto

data class BillByIdResponse(
    var bill: MutableList<BillDto?>?,
    var error: String?,
)

data class BillByIdParamResponse(
    var bill: MutableList<BillDto?>?,
    var error: String?,
)