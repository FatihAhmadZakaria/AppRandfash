package com.example.apprandfash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apprandfash.databinding.ActivityDetailRiwayatBinding
import com.squareup.picasso.Picasso

class ActivityDetailRiwayat : AppCompatActivity() {
    private lateinit var binding: ActivityDetailRiwayatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailRiwayatBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.drInvo.text = intent.getStringExtra("invoice")
        binding.drTgl.text = intent.getStringExtra("date")
        Picasso.get()
            .load(intent.getStringExtra("image"))
            .into(binding.drImgProd)
        binding.drNamaProduk.text = intent.getStringExtra("product_name")
        binding.drHarga.text = intent.getStringExtra("price")
        binding.drJumlah.text = intent.getStringExtra("amount")
        binding.drDescription.text = intent.getStringExtra("description")
        binding.drTotalHarga.text = intent.getStringExtra("total_price")
    }
}