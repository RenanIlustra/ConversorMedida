package com.renan.conversordemedidas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val result = intent.getDoubleExtra("RESULT",0.0)
        val label = intent.getStringExtra("LABEL")

        val tvResult:TextView = findViewById(R.id.tvValue)
        tvResult.text = result.toString()

        val tvLabel:TextView = findViewById(R.id.tvValueLabel)
        tvLabel.text = label

        val btnClose:Button = findViewById(R.id.btnClose)
        btnClose.setOnClickListener { finish() }

    }
}