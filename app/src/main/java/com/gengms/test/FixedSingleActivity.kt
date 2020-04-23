package com.gengms.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.gengms.baserecycler.selectable.OnSelectChangeListener
import kotlinx.android.synthetic.main.activity_fixed_single_select.*

class FixedSingleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fixed_single_select)
        recycler_view.layoutManager = LinearLayoutManager(this)
        val dataList = getTestDataList()
        val adapter = MySingleAdapter(this, dataList)
        recycler_view.adapter = adapter
        adapter.setOnSelectChangeListener(object :
            OnSelectChangeListener {
            override fun onSelectChange(position: Int, isSelected: Boolean) {
                Toast.makeText(this@FixedSingleActivity, "select "+ dataList[position].name,
                    Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getTestDataList() : List<TestBean> {
        return MutableList<TestBean>(10) {
            TestBean("code$it", "name$it")
        }
    }
}
