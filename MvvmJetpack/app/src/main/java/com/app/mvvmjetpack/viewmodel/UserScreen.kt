package com.app.mvvmjetpack.viewmodel

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.app.mvvmjetpack.data.UsersData
import com.app.mvvmjetpack.repo.UiState

@Composable
fun UserScreen(userViewModel: UserViewModel = viewModel()) {
    val uiState by userViewModel.uiState.collectAsState()
    when(uiState) {
        is UiState.Loading -> LoadingScreen()
        is UiState.Success -> UserList((uiState as UiState.Success).user)
        is UiState.Error -> ErrorResult((uiState as UiState.Error).message)
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
            //CircularProgressIndicator()
        Text(text = "Loading")
    }
}

@Composable
fun UserList(userData : List<UsersData>) {
    LazyColumn(modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(userData) {user ->
            UserItem(user)
        }
    }
}

@Composable
fun UserItem(userData: UsersData) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically) {
       /* Image(painter = rememberAsyncImagePainter(model = userData.avatarUrl), contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .padding(end = 16.dp),
            contentScale = ContentScale.Crop)*/
        Column {
            Text(text = userData.title, style = MaterialTheme.typography.bodyLarge)
            Text(text = "Id is ${userData.userId}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "body is ${userData.body}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun ErrorResult(message : String) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = message, color = MaterialTheme.colorScheme.error)
    }
}