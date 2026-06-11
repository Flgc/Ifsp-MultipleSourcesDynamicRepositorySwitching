package biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.datasource.DataStoreManager
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.repositoryprovider.RepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val dataStoreManager: DataStoreManager,
    private val repositoryProvider: RepositoryProvider
) : ViewModel() {

    private val _currentSource = MutableStateFlow(DataStoreManager.SOURCE_ROOM)
    val currentSource: StateFlow<Int> = _currentSource.asStateFlow()

    init {
        viewModelScope.launch {
            dataStoreManager.sourceFlow.collect { source ->
                _currentSource.value = source
                applySource(source)
            }
        }
    }

    fun switchSource(source: Int) {
        viewModelScope.launch {
            dataStoreManager.saveSource(source)
        }
    }

    private fun applySource(source: Int) {
        when (source) {
            DataStoreManager.SOURCE_ROOM -> repositoryProvider.switchToRoom()
            DataStoreManager.SOURCE_REMOTE -> repositoryProvider.switchToRemote()
            DataStoreManager.SOURCE_FIREBASE -> repositoryProvider.switchToFirebase()
        }
    }
}