package com.backend.domain.reponses

import com.backend.domain.dto.ReportDto

data class ReportByIdResponse(
    var report: ReportDto?,
    var error: String?,
)