package com.example.apprandfash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class FragmentSetting : Fragment() {
    private lateinit var editProfile: Button
    private lateinit var about: Button
    private lateinit var exit: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        editProfile= view.findViewById(R.id.ed_profile)
        about= view.findViewById(R.id.about)
        exit= view.findViewById(R.id.exit)

        editProfile.setOnClickListener {
            val intent = Intent(activity, ActivityEditProfile::class.java)
            activity?.startActivity(intent)
        }
        about.setOnClickListener {
            val intent = Intent(activity, ActivityAbout::class.java)
            activity?.startActivity(intent)
        }
        exit.setOnClickListener {
            activity?.finish()
            System.exit(0)
        }
        return view
    }
}