package com.example.listpopupwindow

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ListPopupWindow
import com.example.listpopupwindow.databinding.ActivityMainBinding
import com.nomnom.popupwindow.CustomArrayAdapter
import com.nomnom.popupwindow.CustomListPopupWindowBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var _adapter: CustomArrayAdapter
    private var _selectItem = 0

    private lateinit var listPopupWindow: ListPopupWindow
    private lateinit var binding: ActivityMainBinding
    private var list: MutableList<String> = mutableListOf()


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
        _adapter = CustomArrayAdapter(this, list, _selectItem)
        listPopupWindow = CustomListPopupWindowBuilder(this)
            .setList(list)
            .setAnchor(binding.btnShowPopup)
            .setBackgroundDrawableRes(R.drawable.round_background)
            .setOnItemClickListener { _, _, position, _ ->
                Toast.makeText(this, list[position] + " Clicked ", Toast.LENGTH_SHORT).show()
                _adapter.updateSelectItem(position)
                listPopupWindow.dismiss()
            }
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
        list.add("Profile")
        list.add("Settings")
        list.add("Notification")
        list.add("Logout")


    }


}