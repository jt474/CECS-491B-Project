package com.example.a491bproject.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a491bproject.R
import com.example.a491bproject.models.Button

class ButtonAdapter(
    private val context: Context,
    private val list: ArrayList<Button>,
    private val listener: OnClickListener
) : RecyclerView.Adapter<ButtonAdapter.ViewHolder>() {

    interface OnClickListener {
        fun onClickListener(data: Int)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iconIV: ImageView = view.findViewById(R.id.iv_main)
        val titleTV: TextView = view.findViewById(R.id.tv_main)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.iconIV.setImageDrawable(ContextCompat.getDrawable(context, data.icon))
        holder.titleTV.text = data.name
        holder.itemView.setOnClickListener {
            listener.onClickListener(position)
        }
    }
}