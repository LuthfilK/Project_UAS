package com.example.project_uas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.project_uas.MainActivity
import com.example.project_uas.R
import com.example.project_uas.catatan.MainActivity2
import kotlinx.android.synthetic.main.activity_main11.*

class MainActivity11 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main11)

        cek.setOnClickListener {
            val go = Intent(this@MainActivity11, MainActivity::class.java)
            startActivity(go)
        }
        ceks.setOnClickListener {
            val go = Intent(this@MainActivity11, MainActivity2::class.java)
            startActivity(go)
        }



    }
}