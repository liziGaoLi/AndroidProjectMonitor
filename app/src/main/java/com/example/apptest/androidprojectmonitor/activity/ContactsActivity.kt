package com.example.apptest.androidprojectmonitor.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.apptest.androidprojectmonitor.ContactsData
import com.example.apptest.androidprojectmonitor.R
import com.example.apptest.androidprojectmonitor.adapter.ContactAdapter
import com.example.apptest.androidprojectmonitor.feature.transparentStatus
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.activity_contacts.back
import kotlinx.android.synthetic.main.activity_contacts.toMain
import kotlinx.android.synthetic.main.activity_temporary_control.*
import kotlinx.android.synthetic.main.bottom_sheet_contact_select.view.*

class ContactsActivity : AppCompatActivity() {
    private val permissionCallPhoneRequestCode = 100
    lateinit var view: View
    lateinit var bottomSheet: BottomSheetDialog
    private val adapter: ContactAdapter = ContactAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        transparentStatus()
        initView()
        initData()
    }

    private fun initData() {
        val data = ArrayList<ContactsData>();
        data.add(ContactsData("Alice", 'A', "220xxx", "永吉", "10000"))
        data.add(ContactsData("Bob", 'b', "220xxx", "江北", "10001"))
        data.sort()
        adapter.clearAdd(data)
        view.call.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), permissionCallPhoneRequestCode)
                return@setOnClickListener
            }
            bottomSheet.dismiss()
            callPhone()

        }
//        data.add(ContactsData("Alice",'A',"220221199612230122","永吉","10000"))
//        data.add(ContactsData("Alice",'A',"220221199612230122","永吉","10000"))
    }

    private fun callPhone() {
        currentItem?.let {
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse("tel:${it.phone}")))
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == permissionCallPhoneRequestCode && permissions[0] == Manifest.permission.CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (bottomSheet.isShowing) {
                    bottomSheet.dismiss()
                    callPhone()
                }

            } else {
                Toast.makeText(this, "权限不足,请到设置中开启权限", Toast.LENGTH_SHORT).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun initView() {
        view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_contact_select, null, false);
        bottomSheet = BottomSheetDialog(this)
        bottomSheet.setContentView(view)
        contacts.layoutManager = LinearLayoutManager(this)
        contacts.adapter = adapter
        adapter.setOnItemClickListener { position, item ->
            currentItem = adapter.data[position]
            currentItem?.let {
                view.mTitle.text = "${it.name}/${it.idCode} ${it.location}-联络人"
                view.phone.text = it.phone
            }
            bottomSheet.show()
        }

        back.setOnClickListener {
            finish()
        }
        toMain.setOnClickListener {
            finish()
        }
    }

    var currentItem: ContactsData? = null

}