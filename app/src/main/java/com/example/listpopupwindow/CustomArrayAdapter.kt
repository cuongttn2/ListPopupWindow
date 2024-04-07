package com.nomnom.popupwindow

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.listpopupwindow.R

class CustomArrayAdapter(
    context: Context,
    private val list: List<String>,
    private var selectedItem: Int,
) : ArrayAdapter<String>(context, 0, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_item_option, parent, false)
        }

        val textView = view as TextView
        textView.text = list[position]

        if (position == selectedItem || position == list.size - 1) {
            textView.setTextColor(Color.RED)
        } else {
            textView.setTextColor(Color.BLACK)
        }

        return view
    }

    fun updateSelectItem(id: Int) {
        selectedItem = id
    }
}