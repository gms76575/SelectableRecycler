package com.gengms.baserecycler.selectable

import android.view.View
import android.widget.CompoundButton
import com.gengms.baserecycler.BaseViewHolder

open class SelectableViewHolder(view : View, checkViewId : Int) : BaseViewHolder(view) {
    val checkBox : CompoundButton = view.findViewById(checkViewId)
}