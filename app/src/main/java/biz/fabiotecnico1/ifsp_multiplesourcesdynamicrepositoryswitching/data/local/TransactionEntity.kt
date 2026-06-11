package biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.domain.model.Transaction
import java.util.Date

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey val id: String,
    val description: String,
    val amount: Double,
    val date: Date
)

fun TransactionEntity.toDomain() = Transaction(id, description, amount, date)
fun Transaction.toEntity() = TransactionEntity(id, description, amount, date)