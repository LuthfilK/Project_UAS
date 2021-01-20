package com.example.project_uas.catatan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.project_uas.R
import com.example.project_uas.catatan.room.Catat
import com.example.project_uas.catatan.room.CatatDB
import com.example.project_uas.catatan.room.Constant
import kotlinx.android.synthetic.main.activity_edit2.button_save
import kotlinx.android.synthetic.main.activity_edit2.button_update
import kotlinx.android.synthetic.main.activity_edit2.edit_title
import kotlinx.android.synthetic.main.activity_edit2.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivity2 : AppCompatActivity() {

    private val db by lazy { CatatDB(this) }
    private var catatId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit2)
        setupView()
        setupLstener()
    }

    private fun setupView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        when (intentType()) {
            Constant.TYPE_CREATE -> {
                supportActionBar!!.title = "BUAT BARU"
                button_save.visibility = View.VISIBLE
                button_update.visibility = View.GONE
            }
            Constant.TYPE_READ -> {
                supportActionBar!!.title = "BACA"
                button_save.visibility = View.GONE
                button_update.visibility = View.GONE
                getCatat()
            }
            Constant.TYPE_UPDATE -> {
                supportActionBar!!.title = "EDIT"
                button_save.visibility = View.GONE
                button_update.visibility = View.VISIBLE
                getCatat()
            }
        }
    }

    private fun setupLstener(){
        button_save.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.catatDao().addCatat(
                    Catat(
                        0,
                        edit_title.text.toString(),
                        edit_catat.text.toString()
                    )
                )
                finish()
            }
        }
        button_update.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.catatDao().updateCatat(
                    Catat(
                        catatId,
                        edit_title.text.toString(),
                        edit_catat.text.toString()
                    )
                )
                finish()
            }
        }
    }

    private fun getCatat(){
        catatId = intent.getIntExtra("catat_id", 0)
        CoroutineScope(Dispatchers.IO).launch {
            val catats = db.catatDao().getCatat(catatId).get(0)
            edit_title.setText( catats.title )
            edit_catat.setText( catats.catat )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun intentType(): Int {
        return intent.getIntExtra("intent_type2", 0)
    }
}