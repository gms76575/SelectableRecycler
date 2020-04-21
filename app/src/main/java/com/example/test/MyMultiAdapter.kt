package com.example.test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mybase.baserecycler.BaseViewHolder
import com.example.mybase.baserecycler.selectable.SelectMode
import com.example.mybase.baserecycler.selectable.SelectableRecyclerAdapter
import com.example.mybase.baserecycler.selectable.SelectableViewHolder

class MyMultiAdapter(context: Context, dataList: List<TestBean>, selectMode: SelectMode)
    : SelectableRecyclerAdapter<TestBean>(context, dataList, selectMode) {
    override fun onCreateSelectableViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectableViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_multi_select, parent, false)
        return MyMultiViewHolder(view, R.id.checkbox)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val viewHolder : MyMultiViewHolder = holder as MyMultiViewHolder
        val item = dataList[position].data
        viewHolder.tvCode.text = item.code
        viewHolder.tvName.text = item.name
    }

}

class MyMultiViewHolder(view : View, checkViewId : Int) : SelectableViewHolder(view, checkViewId) {
    val tvName : TextView = view.findViewById(R.id.tv_name)
    val tvCode : TextView = view.findViewById(R.id.tv_code)
}