package com.example.tweets.repository

import com.example.tweets.api.TweetsyAPI
import com.example.tweets.models.TweetListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TweetRepository @Inject constructor(private val tweetsyAPI: TweetsyAPI) {

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> // this is a read only object  and we only expose this to the outside guys so that they cannot modify this but we can modify the _categories inside this by emitting
        get() = _categories


    private val _tweets = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweets: StateFlow<List<TweetListItem>>
        get() = _tweets



    suspend fun getTweets(category: String){
        val response = tweetsyAPI.getTweets("tweets[?(@.category==\"$category\")]")
        if(response.isSuccessful && response.body() !=null){
            //
            _tweets.emit(response.body()!!)
        }
    }

    suspend fun getCategories(){
        val response = tweetsyAPI.getCategories()
        if(response.isSuccessful && response.body() !=null){
            //
            _categories.emit(response.body()!!)
        }
    }



}