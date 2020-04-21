package com.example.mybase.baserecycler

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

open abstract class BaseRecyclerAdapter<T>(
        protected var context: Context,
        protected var dataList : List<T>
    ) : RecyclerView.Adapter<BaseViewHolder>(){
    protected var mOnItemClickListener : OnItemClickListener ?= null
    protected var mOnItemLongClickListener : OnItemLongClickListener ?= null

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.setOnItemClickListener(mOnItemClickListener)
        holder.setOnItemLongClickListener(mOnItemLongClickListener)
    }
    fun setOnItemClickListener(onItemClickListener : OnItemClickListener) {
        mOnItemClickListener = onItemClickListener
    }
    fun setOnItemLongClickListener(onItemLongClickListener : OnItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}