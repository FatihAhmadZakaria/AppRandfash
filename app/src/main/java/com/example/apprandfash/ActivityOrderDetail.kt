package com.example.apprandfash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.apprandfash.databinding.ActivityOrderDetailBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.LocalDate

class ActivityOrderDetail : AppCompatActivity() {
    private lateinit var binding: ActivityOrderDetailBinding
    private lateinit var btConfirm: Button
    private lateinit var auth: FirebaseAuth
    @SuppressLint("MissingInflatedId", "NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOrderDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val db = Firebase.firestore

        // get image
        Picasso.get()
            .load(intent.getStringExtra("image"))
            .into(binding.detailOrderImg)

        btConfirm= findViewById(R.id.doOrderNow)
        btConfirm.setOnClickListener {
            val numb = (0..9).random()
            val tgl = LocalDate.now()
            val harga = intent.getStringExtra("price")?.toInt()
            val jumlah = binding.doJumlah.text.toString()
            val jum = jumlah.toInt()
            val total = harga!! * jum
            val oreder = hashMapOf(
                "fullname" to binding.doNama.text.toString(),
                "mobile" to binding.doNomor.text.toString(),
                "amount" to binding.doJumlah.text.toString(),
                "address" to binding.doAlamat.text.toString(),
                "note" to binding.doCatatan.text.toString(),
                "invoice" to numb.toString(),
                "date" to tgl.toString(),
                "total_price" to total.toString(),
                "product_name" to intent.getStringExtra("product_name"),
                "description" to intent.getStringExtra("description"),
                "original_price" to intent.getStringExtra("price"),
                "product_image" to intent.getStringExtra("image")
            )
            db.collection("order")
                .add(oreder)
                .addOnSuccessListener {
                    Log.d("Add product ", "Succsess")
                }
                .addOnFailureListener {
                    Log.d("Add product ", "Failed")
                }
            val intent= Intent(this@ActivityOrderDetail, SuccsessOrder::class.java)
            startActivity(intent)
        }
    }
}