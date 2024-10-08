package com.example.hostelhub

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.hostelhub.student.StudentLogin
import com.example.hostelhub.warden.WardenLogin

class GlobalLogin : AppCompatActivity() {
    private lateinit var imagePager: ViewPager2
    private lateinit var imageList: List<Int>
    lateinit var btnexit: Button
    lateinit var shareRepo: Button
    lateinit var shareAppTxt: Button
    lateinit var studentLoginBtn: CardView
    lateinit var wardenLoginBtn: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global_login)

        //Exit Button
        btnexit = findViewById(R.id.exitButton)
        btnexit.setOnClickListener() {
            finish()
        }

        //Details
        shareRepo = findViewById(R.id.shareRepo)
        shareRepo.setOnClickListener() {
            val url = "https://jamiahamdard.edu/UserPanel/DisplayPage.aspx?page=ek&ItemID=caq"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse(url), "text/plain")
            val choose = Intent.createChooser(intent, "Share URL")
            startActivity(choose)
        }

        //share app button code
        shareAppTxt = findViewById(R.id.shareAppTxt)
        shareAppTxt.setOnClickListener() {
            val intent = Intent(Intent.ACTION_SEND)
            val url =
                "https://HostelHub.apk"
            intent.type = "text/plain"
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Hey, Checkout this Hostel Hub App. $url"
            )
            val chooser = Intent.createChooser(intent, "Share this app using...")
            startActivity(chooser)
        }

        //student login
        studentLoginBtn = findViewById(R.id.student_login_button)
        studentLoginBtn.setOnClickListener() {
            startActivity(Intent(this, StudentLogin::class.java))
        }

        //warden login
        wardenLoginBtn = findViewById(R.id.warden_login_button)
        wardenLoginBtn.setOnClickListener() {
            startActivity(Intent(this, WardenLogin::class.java))
        }

        //view pager in card view
        imagePager = findViewById(R.id.imagePager)
        imageList = listOf(
            R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3,
            R.drawable.banner4
        )
        val adapter=ImagePagerAdapter(imageList)
        imagePager.adapter=adapter
        startImageSliderTimer()
    }

    private fun startImageSliderTimer() {
        val handler = Handler()
        val updateImageSliderTask = object : Runnable {
            override fun run() {
                val currentPage = imagePager.currentItem
                var nextPage = currentPage + 1
                if (nextPage >= imageList.size) {
                    nextPage = 0;
                }
                imagePager.currentItem = nextPage
                handler.postDelayed(this, 3000)
            }
        }
        handler.postDelayed(updateImageSliderTask, 3000)

    }

    class ImagePagerAdapter(private val imageList: List<Int>) :
        RecyclerView.Adapter<ImagePagerAdapter.ImagePagerViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagePagerViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_item_image_pager, parent, false)
            return ImagePagerViewHolder(view)
        }

        override fun onBindViewHolder(holder: ImagePagerViewHolder, position: Int) {
            val imageRes = imageList[position]
            holder.bind(imageRes)
        }

        override fun getItemCount(): Int {
            return imageList.size
        }

        inner class ImagePagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.imageView)

            fun bind(imageRes: Int) {
                Glide.with(itemView.context)
                    .load(imageRes)
                    .into(imageView)
            }
        }
    }
}
