package biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.domain.repository

import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getAllTransactions(): Flow<List<Transaction>>
}