package com.example.tringconnectapp.Interface

import com.example.tringconnectapp.data.PeopleList
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.GET

interface RetroIF {
   // @GET("/api/v1/feeds")
   // suspend fun getUserDetails(): JsonArray

    @get:GET("api/v1/feeds")
    val posts : Call<List<PeopleList?>?>?
    companion object{
        val BASE_URL = "http://th-alb-1338985061.ap-south-1.elb.amazonaws.com"
    }
}