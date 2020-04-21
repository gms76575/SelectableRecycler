package com.example.mybase.baserecycler.selectable

import android.view.View
import android.widget.CompoundButton
import com.example.mybase.baserecycler.BaseViewHolder

open class SelectableViewHolder(view : View, checkViewId : Int) : BaseViewHolder(view) {
    val checkBox : CompoundButton = view.findViewById(checkViewId)
}