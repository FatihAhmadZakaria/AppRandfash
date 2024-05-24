package com.example.apprandfash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.apprandfash.AdapterSlider.SliderAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FragmentHome : Fragment() {
    lateinit var voSlider : ViewPager
    private lateinit var recyclerView: RecyclerView
    private lateinit var listProduct : ArrayList<DataHome>
    var db = Firebase.firestore
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        voSlider = view.findViewById(R.id.voslider)
// Image
        val arraySlider = ArrayList<Int>()
        arraySlider.add(R.drawable.p1)
        arraySlider.add(R.drawable.img1)
        arraySlider.add(R.drawable.img2)
        arraySlider.add(R.drawable.img3)
        arraySlider.add(R.drawable.img4)

        val sliderAdapter = SliderAdapter(arraySlider, activity)
        voSlider.adapter = sliderAdapter


        recyclerView = view.findViewById(R.id.fragHome)
        listProduct = ArrayList()
        db = Firebase.firestore
        db.collection("products")
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    listProduct.add(
                        (DataHome(
                            document.id as String,
                            document.data["image"] as String,
                            document.data["product_name"] as String,
                            document.data["price"] as String,
                            document.data["description"] as String
                        ))
                    )
                }
                populateData()
            }
        return view
    }
    private fun populateData() {
        val linearlayout = LinearLayoutManager(requireActivity())
        linearlayout.stackFromEnd = true
        linearlayout.reverseLayout = true
        recyclerView.layoutManager = linearlayout
        val adp = AdapterHome(requireActivity(), listProduct)
        recyclerView.adapter = adp

    }
}