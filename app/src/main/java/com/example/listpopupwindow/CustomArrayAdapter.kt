package com.example.listpopupwindow

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat

class CustomArrayAdapter(
    context: Context,
    private val list: List<MyModel>,
    private var selectedItemID: Int,
    private var onClickItem: (item: MyModel) -> Unit,
) : ArrayAdapter<MyModel>(context, 0, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_item_option, parent, false)
        }
        val item = list[position]
        val textView = view as TextView

        textView.text = item.name

        textView.setOnClickListener {
            onClickItem.invoke(list[position])
        }

        if (item.id == selectedItemID || position == list.size - 1) {
            textView.setTextColor(Color.RED)
        } else {
            textView.setTextColor(Color.BLACK)
        }
        if(position == list.size -1 ){
            textView.setCompoundDrawablesWithIntrinsicBounds(
                ContextCompat.getDrawable(context, R.drawable.baseline_check_24), // drawableStart
                null, // drawableTop
                null, // drawableEnd
                null  // drawableBottom
            )
        }

        return view
    }

    fun updateSelectItem(id: Int) {
        selectedItemID = id
    }
}