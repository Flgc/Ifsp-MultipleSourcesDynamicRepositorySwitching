package biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.datasource.DataStoreManager
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.presentation.viewmodel.SettingsViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = koinViewModel()
) {
    val currentSource by viewModel.currentSource.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Configurações") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Origem atual:", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = when (currentSource) {
                            DataStoreManager.SOURCE_ROOM -> "📀 Room (Local)"
                            DataStoreManager.SOURCE_REMOTE -> "🌐 API REST"
                            DataStoreManager.SOURCE_FIREBASE -> "🔥 Firebase Firestore"
                            else -> "Desconhecido"
                        },
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Selecione a fonte de dados:", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { viewModel.switchSource(DataStoreManager.SOURCE_ROOM) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (currentSource == DataStoreManager.SOURCE_ROOM)
                        MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("Room (Local)")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { viewModel.switchSource(DataStoreManager.SOURCE_REMOTE) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (currentSource == DataStoreManager.SOURCE_REMOTE)
                        MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("API REST")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { viewModel.switchSource(DataStoreManager.SOURCE_FIREBASE) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (currentSource == DataStoreManager.SOURCE_FIREBASE)
                        MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("Firebase Firestore")
            }
        }
    }
}