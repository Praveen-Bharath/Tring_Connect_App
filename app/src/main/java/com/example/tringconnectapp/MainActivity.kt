package com.example.tringconnectapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tringconnectapp.Interface.RetroIF
import com.example.tringconnectapp.adapter.CourseListAdapter
import com.example.tringconnectapp.adapter.CoursesAdapter
import com.example.tringconnectapp.adapter.CustomAdapter
import com.example.tringconnectapp.adapter.DetailsAdapter
import com.example.tringconnectapp.data.*
import com.example.tringconnectapp.object_list.RetrofitHelper
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //retrofit()

        // getting the recyclerview by its id
        var recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        var recyclerview2 = findViewById<RecyclerView>(R.id.recyclerview2)
        var recyclerview3 = findViewById<RecyclerView>(R.id.recyclerview3)

        // this creates a vertical layout Manager
        recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerview2.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerview3.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<Items>()
        val value = ArrayList<Details>()
        val info = ArrayList<CourseList>()

        val images = arrayOf(
            R.drawable.ic_plus_with_circle_icon_plus_with_circle2x,
            R.drawable.image_mask2x,
            R.drawable.image_mask3x,
            R.drawable.image_mask4x,
            R.drawable.image_mask5x,
            R.drawable.image_mask2x
        )
        val text = arrayOf(
            "",
            "HR team",
            "Creative Bees",
            "TechMads",
            "Cloud 9",
            "Digidots"
        )
        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 0..5) {
            data.add(Items(images[i], text[i]))
        }

        value.add(
            Details(
                R.drawable.profile_avatar2x,
                "Praveen Bharath",
                "Android App Developer",
                "Have some good basic knowledge in Android",
                "#android#kotlin#jetpackcompose",
                "2 hours ago"
            )
        )
        value.add(
            Details(
                R.drawable.profile_avatar2x,
                "Saravanan",
                "IOS App Developer",
                "Have some good basic knowledge in Apple iOS",
                "#swift#ios",
                "3 hours ago"
            )
        )
        info.add(
            CourseList(
                R.drawable.image02,
                "Course 1"
            )
        )
        info.add(
            CourseList(
                R.drawable.image3x,
                "Course 2"
            )
        )
        info.add(
            CourseList(
                R.drawable.image02,
                "Course 1"
            )
        )
        info.add(CourseList(R.drawable.image3x, "Course 2"))


        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)
        val adapter2 = DetailsAdapter(value)
        val adapter3 = CourseListAdapter(info)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
       recyclerview2.adapter = adapter2
        recyclerview3.adapter = adapter3

        var drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    /*    val peoplelist = RetrofitHelper.getInstance().create(RetroIF::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val result = peoplelist.getUserDetails()
            if (result.isJsonArray)
                Log.d("User", result.toString())
        }*/
    }
      private fun retrofit() {
         // val BASE_URL = "http://th-alb-1338985061.ap-south-1.elb.amazonaws.com"
          val retrofit = Retrofit.Builder()
              .baseUrl(RetroIF.BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .build()
          var API = retrofit.create(RetroIF::class.java)
          var call = API.posts
          call?.enqueue(object : Callback<List<PeopleList?>?> {
              override fun onResponse(
                  call: Call<List<PeopleList?>?>,
                  response: Response<List<PeopleList?>?>
              ) {
                  var postlist:List<PeopleList>? = response.body() as List<PeopleList>
                  var post = arrayOfNulls<String>(postlist!!.size)

                  for (i in postlist!!.indices ) {
                      post[i] = postlist!![i]!!.name
                  }
//                  val listView2 :ListView = findViewById(R.id.list_item)

                 // var adapter2 = listView2
  //                var adapter2 = ArrayAdapter<String>(applicationContext,android.R.layout.simple_dropdown_item_1line,post)
    //                listView2.adapter = adapter2
                  var recyclerview2 = findViewById<RecyclerView>(R.id.recyclerview2)
              //    recyclerview2.adapter = adapter2
                 // listView2.adapter = adapter2

              }

              override fun onFailure(call: Call<List<PeopleList?>?>, t: Throwable) {
              }
          })
      }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)

    }
}

