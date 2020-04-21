package com.gengms.baserecycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

/**
 *  @author gengmingshan
 *  基础ViewHolder 支持itemClick和itemLongClick事件
 */
open class BaseViewHolder(itemView : View) : ViewHolder(itemView) {
    private var mOnItemClickListener : OnItemClickListener?= null
    private var mOnItemLongClickListener : OnItemLongClickListener?= null
    init {
        itemView.setOnClickListener {
            if (mOnItemClickListener != null) {
                mOnItemClickListener!!.onItemClicked(this, adapterPosition)
            }
        }
        itemView.setOnLongClickListener {
            if (mOnItemLongClickListener != null) {
                return@setOnLongClickListener mOnItemLongClickListener!!.onItemLongClicked(this, adapterPosition)
            }
            return@setOnLongClickListener false
        }
    }
    fun setOnItemClickListener(onItemClickListener : OnItemClickListener?) {
        mOnItemClickListener = onItemClickListener
    }
    fun setOnItemLongClickListener(onItemLongClickListener : OnItemLongClickListener?) {
        mOnItemLongClickListener = onItemLongClickListener
    }
}

interface OnItemClickListener {
    fun onItemClicked(viewHolder: BaseViewHolder, position : Int)
}
interface OnItemLongClickListener {
    fun onItemLongClicked(viewHolder: BaseViewHolder, position : Int) : Boolean
}