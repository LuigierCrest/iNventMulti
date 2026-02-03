package com.luigiercrest.inventmulti.ui


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.luigiercrest.presentation.home.HomeViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(backStack: NavBackStack<NavKey>, viewModel: HomeViewModel = koinViewModel(), modifier: Modifier = Modifier) {

    // backstack.remove(NavRoutes.login)

    Screen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("HomeBar") }
                )
            },
            modifier = modifier
        ){
            Text(text = "Home", style = MaterialTheme.typography.headlineLarge)
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(46.dp)
            ) {
                item {
                    Text(text = "Home", style = MaterialTheme.typography.headlineLarge)
                }
                //Tareas que recoge de la opción seleccionada en la pantalla principal y las muestra según el rol.


//                val categorias by viewModel.categorias.collectAsStateWithLifecycle()
//                items(categorías){
//                }

            }
        }
    }

}

@Composable
@Preview
fun HomeScreenPreview(){
//    HomeScreen()
}