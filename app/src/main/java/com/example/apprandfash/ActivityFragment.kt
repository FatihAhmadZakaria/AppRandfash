package com.example.apprandfash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class ActivityFragment : AppCompatActivity() {
    private lateinit var bottomNav : BottomNavigationView
    private lateinit var fragmente: FragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        replaceFragment(FragmentHome())

        bottomNav = findViewById(R.id.navbar)
        fragmente = findViewById(R.id.fragmentContain)

        bottomNav.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.home -> replaceFragment(FragmentHome())
                R.id.riwayat -> replaceFragment(FragmentRiwayat())
                R.id.setting -> replaceFragment(FragmentSetting())

                else -> {

                }
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContain,fragment)
        fragmentTransaction.commit()
    }
}