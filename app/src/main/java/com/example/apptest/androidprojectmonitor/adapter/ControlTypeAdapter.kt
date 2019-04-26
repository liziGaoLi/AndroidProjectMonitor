package com.example.apptest.androidprojectmonitor.adapter

import android.view.View
import com.example.apptest.androidprojectmonitor.R
import com.example.apptest.androidprojectmonitor.SpinnerData
import com.example.apptest.androidprojectmonitor.feature.BaseListAdapter
import kotlinx.android.synthetic.main.item_spinner_selected.view.*

class ControlTypeAdapter : BaseListAdapter<String, ControlTypeAdapter.ControlTypeViewHolder>() {
    override fun createViewHolder(view: View): ControlTypeViewHolder = ControlTypeViewHolder(this, view)

    override fun onBindView(vh: ControlTypeViewHolder, position: Int, item: String) {
        vh.type.text = item
    }

    override fun layoutId() = R.layout.item_spinner_selected

    class ControlTypeViewHolder(adapter: ControlTypeAdapter, view: View) : BaseListAdapter.ViewHolder(adapter, view) {
        val type = view.mType
    }
}