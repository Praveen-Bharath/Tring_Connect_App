package com.example.tringconnectapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tringconnectapp.Interface.RetroIF
import com.example.tringconnectapp.adapter.*
import com.example.tringconnectapp.adapter.MyAdapter
import com.example.tringconnectapp.data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle

    private var gridView: GridView? = null
    private var arrayList: ArrayList<PostImages>? = null
    private var myAdapter: MyAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // binding = DataBindingUtil.setContentView(this,R.layout.people_posts)

        gridView = findViewById(R.id.middle_picture2)
        arrayList = ArrayList()
        arrayList = setDetails()
        myAdapter = MyAdapter(applicationContext, arrayList!!)
        gridView?.adapter = myAdapter

        //retrofit()
        val menu = findViewById<ImageButton>(R.id.menu_logo)
        val search = findViewById<ImageButton>(R.id.search_icon)
        val notify = findViewById<ImageButton>(R.id.notification_icon)
        val dot = findViewById<ImageView>(R.id.btn_capture_photo2)
//        val heart = findViewById<ImageButton>(R.id.heart_icon2)

        menu.setOnClickListener {
            it.background.setTint(Color.RED)
            //it.setBackgroundColor(Color.GRAY)
        }
        search.setOnClickListener {
            it.background.setTint(Color.RED)
            //it.setBackgroundResource(R.drawable.ic_baseline_search_24)
        }
        notify.setOnClickListener {
            dot.setImageDrawable(null)
        }
         /*heart.setOnClickListener { v : View ->
            // v.setBackgroundColor(Color.RED)
            // v.resources.also { (R.drawable.ic_heart_filled2x)}
            v.setBackgroundResource(R.drawable.ic_heart_filled2x)
         }*/


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
                R.drawable.post_image2x,
                "Praveen Bharath",
                "Android App Developer",
                "Have some good basic knowledge in Android",
                "#android#kotlin#jetpackcompose",
                "2 hours ago"
            )
        )
        value.add(
            Details(
                R.drawable.post_image22x,
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

    private fun setDetails(): ArrayList<PostImages> {
        var arrayList: ArrayList<PostImages> = ArrayList()
        arrayList.add(PostImages(R.drawable.image_post_2x))
        arrayList.add(PostImages(R.drawable.image_post_22x))
        arrayList.add(PostImages(R.drawable.image_post_32x))
        arrayList.add(PostImages(R.drawable.video_12x))
        return arrayList
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
                var postlist: List<PeopleList>? = response.body() as List<PeopleList>
                var post = arrayOfNulls<String>(postlist!!.size)

                for (i in postlist!!.indices) {
                    post[i] = postlist!![i]!!.name
                }

                var recyclerview2 = findViewById<RecyclerView>(R.id.recyclerview2)

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

