package com.backend.domain.reponses

import com.backend.domain.dto.ExpenseDto

data class ExpenseByIdResponse(
    var expense: MutableList<ExpenseDto?>?,
    var error: String?,
)

data class ExpensesStringResponses(
    var expense: String?,
    var error: String?,
)