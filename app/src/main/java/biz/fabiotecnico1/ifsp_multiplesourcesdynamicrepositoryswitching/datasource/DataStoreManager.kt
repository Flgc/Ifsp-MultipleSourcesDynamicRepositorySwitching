package biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

class DataStoreManager(context: Context) {
    private val dataStore = context.dataStore

    companion object {
        private val SOURCE_KEY = intPreferencesKey("data_source")
        const val SOURCE_ROOM = 0
        const val SOURCE_REMOTE = 1
        const val SOURCE_FIREBASE = 2
    }

    val sourceFlow: Flow<Int> = dataStore.data.map { preferences ->
        preferences[SOURCE_KEY] ?: SOURCE_ROOM
    }

    suspend fun saveSource(source: Int) {
        dataStore.edit { preferences ->
            preferences[SOURCE_KEY] = source
        }
    }
}