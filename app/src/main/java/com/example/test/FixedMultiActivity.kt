package com.example.test

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mybase.baserecycler.selectable.OnSelectChangeListener
import com.example.mybase.baserecycler.selectable.SelectMode

open class FixedMultiActivity : AppCompatActivity() {

    protected val dataList = getTestDataList()
    protected var adapter : MyMultiAdapter = MyMultiAdapter(this, dataList, SelectMode.FIXED_MULTI)
    private var checkSelectAll : CheckBox ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_long_click_multi)
        checkSelectAll = findViewById<CheckBox>(R.id.check_select_all)
        checkSelectAll?.setOnClickListener {
            val isChecked = checkSelectAll!!.isChecked
            adapter.selectAll(isChecked)
        }
        findViewById<Button>(R.id.btn_complete).setOnClickListener {
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
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.setOnSelectChangeListener(object : OnSelectChangeListener {
            override fun onSelectChange(position: Int, isSelected: Boolean) {
                val toast: String = if (isSelected) "check " else "uncheck ";
                Toast.makeText(
                    this@FixedMultiActivity, toast + dataList[position].name,
                    Toast.LENGTH_SHORT
                ).show()
                checkSelectAll?.isChecked = adapter.isSelectAll()
            }
        })
    }

    private fun getTestDataList() : List<TestBean> {
        return MutableList<TestBean>(10, init = {
            TestBean("code$it", "name$it")
        })
    }
}
