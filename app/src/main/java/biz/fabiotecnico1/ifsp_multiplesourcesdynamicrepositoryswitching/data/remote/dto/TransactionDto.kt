package biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.data.remote.dto

import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.domain.model.Transaction
import java.util.Date

data class TransactionDto(
    val id: String,
    val description: String,
    val amount: Double,
    val timestamp: Long
) {
    fun toDomain() = Transaction(id, description, amount, Date(timestamp))
}