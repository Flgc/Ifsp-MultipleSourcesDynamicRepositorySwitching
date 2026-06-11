package biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.data.repositoryimpl

import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.data.local.AppDatabase
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.data.local.toDomain
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.domain.model.Transaction
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TransactionRepositoryRoomImpl(
    private val database: AppDatabase
) : TransactionRepository {
    override fun getAllTransactions(): Flow<List<Transaction>> {
        return database.transactionDao().getAll().map { entities ->
            entities.map { it.toDomain() }
        }
    }
}