package biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.data.repositoryimpl

import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.data.remote.TransactionRemoteDataSource
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.domain.model.Transaction
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TransactionRepositoryRemoteImpl(
    private val remoteDataSource: TransactionRemoteDataSource
) : TransactionRepository {
    override fun getAllTransactions(): Flow<List<Transaction>> = flow {
        val transactions = remoteDataSource.fetchTransactions()
        emit(transactions)
    }
}