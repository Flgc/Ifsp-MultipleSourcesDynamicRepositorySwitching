package biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.domain.model

import java.util.Date

data class Transaction(
    val id: String,
    val description: String,
    val amount: Double,
    val date: Date
)