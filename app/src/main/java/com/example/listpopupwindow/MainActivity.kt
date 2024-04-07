package com.example.listpopupwindow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ListPopupWindow
import com.example.listpopupwindow.databinding.ActivityMainBinding
import com.nomnom.popupwindow.CustomListPopupWindowBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var _adapter: CustomArrayAdapter
    private var _selectItem = 0

    private lateinit var listPopupWindow: ListPopupWindow
    private lateinit var binding: ActivityMainBinding
    private var list: MutableList<MyModel> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()


    }

    private fun initComponents() {


        initList()
        initClicks()
        initPopWindow()


    }

    private fun initPopWindow() {
        _adapter = CustomArrayAdapter(this, list, _selectItem) {
            _adapter.updateSelectItem(it.id)
            listPopupWindow.dismiss()
        }
        _adapter.updateSelectItem(1)
        listPopupWindow = CustomListPopupWindowBuilder(this)
            .setList(list)
            .setAnchor(binding.btnShowPopup)
            .setBackgroundDrawableRes(R.drawable.round_background)
            .setAdapter(_adapter)
            .build()

        binding.btnShowPopup.setOnClickListener {
            listPopupWindow.show()
        }
    }

    private fun initClicks() {


        binding.btnShowPopup.setOnClickListener {
            showPopUp()
        }
    }

    private fun showPopUp() {

        listPopupWindow.show()

    }

    private fun initList() {


        list = ArrayList()
        list.add(MyModel(1, "Profile"))
        list.add(MyModel(2, "Settings"))
        list.add(MyModel(4, "Notification"))
        list.add(MyModel(5, "Logout"))


    }


}