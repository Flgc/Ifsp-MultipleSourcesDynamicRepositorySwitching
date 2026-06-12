package biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.di

import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.data.firebase.TransactionFirestoreDataSource
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.data.local.AppDatabase
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.data.remote.RetrofitClient
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.data.remote.TransactionRemoteDataSource
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.data.repositoryimpl.TransactionRepositoryFirebaseImpl
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.data.repositoryimpl.TransactionRepositoryRemoteImpl
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.data.repositoryimpl.TransactionRepositoryRoomImpl
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.datasource.DataStoreManager
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.domain.repository.TransactionRepository
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.repositoryprovider.RepositoryProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.getInstance(context = androidContext()) }
    single { get<AppDatabase>().transactionDao() }
    single { RetrofitClient.apiService }
    single { TransactionRemoteDataSource(apiService = get()) }
    single { TransactionFirestoreDataSource() }
    single<TransactionRepository>(named("room")) {
        TransactionRepositoryRoomImpl(database = get())
    }
    single<TransactionRepository>(named("remote")) {
        TransactionRepositoryRemoteImpl(remoteDataSource = get())
    }
    single<TransactionRepository>(named("firebase")) {
        TransactionRepositoryFirebaseImpl(firestoreDataSource = get())
    }
    single<RepositoryProvider> {
        RepositoryProvider(
            roomRepo = get(named("room")),
            remoteRepo = get(named("remote")),
            firebaseRepo = get(named("firebase"))
        )
    }
    single { DataStoreManager(context = androidContext()) }
}