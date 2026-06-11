package biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.data.remote

import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.domain.model.Transaction

class TransactionRemoteDataSource(
    private val apiService: ApiService
) {
    suspend fun fetchTransactions(): List<Transaction> {
        return apiService.getTransactions().map { it.toDomain() }
    }
}