package com.example.menu.adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.data.com.example.data.UncompletedOrder
import com.example.v2.R

class UncompleteOrderAdapter (private val context: Activity, private val data: ArrayList<UncompletedOrder>): ArrayAdapter<UncompletedOrder>(context, R.layout.goods_item, data){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var inflater = context.layoutInflater
        var rowView = inflater.inflate(R.layout.goods_item, null, true)

        var name = rowView.findViewById<TextView>(R.id.goods_name)
        var count = rowView.findViewById<TextView>(R.id.count)

        name.text = data[position].name
        count.text = data[position].id.toString()

        return rowView
    }
}