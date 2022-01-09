package com.example.retrofit.Retrofit_Interface

import com.example.retrofit.model.DataModel
import com.example.retrofit.model.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface UserInterface {

    @GET("users")
    fun listOfUser(): Call<List<UserModel>>
    @POST("users")
    fun saveUser(): Call<DataModel>
}