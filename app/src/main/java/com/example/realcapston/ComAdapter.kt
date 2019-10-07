package com.example.realcapston

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import lecho.lib.hellocharts.model.PieChartData
import lecho.lib.hellocharts.model.SliceValue
import lecho.lib.hellocharts.view.PieChartView


class ComAdapter(val comList: ArrayList<ComModel>, private val clickListener: (ComModel) -> Unit) :
    RecyclerView.Adapter<ComAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflate = LayoutInflater.from(parent.context)
        val view = inflate.inflate(R.layout.custom_listview, parent, false)
        return ViewHolder(view)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(comList[position], clickListener)

    }

    override fun getItemCount(): Int {
        return comList.size
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val comChart = itemView?.findViewById<PieChartView>(R.id.m_chart)
        val comNumber = itemView?.findViewById<TextView>(R.id.m_number)
        val comOs = itemView?.findViewById<TextView>(R.id.m_os)
        val comHostname = itemView?.findViewById<TextView>(R.id.m_hostname)

        @SuppressLint("SetTextI18n")
        fun bind(com: ComModel?, clickListener: (ComModel) -> Unit) {

            val pieData = ArrayList<SliceValue>()

            val memusing = com?.memuse?.toFloat()
            val memtotal = com?.memtotal?.toFloat()

            val result = (memusing?.div(memtotal!!))?.times(100)

            val pieChartData1 = PieChartData(pieData)

            pieData.add(
                SliceValue(
                    com?.memuse?.toFloat()!!,
                    Color.rgb(255, 0, 0)
                ).setLabel("$result%")
            )
            pieData.add(SliceValue(com.memtotal.toFloat(), Color.rgb(255, 140, 0)).setLabel(""))

            pieChartData1.setHasCenterCircle(true).setCenterText1("Memory 사용량")
                .centerText1FontSize = 20
            pieChartData1.setHasLabels(true)
//            pieChartData1.setSlicesSpacing()

            comChart?.pieChartData = pieChartData1
            comNumber?.text = "PC Number : " + com.number
            comOs?.text = "운영체제 : " + com.os
            comHostname?.text = "Hostname : " + com.hostname

            itemView.setOnClickListener { clickListener(com) }
        }

    }
}

//fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
//    itemView.setOnClickListener {
//        event.invoke(adapterPosition, itemViewType)
//    }
//    return this
//}