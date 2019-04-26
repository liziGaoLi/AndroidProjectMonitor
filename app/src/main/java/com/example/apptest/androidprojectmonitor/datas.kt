package com.example.apptest.androidprojectmonitor

data class SpinnerData(var data: String, var selected: Boolean)

data class ContactsData(var name: String, var pinyinChar: Char, var idCode: String, var location: String, var phone: String) : Comparable<ContactsData> {
    override fun compareTo(other: ContactsData) = pinyinChar.toInt() - other.pinyinChar.toInt()


}