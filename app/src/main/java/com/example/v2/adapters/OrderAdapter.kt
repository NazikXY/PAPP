package com.example.menu.adapters

import android.app.Activity
import android.text.Editable
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.data.com.example.data.OrderItem
import com.example.v2.R
import org.w3c.dom.Text
import kotlin.collections.ArrayList

class OrderAdapter(private val context: Activity, private val data: ArrayList<OrderItem>): ArrayAdapter<OrderItem>(context, R.layout.goods_item, data){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.goods_item, null, true)

        val name = rowView.findViewById<TextView>(R.id.goods_name)
        val count = rowView.findViewById<TextView>(R.id.count)
        val units = rowView.findViewById<TextView>(R.id.tvUnits)
        val price = rowView.findViewById<TextView>(R.id.tvPrice)
        val totalCount = rowView.findViewById<TextView>(R.id.tvTotalCouunt)
        val tvFullPrice = rowView.findViewById<TextView>(R.id.tvFullPrice)

        val orderStatus = rowView.findViewById<TextView>(R.id.tvStatus)

        val fullPrice = data[position].price * data[position].totalCount

        name.text = data[position].name
        count.text = data[position].count.toString()
        units.text = data[position].units
        price.text = data[position].price.toString().plus(" руб./${data[position].units}")
        totalCount.text = data[position].totalCount.toString().plus(" ${data[position].units}")

        tvFullPrice.text = "Всего: ".plus(fullPrice.toString())

        if (data[position].price != 0.0 && data[position].totalCount != 0.0){
            orderStatus.text = "Готово"
        }else{
            orderStatus.text = "Ожидает"
        }

        return rowView
    }

}