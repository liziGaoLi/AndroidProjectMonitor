package com.example.apptest.androidprojectmonitor.activity

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import com.example.apptest.androidprojectmonitor.App
import com.example.apptest.androidprojectmonitor.BasedData
import com.example.apptest.androidprojectmonitor.DateUtil
import com.example.apptest.androidprojectmonitor.IdCardVerification.*
import com.example.apptest.androidprojectmonitor.R
import com.example.apptest.androidprojectmonitor.adapter.ControlTypeAdapter
import com.example.apptest.androidprojectmonitor.feature.*
import kotlinx.android.synthetic.main.activity_temporary_control.*
import kotlinx.android.synthetic.main.bottom_sheet_keyborad.*

class TemporaryControl : AppCompatActivity() {
    private var immManager: InputMethodManager? = null;
    lateinit var bottomSheet: BottomSheetBehavior<View>
    private val data = arrayListOf("请选择", "吉林市重点人员")
    private val controlTypeAdapter = ControlTypeAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temporary_control)
        transparentStatus(resources.getColor(R.color.colorToolbarDefault))
        immManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        initView()
        initData()
    }

    private fun initData() {
        controlTypeAdapter.setData(data)
        applyPolice.text = "${App.app().loginBean.userInfo.name}/${App.app().loginBean.userInfo.code}"
        applyTime.text = DateUtil.format("yyyy-MM-dd HH:mm:ss", System.currentTimeMillis())
        idCord.setOnClickListener {
            controlContent.isCursorVisible = false
            immManager?.hideSoftInputFromWindow(controlContent.windowToken, 0)
            if (bottomSheet.state == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
            } else {
                bottomSheet.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        back.setOnClickListener {
            finish()
        }
        controlContent.setOnTouchListener { v, event ->
            controlContent.isCursorVisible = true
            bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
            false
        }
    }

    private fun initView() {
        progressbar.visibility = View.GONE
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
        applyTime.setOnClickListener {
            applyTime.text = DateUtil.format("yyyy-MM-dd HH:mm:ss", System.currentTimeMillis())
        }
        idCord.setOnFocusChangeListener { v, h ->
            toast("$h")
        }
        commit.setOnClickListener {
            if (idCord.text.isEmpty()) {
                toast("请输入身份证号")
                return@setOnClickListener
            }

            val result = IDCardValidate(idCord.text.toString())
            if (result != VALIDITY) {
                val tip = when (result) {
                    VALIDITY -> "该身份证有效！";
                    LACKDIGITS -> "身份证号码长度应该为15位或18位。";
                    LASTOFNUMBER -> "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
                    INVALIDBIRTH -> "身份证出生日期无效。";
                    INVALIDSCOPE -> "身份证生日不在有效范围。";
                    INVALIDMONTH -> "身份证月份无效";
                    INVALIDDAY -> "身份证日期无效";
                    CODINGERROR -> "身份证地区编码错误。";
                    INVALIDCALIBRATION -> "身份证校验码无效，不是合法的身份证号码";
                    else -> ""
                }
                toast(tip)
                return@setOnClickListener
            }



            if (controlContent.text.isEmpty()) {
                toast("请填写布控原因")
                return@setOnClickListener
            }
            if (selectedText == "请选择" || selectedText.isEmpty()) {
                toast("请选择布控类型")
                return@setOnClickListener
            }
            progressbar.visibility = View.VISIBLE

            val okHttp = OkHttpDSL()
            okHttp {
                requestDescription {
                    uri = "http://ztcih9.natappfree.cc/jcyk/system/layoutUserApply/insertLayOutInfo"
                    method = Method.POST
                    mimeType = MimeType.APPLICATION_JSON
                    body = "{\n" +
                            "    \"applyPolicemanId\": \"${App.app().loginBean.userInfo.code}\",\n" +
                            "    \"applyPoliceman\": \"${App.app().loginBean.userInfo.name}\",\n" +
                            "    \"applyDate\": \"${DateUtil.format("yyyy-MM-dd HH:mm:ss", System.currentTimeMillis())}\",\n" +
                            "    \"idNum\": \"${idCord.text}\",\n" +
                            "    \"layoutType\": \"$selectedText\",\n" +
                            "    \"reasonAndPhone\": \"${controlContent.text.toString()}\"\n" +
                            "}"
                }

                callType(BasedData::class.java, {
                    progressbar.visibility = View.GONE
                    toast(it.msg)

                    idCord.text = ""
                    controlType.setSelection(0)
                    controlContent.setText("")
                    applyTime.text = DateUtil.format("yyyy-MM-dd HH:mm:ss", System.currentTimeMillis())
                }, {
                    progressbar.visibility = View.GONE
                    toast("啊呀，出现了一点问题：${it.message}")
                    it.printStackTrace()
                })
            }
        }
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
        backspace.setOnLongClickListener {
            idCord.text = ""
            return@setOnLongClickListener true
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


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        bottomSheet.state = BottomSheetBehavior.STATE_HIDDEN
        immManager?.hideSoftInputFromWindow(controlContent.windowToken, 0)
        return super.onTouchEvent(event)
    }


}
