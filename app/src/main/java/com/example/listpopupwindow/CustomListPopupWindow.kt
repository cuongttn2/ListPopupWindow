package com.nomnom.popupwindow

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import androidx.appcompat.widget.ListPopupWindow
import androidx.core.content.ContextCompat
import com.example.listpopupwindow.MyModel

class CustomListPopupWindowBuilder(private val context: Context) {

    private var list: List<MyModel> = emptyList()
    private var anchor: View? = null
    private var backgroundDrawableRes: Int = 0
    private var horizontalOffsetValue: Int = 0
    private var verticalOffsetValue: Int = 0
    private var adapter: ArrayAdapter<MyModel>? = null

    fun setList(list: List<MyModel>) = apply { this.list = list }
    fun setAnchor(anchor: View) = apply { this.anchor = anchor }
    fun setBackgroundDrawableRes(backgroundDrawableRes: Int) =
        apply { this.backgroundDrawableRes = backgroundDrawableRes }

    fun setHorizontalOffset(horizontalOffsetValue: Int) =
        apply { this.horizontalOffsetValue = horizontalOffsetValue }

    fun setVerticalOffset(verticalOffsetValue: Int) =
        apply { this.verticalOffsetValue = verticalOffsetValue }

    fun setAdapter(adapter: ArrayAdapter<MyModel>) = apply { this.adapter = adapter }

    fun build(): ListPopupWindow {
        val listPopupWindow = ListPopupWindow(context)
        adapter?.let { listPopupWindow.setAdapter(it) }
        listPopupWindow.width = measureContentWidth(adapter)
        listPopupWindow.height = -2
        listPopupWindow.isModal = true
        listPopupWindow.anchorView = anchor
        listPopupWindow.horizontalOffset = horizontalOffsetValue
        listPopupWindow.verticalOffset = verticalOffsetValue
        if (backgroundDrawableRes != 0) {
            listPopupWindow.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    context,
                    backgroundDrawableRes
                )
            )
        }

//        if (itemClickListener != null) {
//            listPopupWindow.setOnItemClickListener(itemClickListener)
//        }

        return listPopupWindow
    }

    private fun measureContentWidth(adapter: ArrayAdapter<MyModel>?): Int {
        adapter ?: return 0
        val measureParentViewGroup = FrameLayout(context)
        var itemView: View? = null
        var maxWidth = 0
        var itemType = 0
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val count = adapter.count
        for (index in 0 until count) {
            val positionType = adapter.getItemViewType(index)
            if (positionType != itemType) {
                itemType = positionType
                itemView = null
            }
            itemView = adapter.getView(index, itemView, measureParentViewGroup)
            itemView.measure(widthMeasureSpec, heightMeasureSpec)

            val itemWidth = itemView.measuredWidth
            if (itemWidth > maxWidth) {
                maxWidth = itemWidth
            }
        }
        return maxWidth
    }
}