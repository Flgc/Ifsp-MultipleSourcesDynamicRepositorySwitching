package biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.domain.model.Transaction
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.repositoryprovider.RepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class TransactionViewModel(
    private val repositoryProvider: RepositoryProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        observeRepositoryChanges()
    }

    private fun observeRepositoryChanges() {
        viewModelScope.launch {
            repositoryProvider.currentRepository
                .flatMapLatest { repository ->
                    repository.getAllTransactions().catch { e ->
                        emit(emptyList())
                        _uiState.value = UiState.Error(e.message ?: "Erro desconhecido")
                    }
                }
                .collect { transactions ->
                    _uiState.value = UiState.Success(transactions)
                }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val transactions: List<Transaction>) : UiState()
        data class Error(val message: String) : UiState()
    }
}