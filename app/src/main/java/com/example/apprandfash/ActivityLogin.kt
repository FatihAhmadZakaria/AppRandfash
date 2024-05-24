package com.example.apprandfash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.apprandfash.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ActivityLogin : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.tvDaftar.setOnClickListener {
            val pindah = Intent(this, ActivityRegister::class.java)
            startActivity(pindah)
        }

        auth = FirebaseAuth.getInstance()
        binding.btLogin.setOnClickListener {
            val email = binding.loginEmail.text.toString().trim()
            val passw = binding.loginPassw.text.toString()

            if (email.isEmpty()){
                binding.loginEmail.error = "Email harus diisi"
                binding.loginEmail.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.loginEmail.error = "Email tidak valid!"
                binding.loginEmail.requestFocus()
                return@setOnClickListener
            }
            if (passw.isEmpty()){
                binding.loginPassw.error = "Password harus diisi"
                binding.loginPassw.requestFocus()
                return@setOnClickListener
            }
            LoginFirebase(email,passw)
    }
}

    private fun LoginFirebase(email: String, passw: String) {
        auth.signInWithEmailAndPassword(email,passw)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Welcome to Randfash $email", Toast.LENGTH_SHORT).show()
                    val intent= Intent(this, ActivityFragment::class.java)
                    var uid = auth.currentUser?.uid
                    Log.d("Get UID", uid.toString())
                    val documetref : DocumentReference = db.collection("users").document(uid.toString())
                    val product = hashMapOf(
                        "name" to "1UP",
                        "email" to binding.loginEmail.text.toString().trim(),
                        "password" to binding.loginPassw.text.toString().trim()
                    )
                    documetref.set(product)
                    db.collection("users")
                        .add(product)
                        .addOnSuccessListener {
                            Log.d("Add product ", "Succsess")
                            Toast.makeText(this, "Add product Success", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Log.d("Add product ", "Failed")
                            Toast.makeText(this, "Add product Failed", Toast.LENGTH_SHORT).show()
                        }
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
