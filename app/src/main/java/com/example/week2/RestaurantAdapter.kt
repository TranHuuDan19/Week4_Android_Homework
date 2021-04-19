package com.example.week2

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RestaurantAdapter:RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {
    var data:List<Restaurant> = listOf()
        set(value) {
        field = value
        notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }
    class ViewHolder(val itemView:View):RecyclerView.ViewHolder(itemView){
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val tvAddress = itemView.findViewById<TextView>(R.id.tvAddress)
        val ivPicture = itemView.findViewById<ImageView>(R.id.ivPicture)

        companion object {
            fun from(parent: ViewGroup):ViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.restaurant_item_view,parent,false)
                return ViewHolder(view)
            }
        }
        fun bind(item: Restaurant) {
            tvAddress.text = item.address
            tvName.text = item.name
            Glide.with(itemView.context).load(item.picturePath).into(ivPicture)
        }
    }
}