package com.example.apptest.androidprojectmonitor.feature

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.WindowManager
import android.widget.Toast

private fun isLightColor(color: Int): Boolean {
    val r = (color shr 16) and 0xff
    val g = (color shr 8) and 0xff
    val b = color and 0xff
    return r * 0.299 + g * 0.587 + b * 0.114 >= 192
}

fun Activity.transparentStatus(colorRes: Int) {


    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        var option = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isLightColor(colorRes)) {
                option = (option or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            }

        }
        window.decorView.systemUiVisibility = option;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.statusBarColor = Color.TRANSPARENT;
        }
    }
}

fun RecyclerView.moveToPosition(position: Int) {
    adapter?.let {
        if (position >= 0 && it.itemCount > position)
            if (layoutManager is LinearLayoutManager) {
                layoutManager?.let { lm ->
                    val first = (lm as LinearLayoutManager).findFirstVisibleItemPosition()
                    val last = lm.findLastVisibleItemPosition()

                    if (position <= first) {
                        scrollToPosition(position)
                    } else if (position <= last) {
                        val top = getChildAt(position - first).top
                        scrollBy(0, top)
                    } else {
                        scrollToPosition(position)
                        post {
                            val n = position - (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                            if (n in 0 until childCount) {
                                //获取要置顶的项顶部离RecyclerView顶部的距离
                                val top = getChildAt(n).top
                                //最后的移动
                                scrollBy(0, top)
                            }
                        }
                    }
                }
            }
    }

}


fun Activity.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}