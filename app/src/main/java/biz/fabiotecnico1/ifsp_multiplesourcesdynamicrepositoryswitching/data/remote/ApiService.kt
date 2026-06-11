package biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.data.remote
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.data.remote.dto.TransactionDto

import retrofit2.http.GET

interface ApiService {
    @GET("transactions") // Será utilizado um endpoint real ou mock
    suspend fun getTransactions(): List<TransactionDto>
}