package com.example.project_uas.catatan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_uas.R
import com.example.project_uas.catatan.room.Catat
import kotlinx.android.synthetic.main.adapter_main2.view.*

class CatatAdapter (var catats: ArrayList<Catat>, var listener: OnAdapterListener) :
    RecyclerView.Adapter<CatatAdapter.CatatViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatatViewHolder {
        return CatatViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.adapter_main2,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount() = catats.size

    override fun onBindViewHolder(holder: CatatViewHolder, position: Int) {
        val catat = catats[position]
        holder.view.text_title.text = catat.title
        holder.view.text_title.setOnClickListener {
            listener.onClick(catat)
        }
        holder.view.icon_edit.setOnClickListener {
            listener.onUpdate(catat)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(catat)
        }
    }

    class CatatViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(newList: List<Catat>) {
        catats.clear()
        catats.addAll(newList)
    }

    interface OnAdapterListener {
        fun onClick(catat: Catat)
        fun onUpdate(catat: Catat)
        fun onDelete(catat: Catat)
    }
}