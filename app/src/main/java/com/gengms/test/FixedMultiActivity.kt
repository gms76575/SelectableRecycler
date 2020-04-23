package com.gengms.test

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gengms.baserecycler.selectable.OnSelectChangeListener
import com.gengms.baserecycler.selectable.SelectMode
import kotlinx.android.synthetic.main.activity_long_click_multi.*

open class FixedMultiActivity : AppCompatActivity() {

    protected val dataList = getTestDataList()
    protected lateinit var adapter : MyMultiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_long_click_multi)
        check_select_all?.setOnClickListener {
            val isChecked = check_select_all!!.isChecked
            adapter.selectAll(isChecked)
        }
        btn_complete.setOnClickListener {
            onComplete()
        }
        initRecycler()
    }

    protected open fun onComplete() {
        adapter.onComplete()
        val selectedData = adapter.getSelectedData()
        val sb = StringBuilder()
        for (item in selectedData) {
            if (sb.isNotEmpty()) {
                sb.append(",")
            }
            sb.append(item.name)
        }
        Toast.makeText(
            this@FixedMultiActivity, sb.toString(),
            Toast.LENGTH_SHORT
        ).show()
    }

    protected open fun initRecycler() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        adapter  = MyMultiAdapter(this, dataList, SelectMode.FIXED_MULTI)
        recycler_view.adapter = adapter
        adapter.setOnSelectChangeListener(object :
            OnSelectChangeListener {
            override fun onSelectChange(position: Int, isSelected: Boolean) {
                val toast: String = if (isSelected) "check " else "uncheck "
                Toast.makeText(
                    this@FixedMultiActivity, toast + dataList[position].name,
                    Toast.LENGTH_SHORT
                ).show()
                check_select_all?.isChecked = adapter.isSelectAll()
            }
        })
    }

    private fun getTestDataList() : List<TestBean> {
        return MutableList<TestBean>(10, init = {
            TestBean("code$it", "name$it")
        })
    }
}
