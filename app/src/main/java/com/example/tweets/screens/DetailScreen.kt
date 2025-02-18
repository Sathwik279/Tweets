package com.example.tweets.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tweets.viewmodels.DetailViewModel

@Composable
fun DetailScreen(){
    val detailViewModel: DetailViewModel = hiltViewModel() // whenever we use the navigation framework we use hiltViewModel not viewModel to instantiate the viewModel
    val tweets = detailViewModel.tweets.collectAsState()


    if (tweets.value.isEmpty()) {
        Text("No tweets available")
    } else {
        LazyColumn {
            items(tweets.value) { tweet ->
                TweetListItem(tweet = tweet.tweet)
            }
        }
    }
}

@Composable
fun TweetListItem(tweet: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        border = BorderStroke(1.dp,Color(0xFFCCCCCC)),
        content = {
            Text(
                text = tweet,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.body2
            )
        }
    )
}
