package biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
package biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.presentation.viewmodel.TransactionViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun TransactionListScreen(
    navController: NavController,
    viewModel: TransactionViewModel = getViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Transações") },
                actions = {
                    IconButton(onClick = { navController.navigate("settings") }) {
                        Icon(Icons.Default.Settings, contentDescription = "Configurações")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (uiState) {
                is TransactionViewModel.UiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
                is TransactionViewModel.UiState.Success -> {
                    val transactions = (uiState as TransactionViewModel.UiState.Success).transactions
                    if (transactions.isEmpty()) {
                        Text("Nenhuma transação encontrada", modifier = Modifier.padding(16.dp))
                    } else {
                        LazyColumn {
                            items(transactions) { transaction ->
                                Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Text(text = transaction.description, style = MaterialTheme.typography.titleMedium)
                                        Text(text = "Valor: R$ ${transaction.amount}")
                                        Text(text = "Data: ${transaction.date}")
                                    }
                                }
                            }
                        }
                    }
                }
                is TransactionViewModel.UiState.Error -> {
                    Text(
                        text = "Erro: ${(uiState as TransactionViewModel.UiState.Error).message}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}