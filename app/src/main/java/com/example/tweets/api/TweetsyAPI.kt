package com.example.tweets.api

import com.example.tweets.Tweetsy
import com.example.tweets.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsyAPI {


    // this header thing inside means passing the category as the header parameter to filter based on category  we are using suspend as this is an asynchronous request
    @GET("/v3/b/678dab54acd3cb34a8cf328f?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path")category: String) : Response<List<TweetListItem>> // this Header is used for dynamic

    @GET("/v3/b/678dab54acd3cb34a8cf328f?meta=false")
    @Headers("X-JSON-Path: tweets..category") // this Headers is used for static calls
    suspend fun getCategories(): Response<List<String>>



}