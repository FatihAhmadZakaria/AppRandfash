package com.example.apprandfash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FragmentRiwayat : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var listHistory : ArrayList<DataRiwayat>
    var db = Firebase.firestore
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_riwayat, container, false)
        recyclerView = view.findViewById(R.id.fragRiwayat)
        listHistory = ArrayList()
        db = Firebase.firestore
        db.collection("order")
            .get()
            .addOnSuccessListener {
                for (document in it) {
                    listHistory.add(
                        (DataRiwayat(
                            document.id as String,
                            document.data["invoice"] as String,
                            "Total bayar" + document.data["total_price"] as String,
                            document.data["date"] as String,
                            document.data["product_name"] as String,
                            document.data["original_price"] as String,
                            document.data["description"] as String,
                            document.data["product_image"] as String,
                            document.data["amount"] as String
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
        val adp = AdapterRiwayat(requireActivity(), listHistory)
        recyclerView.adapter = adp

    }
}