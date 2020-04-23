package com.gengms.test

import android.os.Bundle
import android.view.View
import com.gengms.baserecycler.selectable.OnSelectStateChangeListener
import com.gengms.baserecycler.selectable.SelectMode
import com.gengms.baserecycler.selectable.SelectState
import kotlinx.android.synthetic.main.activity_long_click_multi.*

class LongClickMultiActivity : FixedMultiActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linear_button?.visibility = View.GONE
        adapter.setOnSelectStateChangeListener(object :
            OnSelectStateChangeListener {
            override fun onSelectStateChange(selectState: SelectState) {
                if (SelectState.DOING == selectState) {
                    linear_button?.visibility = View.VISIBLE
                } else {
                    linear_button?.visibility = View.GONE
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
        linear_button?.visibility = View.GONE
    }

}
