package com.example.apptest.androidprojectmonitor

data class SpinnerData(var data: String, var selected: Boolean)

data class ContactsData(var name: String, var pinyinChar: Char, var idCode: String, var location: String, var phone: String) : Comparable<ContactsData> {
    override fun compareTo(other: ContactsData): Int {
        if (pinyinChar != '#' && other.pinyinChar == '#')
            return -1
        else if (pinyinChar == '#' && other.pinyinChar != '#') {
            return 1;
        } else if (pinyinChar == '#' && other.pinyinChar == '#') {
            return 0;
        } else {
            return pinyinChar.toInt() - other.pinyinChar.toInt()
        }
    }


}

data class ContactData(
        val attributes: Any,
        val msg: String,
        val obj: List<ContactDataObj>,
        val success: Boolean
)

data class ContactDataObj(
        val createBy: Any,
        val createTime: Any,
        val id: Int,
        val memo: String,
        val name: String,
        val params: Params,
        val phone: String,
        val policeNum: String,
        val remark: Any,
        val searchValue: Any,
        val updateBy: Any,
        val updateTime: Any
)

class Params(
)

data class BasedData(
        val attributes: Any,
        val msg: String,
        val obj: Any,
        val success: Boolean
)