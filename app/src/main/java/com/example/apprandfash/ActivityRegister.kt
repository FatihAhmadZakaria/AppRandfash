package com.example.apprandfash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.apprandfash.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class ActivityRegister : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvLogin.setOnClickListener {
            val pindah = Intent(this, ActivityLogin::class.java)
            startActivity(pindah)
        }

        auth = FirebaseAuth.getInstance()
        binding.btDaftar.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val passw = binding.etPassw.text.toString()

            if (email.isEmpty()){
                binding.etEmail.error = "Email harus diisi"
                binding.etEmail.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.etEmail.error = "Email tidak valid!"
                binding.etEmail.requestFocus()
                return@setOnClickListener
            }
            if (passw.isEmpty()){
                binding.etPassw.error = "Password harus diisi"
                binding.etPassw.requestFocus()
                return@setOnClickListener
            }
            RegisterFirebase(email,passw)
        }
    }

    private fun RegisterFirebase(email: String, passw: String) {
        auth.createUserWithEmailAndPassword(email,passw)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this@ActivityRegister, "Register Succsess", Toast.LENGTH_SHORT).show()
                    val intent= Intent(this@ActivityRegister, ActivityLogin::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this@ActivityRegister, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}