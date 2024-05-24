package com.example.apprandfash

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AdapterHome(val context: Context, val dataHome: ArrayList<DataHome>) : RecyclerView.Adapter<AdapterHome.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.rec_home, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataHome[position]
        val getImage = currentItem.imageHome
        Log.d("Ini cek response", getImage.toString())
        Picasso.get()
            .load(getImage)
            .into(holder.imageView)

        holder.produk.text = currentItem.prohome
        holder.harga.text = currentItem.harga

        holder.imageView.setOnClickListener {
            val intent = Intent(context, ActivityOrder::class.java)
            intent.putExtra("image",getImage)
            intent.putExtra("product_name", currentItem.prohome.toString().trim())
            intent.putExtra("price",currentItem.harga.toString().trim())
            intent.putExtra("description",currentItem.deskripsi.toString().trim())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataHome.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageHome)
        val produk: TextView = itemView.findViewById(R.id.productName)
        val harga: TextView = itemView.findViewById(R.id.harga)
    }
}