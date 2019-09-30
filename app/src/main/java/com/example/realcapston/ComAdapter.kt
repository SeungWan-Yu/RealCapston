package com.example.realcapston

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ComAdapter(val comList: ArrayList<ComModel>, val context: Context) :
    RecyclerView.Adapter<ComAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ComAdapter.ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.custom_listview, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(comList[position], context)

//        holder.comId.text("이름 : "+ComModel)
    }

    override fun getItemCount(): Int {
        return comList.count()
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val comId = itemView?.findViewById<TextView>(R.id.m_id)
        val comNumber = itemView?.findViewById<TextView>(R.id.m_number)
        val comOs = itemView?.findViewById<TextView>(R.id.m_os)
        val comHostname = itemView?.findViewById<TextView>(R.id.m_hostname)

        fun bind(com: ComModel?, context: Context) {
            comId?.text = com?.id
            comNumber?.text = com?.number
            comOs?.text = com?.os
            comHostname?.text = com?.hostname
        }

    }
}