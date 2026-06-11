package biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.repositoryprovider

import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RepositoryProvider(
    private val roomRepo: TransactionRepository,
    private val remoteRepo: TransactionRepository,
    private val firebaseRepo: TransactionRepository
) {
    private val _currentRepository = MutableStateFlow<TransactionRepository>(roomRepo)
    val currentRepository: StateFlow<TransactionRepository> = _currentRepository.asStateFlow()

    fun switchToRoom() {
        _currentRepository.value = roomRepo
    }

    fun switchToRemote() {
        _currentRepository.value = remoteRepo
    }

    fun switchToFirebase() {
        _currentRepository.value = firebaseRepo
    }
}