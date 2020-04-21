package com.gengms.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gengms.baserecycler.selectable.OnSelectChangeListener

class FixedSingleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fixed_single_select)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val dataList = getTestDataList()
        val adapter = MySingleAdapter(this, dataList)
        recyclerView.adapter = adapter
        adapter.setOnSelectChangeListener(object :
            OnSelectChangeListener {
            override fun onSelectChange(position: Int, isSelected: Boolean) {
                Toast.makeText(this@FixedSingleActivity, "select "+ dataList[position].name,
                    Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getTestDataList() : List<TestBean> {
        return MutableList<TestBean>(10, init = {
            TestBean("code$it", "name$it")
        })
    }
}
