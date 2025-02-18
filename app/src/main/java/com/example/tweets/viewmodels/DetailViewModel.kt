package com.example.tweets.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweets.models.TweetListItem
import com.example.tweets.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: TweetRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    val tweets: StateFlow<List<TweetListItem>>
        get() = repository.tweets

    init{
        viewModelScope.launch {
            // saved state handle is used to fetch the category without passing the variables as parameters
            // Nav back stack entry keeps all the arguments in the savedStateHandle
            val category = savedStateHandle.get<String>("category") ?: "Android" //if nothing is avaliable then use Android by default
            repository.getTweets(category)
        }
    }
}