package com.example.apprandfash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.apprandfash.databinding.ActivityOrderBinding
import com.squareup.picasso.Picasso

class ActivityOrder : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding
    private lateinit var btOrder: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOrderBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        btOrder= findViewById(R.id.poOrder)
        btOrder.setOnClickListener {
            val gas= Intent(this@ActivityOrder, ActivityOrderDetail::class.java)
            gas.putExtra("image", intent.getStringExtra("image"))
            gas.putExtra("price", intent.getStringExtra("price"))
            gas.putExtra("product_name", intent.getStringExtra("product_name"))
            gas.putExtra("description", intent.getStringExtra("description"))
            startActivity(gas)
        }
        Picasso.get()
            .load(intent.getStringExtra("image"))
            .into(binding.orderImage)
        binding.poNamaProduk.text = intent.getStringExtra("product_name")
        binding.poHarga.text = intent.getStringExtra("price")
        binding.poDetail.text = intent.getStringExtra("description")
    }
}