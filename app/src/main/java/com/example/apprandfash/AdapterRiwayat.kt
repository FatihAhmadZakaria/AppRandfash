package com.example.apprandfash

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class AdapterRiwayat(val context: Context, val dataRiwayat: ArrayList<DataRiwayat>) : RecyclerView.Adapter<AdapterRiwayat.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.rec_riwayat, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataRiwayat[position]
        holder.invo.text = currentItem.invoice
        holder.total.text = currentItem.total
        holder.tgl.text = currentItem.tgl
        val getImg = currentItem.productImage
        holder.detRiwayat.setOnClickListener {
            val intent= Intent(context, ActivityDetailRiwayat::class.java)
            intent.putExtra("invoice", currentItem.invoice)
            intent.putExtra("total_price", currentItem.total)
            intent.putExtra("date", currentItem.tgl)
            intent.putExtra("product_name", currentItem.produkname)
            intent.putExtra("price", currentItem.price)
            intent.putExtra("description", currentItem.description)
            intent.putExtra("image", getImg)
            intent.putExtra("amount", currentItem.amount)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataRiwayat.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val invo: TextView = itemView.findViewById(R.id.invoice)
        val total: TextView = itemView.findViewById(R.id.total)
        val tgl: TextView = itemView.findViewById(R.id.tgl)
        val detRiwayat: CardView = itemView.findViewById(R.id.cardRiwayat)
    }
}