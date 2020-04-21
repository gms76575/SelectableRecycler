package com.example.mybase.baserecycler.selectable

fun <T> newSelectableList(dataList : List<T>) : List<Selectable<T>> {
    val selectableList : MutableList<Selectable<T>> = ArrayList()
    for (data : T in dataList) {
        selectableList.add(Selectable(data))
    }
    return selectableList
}