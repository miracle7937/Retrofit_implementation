package com.example.retrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import com.example.retrofit.Retrofit_Interface.UserInterface
import com.example.retrofit.model.UserModel
import com.example.retrofit.retrofit_builder.RequestBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val extraValue = "USERNAME"
lateinit var indicatorBar: ProgressBar;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        indicatorBar= ProgressBar;
    }

    override fun onResume() {
        super.onResume()
        setItems()
    }

  private fun setItems(){
      indicatorBar.visibility= View.VISIBLE
      val  requestBuilder = RequestBuilder.buildService(UserInterface::class.java)
     val requestCall =  requestBuilder.listOfUser()
      System.out.println("Mira1")
      requestCall.enqueue(object:Callback<List<UserModel>>{
          override fun onResponse(
              call: Call<List<UserModel>>,
              response: Response<List<UserModel>>
          ) {
              System.out.println("Mira"+ response.body())
        if (!response.body().isNullOrEmpty()){

            val users = ArrayList<String>()
            for (a in response.body()!!){
                a.name?. let { users.add(it) }
            }
            setAdapter(users)
        }
          }
          override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
              System.out.println("Mira Error")
          }
      })


   }


    private fun  setAdapter(users:ArrayList<String>){
        val listView = recipe_list_view
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)
        listView.adapter = adapter
        indicatorBar.visibility= View.INVISIBLE
        listView.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, SecondScreenActivity::class.java)
            intent.putExtra(this.extraValue, users[i])
            startActivity(intent)
        }

    }
}