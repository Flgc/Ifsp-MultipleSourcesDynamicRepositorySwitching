package biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.ui.theme.IfspMultipleSourcesDynamicRepositorySwitchingTheme
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.presentation.screens.SettingsScreen
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.presentation.screens.TransactionListScreen
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.ui.theme.MultiSourceTheme
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext
import biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching.di.appModule

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }
        setContent {
            IfspMultipleSourcesDynamicRepositorySwitchingTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    KoinAndroidContext {
                        AppNavigation()
                    }
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "transactions") {
        composable("transactions") {
            TransactionListScreen(navController = navController)
        }
        composable("settings") {
            SettingsScreen()
        }
    }
}