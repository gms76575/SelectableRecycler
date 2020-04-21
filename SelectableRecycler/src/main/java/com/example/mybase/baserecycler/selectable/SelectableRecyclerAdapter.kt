package com.example.mybase.baserecycler.selectable

import android.content.Context
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import com.example.mybase.baserecycler.BaseRecyclerAdapter
import com.example.mybase.baserecycler.BaseViewHolder
import com.example.mybase.baserecycler.OnItemClickListener
import com.example.mybase.baserecycler.OnItemLongClickListener

/**
 * 单选/多选适配器
 */
abstract class SelectableRecyclerAdapter<T>(
    context: Context,
    dataList: List<T>,
    private var selectMode: SelectMode
) : BaseRecyclerAdapter<Selectable<T>>(context, newSelectableList(dataList)),
    OnItemLongClickListener, OnItemClickListener{
    private var selectState: SelectState = SelectState.DOING
    private var singleSelectPosition = 0
    private var mOnSelectChangeListener : OnSelectChangeListener? = null
    private var mOnSelectStateChangeListener : OnSelectStateChangeListener? = null

    init {
        if (selectMode == SelectMode.LONG_CLICK_MULTI) {
            selectState = SelectState.DONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return onCreateSelectableViewHolder(parent, viewType)
    }

    abstract fun onCreateSelectableViewHolder(parent: ViewGroup, viewType: Int): SelectableViewHolder

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val selectableHolder : SelectableViewHolder = holder as SelectableViewHolder
        if (selectState == SelectState.DONE) {
            selectableHolder.checkBox.visibility = View.GONE
            holder.setOnItemLongClickListener(this)
            holder.setOnItemClickListener(mOnItemClickListener)
        } else {
            selectableHolder.checkBox.visibility = View.VISIBLE
            selectableHolder.checkBox.isChecked = dataList[position].selected
            selectableHolder.checkBox.tag = position
            selectableHolder.checkBox.setOnClickListener { v ->
                run {
                    val pos = selectableHolder.checkBox.tag as Int
                    onItemClicked(holder, pos)
                }
            }
            holder.setOnItemLongClickListener(mOnItemLongClickListener)
            holder.setOnItemClickListener(this)
        }
    }

    /**
     * 长按开始选择（多选)
     */
    override fun onItemLongClicked(viewHolder: BaseViewHolder, position: Int): Boolean {
        selectState = SelectState.DOING
        notifyDataSetChanged()
        if (mOnSelectStateChangeListener != null) {
            mOnSelectStateChangeListener!!.onSelectStateChange(SelectState.DOING)
        }
        return true
    }

    override fun onItemClicked(viewHolder: BaseViewHolder, position: Int) {
        if(selectMode == SelectMode.SINGLE) {
            singleSelect(viewHolder, position)
        } else {
            multiSelect(viewHolder, position)
        }
    }

    /**
     * 多选改变item选择状态
     */
    private fun multiSelect(viewHolder: BaseViewHolder, position: Int) {
        val isSelected = dataList[position].selected
        dataList[position].selected = !isSelected
        Handler().post {notifyItemChanged(position)}
        if (mOnSelectChangeListener != null) {
            mOnSelectChangeListener!!.onSelectChange(position, !isSelected)
        }
    }

    /**
     * 单选选中item
     */
    private fun singleSelect(viewHolder: BaseViewHolder, position: Int) {
        dataList[singleSelectPosition].selected = false
        dataList[position].selected = true
        singleSelectPosition = position
        notifyDataSetChanged()
        if (mOnSelectChangeListener != null) {
            mOnSelectChangeListener!!.onSelectChange(position, true)
        }
    }

    /**
     * 点击返回键，在container返回键监听处调用
     */
    fun onBackClicked() : Boolean {
        if (selectMode == SelectMode.LONG_CLICK_MULTI && selectState == SelectState.DOING) {
            selectState = SelectState.DONE
            notifyDataSetChanged()
            if (mOnSelectStateChangeListener != null) {
                mOnSelectStateChangeListener!!.onSelectStateChange(SelectState.DONE)
            }
            return true
        }
        return false
    }

    /**
     * 完成选择时改变选择状态
     */
    fun onSelectComplete() {
        if (selectMode == SelectMode.LONG_CLICK_MULTI && selectState == SelectState.DOING) {
            selectState = SelectState.DONE
            notifyDataSetChanged()
        }
    }

    /**
     * 是否有选中
     */
    fun hasSelectAnything() : Boolean {
        for (item in dataList) {
            if (item.selected) {
                return true
            }
        }
        return false
    }

    /**
     * 是否全选
     */
    fun isSelectAll() : Boolean {
        for (item in dataList) {
            if (!item.selected) {
                return false
            }
        }
        return true
    }

    /**
     * 全选/取消全选
     */
    fun selectAll(selectAll  : Boolean) {
        if (selectMode == SelectMode.SINGLE || selectState == SelectState.DONE) {
            return
        }
        for (item in dataList) {
            item.selected = selectAll
        }
        notifyDataSetChanged()
    }

    /**
     * 获取选中数据
     */
    fun getSelectedData() : List<T> {
        val selected = ArrayList<T>()
        for (item in dataList) {
            if (item.selected) {
                selected.add(item.data)
            }
        }
        return selected
    }

    fun onComplete() {
        if (selectMode == SelectMode.LONG_CLICK_MULTI && selectState == SelectState.DOING) {
            selectState = SelectState.DONE
            notifyDataSetChanged()
        }
    }

    fun setOnSelectChangeListener(listener: OnSelectChangeListener) {
        mOnSelectChangeListener = listener
    }

    fun setOnSelectStateChangeListener(listener: OnSelectStateChangeListener) {
        mOnSelectStateChangeListener = listener
    }
}