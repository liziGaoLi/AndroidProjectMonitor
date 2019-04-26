package com.example.apptest.androidprojectmonitor.activity

import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import butterknife.ButterKnife
import com.example.apptest.androidprojectmonitor.DateUtil
import com.example.apptest.androidprojectmonitor.R
import com.example.apptest.androidprojectmonitor.adapter.ControlTypeAdapter
import com.example.apptest.androidprojectmonitor.feature.transparentStatus
import kotlinx.android.synthetic.main.activity_temporary_control.*
import kotlinx.android.synthetic.main.bottom_sheet_keyborad.*

class TemporaryControl : AppCompatActivity() {
    lateinit var bottomSheet: BottomSheetBehavior<View>
    private val data = arrayListOf("请选择", "吉林市重点人员")
    private val controlTypeAdapter = ControlTypeAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temporary_control)
        transparentStatus()
        ButterKnife.bind(this)
        initView()
        initData()
    }

    private fun initData() {
        controlTypeAdapter.setData(data)
        applyPolice.text = "刘雷 / 204328"
        applyTime.text = DateUtil.format("yyyy-MM-dd HH:mm", System.currentTimeMillis())
        idCord.setOnClickListener {
            if (bottomSheet.state == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
            } else {
                bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        back.setOnClickListener {
            finish()
        }
    }

    private fun initView() {
        bottomSheet = BottomSheetBehavior.from(bottom_sheet)
        controlType.adapter = controlTypeAdapter
        controlType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedText = data[position]
            }

        }
        keyboardOnKeyEvent()
    }

    private fun keyboardOnKeyEvent() {

        key0.setOnClickListener {
            idCord.append("0")
        }
        key1.setOnClickListener {
            idCord.append("1")
        }
        key2.setOnClickListener {
            idCord.append("2")
        }
        key3.setOnClickListener {
            idCord.append("3")
        }
        key4.setOnClickListener {
            idCord.append("4")
        }
        key5.setOnClickListener {
            idCord.append("5")
        }
        key6.setOnClickListener {
            idCord.append("6")
        }
        key7.setOnClickListener {
            idCord.append("7")
        }
        key8.setOnClickListener {
            idCord.append("8")
        }
        key9.setOnClickListener {
            idCord.append("9")
        }
        keyX.setOnClickListener {
            idCord.append("X")
        }
        close_bottom_sheet.setOnClickListener {
            bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
        }
        clear.setOnClickListener {
            idCord.text = ""
        }
        confirm.setOnClickListener {
            bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
        }
        backspace.setOnClickListener {
            val text = idCord.text
            if (text.isNotEmpty())
                idCord.text = text.subSequence(0, text.length - 1)
        }

    }

    lateinit var selectedText: String

    override fun onBackPressed() {
        if (bottomSheet.state == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
        } else
            super.onBackPressed()

    }
}
