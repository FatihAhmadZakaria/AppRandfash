package com.example.apprandfash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SuccsessOrder : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_succsess_order)
        Handler().postDelayed({
            val intent = Intent(this@SuccsessOrder, ActivityFragment::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}