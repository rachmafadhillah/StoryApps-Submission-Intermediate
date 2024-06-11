package com.dicoding.picodiploma.loginwithanimation.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.loginwithanimation.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val photoUrl = intent.getStringExtra("photoUrl")
        val lat = intent.getStringExtra("lat")
        val lon = intent.getStringExtra("lon")

        name?.let { binding.tvDetailName.text = it }
        lat?.let { binding.tvDetailLat.text = "Latitude: $it" }
        lon?.let { binding.tvDetailLon.text = "Longitude: $it" }
        description?.let { binding.tvDetailDescription.text = it }
        photoUrl?.let {
            Glide.with(this)
                .load(it)
                .into(binding.ivDetailPhoto)
        }
    }
}