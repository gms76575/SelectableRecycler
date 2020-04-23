package com.gengms.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_long_click_multi.setOnClickListener {
            startActivity(
            Intent(this, LongClickMultiActivity::class.java)
        ) }
        btn_fixed_multi.setOnClickListener {
            startActivity(
            Intent(this, FixedMultiActivity::class.java)
        ) }
        btn_fixed_single.setOnClickListener {
            startActivity(
            Intent(this, FixedSingleActivity::class.java)
        ) }
    }

}
