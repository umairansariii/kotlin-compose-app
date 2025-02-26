package com.example.composeapp.app.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeapp.model.Quote

@Composable
fun HomeScreen() {
    val viewModel = viewModel<HomeViewModel>()
    var quotes by remember { mutableStateOf(emptyList<Quote>()) }

//    LazyColumn(modifier = Modifier.fillMaxSize()) {
//        items(list) {
//            Column(modifier = Modifier.fillMaxWidth()) {
//                Text(text = it.id.toString())
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(text = it.quote)
//            }
//        }
//    }
}