package com.example.apprandfash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.apprandfash.databinding.ActivityTambahProductBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ActivityTambahProduct : AppCompatActivity() {
    private lateinit var binding: ActivityTambahProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTambahProductBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val db = Firebase.firestore

        binding.goStart.setOnClickListener {
            val gas = Intent(this, MainActivity::class.java)
            startActivity(gas)
        }

        binding.tambahButton.setOnClickListener {
            val product = hashMapOf(
                "product_name" to binding.tambahProductName.text.toString().trim(),
                "price" to binding.tambahPrice.text.toString().trim(),
                "image" to binding.tambahImage.text.toString().trim(),
                "description" to binding.tambahDescription.text.toString().trim()
            )
            db.collection("products")
                .add(product)
                .addOnSuccessListener {
                    Log.d("Add product ", "Succsess")
                    Toast.makeText(this, "Add product Success", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Log.d("Add product ", "Failed")
                    Toast.makeText(this, "Add product Failed", Toast.LENGTH_SHORT).show()
                }
        }
    }
}