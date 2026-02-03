package com.luigiercrest.inventmulti.ui


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.luigiercrest.domain.models.AdminCategory
import com.luigiercrest.domain.models.listAdminCategories
import com.luigiercrest.presentation.home.HomeViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(backStack: NavBackStack<NavKey>, viewModel: HomeViewModel = koinViewModel(), modifier: Modifier = Modifier) {

    Screen {
        Scaffold(
            topBar = {
                TopAppBar(
                    // Colocar el centro de trabajo
                    title = { Text("HomeBar") },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
                )
            },
            modifier = modifier
        ){ innerPadding ->

            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                // Crear una clase con las categorias que deben aparecer según el rol
                //val listaCategorias = MutableList<String>
                item {

                    Spacer(modifier = Modifier.size(4.dp))
                    // Colocar el rol del usuario
                    Text(text = "Categorías", style = MaterialTheme.typography.headlineMedium)
                }

                items(items = listAdminCategories) { adminCategory ->
                    Card(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            //.clickable()

                    ){
                        Text(text = adminCategory.categoria, style = MaterialTheme.typography.headlineSmall)
                    }

                }




            }
        }
    }

}

@Composable
@Preview
fun HomeScreenPreview(){
//    HomeScreen()
}