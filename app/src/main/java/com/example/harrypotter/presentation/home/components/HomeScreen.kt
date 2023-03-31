package com.example.harrypotter.presentation.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.harrypotter.presentation.home.HomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold {
        Box(modifier = Modifier.fillMaxSize()){
            LazyColumn(){
                item {
                    Spacer(modifier = Modifier.height(it.calculateTopPadding()))
                }
                items(state.characters){
                    SingleCard(name = it.name, image = it.image) {}
                }
                item {
                    Spacer(modifier = Modifier.height(it.calculateBottomPadding()))
                }
            }
            if (state.isLoading){
                CircularProgressIndicator()
            }
        }
    }
}