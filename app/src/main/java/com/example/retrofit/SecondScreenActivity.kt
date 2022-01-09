package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofit.Retrofit_Interface.UserInterface
import com.example.retrofit.model.DataModel
import com.example.retrofit.retrofit_builder.RequestBuilder
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_second_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)
     val username =   intent.extras?.get(MainActivity().extraValue)
        textInputEditText1.text = username.toString()
        summit.setOnClickListener {
            postRequest()
        }
    }

    fun postRequest(){
        val  requestBuilder = RequestBuilder.buildService(UserInterface::class.java)
        val requestCall =  requestBuilder.saveUser()
        requestCall.enqueue(
            object :Callback<DataModel>{
                override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                println(response.body())
                }

                override fun onFailure(call: Call<DataModel>, t: Throwable) {
                }
            }
        )
    }
}


