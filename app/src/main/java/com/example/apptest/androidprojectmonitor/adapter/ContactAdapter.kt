package com.example.apptest.androidprojectmonitor.adapter

import android.view.View
import com.example.apptest.androidprojectmonitor.ContactsData
import com.example.apptest.androidprojectmonitor.R
import com.example.apptest.androidprojectmonitor.feature.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_contacts.view.*

class ContactAdapter : BaseRecyclerViewAdapter<ContactsData, ContactAdapter.ContactViewHolder>() {
    override fun createViewHolder(view: View): ContactViewHolder = ContactViewHolder(this, view)

    override fun layoutId() = R.layout.item_contacts

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        var item = data[position]
        holder.itemView.title.text = "${item.pinyinChar} ${item.name}/${item.idCode} ${item.location}-联络人"
        holder.itemView.phone.text = item.phone
    }

    class ContactViewHolder(adapter: ContactAdapter, view: View) : BaseRecyclerViewAdapter.RecyclerViewHolder(adapter, view) {

    }
}