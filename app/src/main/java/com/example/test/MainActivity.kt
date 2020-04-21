package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_long_click_multi).setOnClickListener { v -> startActivity(
            Intent(this, LongClickMultiActivity::class.java)
        ) }
        findViewById<Button>(R.id.btn_fixed_multi).setOnClickListener { v -> startActivity(
            Intent(this, FixedMultiActivity::class.java)
        ) }
        findViewById<Button>(R.id.btn_fixed_single).setOnClickListener { v -> startActivity(
            Intent(this, FixedSingleActivity::class.java)
        ) }
    }

}
