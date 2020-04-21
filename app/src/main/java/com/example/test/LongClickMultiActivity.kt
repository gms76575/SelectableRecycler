package com.example.test

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.example.mybase.baserecycler.selectable.OnSelectStateChangeListener
import com.example.mybase.baserecycler.selectable.SelectMode
import com.example.mybase.baserecycler.selectable.SelectState

class LongClickMultiActivity : FixedMultiActivity() {

    private var linearButton : LinearLayout ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearButton = findViewById<LinearLayout>(R.id.linear_button)
        linearButton?.visibility = View.GONE
        adapter.setOnSelectStateChangeListener(object : OnSelectStateChangeListener {
            override fun onSelectStateChange(selectState: SelectState) {
                if (SelectState.DOING == selectState) {
                    linearButton?.visibility = View.VISIBLE
                } else {
                    linearButton?.visibility = View.GONE
                }
            }
        })
    }

    override fun initRecycler() {
        adapter = MyMultiAdapter(this, dataList, SelectMode.LONG_CLICK_MULTI)
        super.initRecycler()
    }

    override fun onComplete() {
        super.onComplete()
        linearButton?.visibility = View.GONE
    }

}
