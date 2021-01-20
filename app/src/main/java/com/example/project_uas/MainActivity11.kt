package com.example.project_uas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

        info.setOnClickListener {
            val go = Intent(this@MainActivity11, MainInfo::class.java)
            startActivity(go)
        }


        close.setOnClickListener {
            val builder = AlertDialog.Builder(this, R.style.AlertTheme)
            builder.setTitle("Exit")
            builder.setIcon(R.drawable.ic_action_warning)

            builder.setMessage("Are you sure want to exit?")
            builder.setPositiveButton("Yes"){ dialog, which ->
                finish()
            }

            builder.setNegativeButton("No"){ dialog, which ->
                Toast.makeText(this, "You Clicked Over no Button", Toast.LENGTH_LONG).show()
            }
            builder.setNeutralButton("Cancel"){ dialog, which ->
                Toast.makeText(this, "You clicked over cancel button", Toast.LENGTH_LONG).show()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

    }
}