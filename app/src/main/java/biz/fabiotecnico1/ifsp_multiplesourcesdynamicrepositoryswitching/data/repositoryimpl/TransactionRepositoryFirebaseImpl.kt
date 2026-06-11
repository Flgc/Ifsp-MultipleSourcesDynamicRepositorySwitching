package biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.data.repositoryimpl

import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.data.firebase.TransactionFirestoreDataSource
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.domain.model.Transaction
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TransactionRepositoryFirebaseImpl(
    private val firestoreDataSource: TransactionFirestoreDataSource
) : TransactionRepository {
    override fun getAllTransactions(): Flow<List<Transaction>> = flow {
        val transactions = firestoreDataSource.getTransactions()
        emit(transactions)
    }
}