package com.example.project_uas.catatan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project_uas.R
import com.example.project_uas.catatan.room.Catat
import com.example.project_uas.catatan.room.CatatDB
import com.example.project_uas.catatan.room.Constant
import kotlinx.android.synthetic.main.activity_main2.button_create
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.*


class MainActivity2: AppCompatActivity() {

    private val db by lazy { CatatDB(this) }
    lateinit var catatAdapter: CatatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setupView()
        setupListener()
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData(){
        CoroutineScope(Dispatchers.IO).launch {
            catatAdapter.setData(db.catatDao().getCatats())
            withContext(Dispatchers.Main) {
                catatAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun setupView (){
        supportActionBar!!.apply {
            title = "Catatan"
        }
    }

    private fun setupListener(){
        button_create.setOnClickListener {
            intentEdit2(Constant.TYPE_CREATE, 0)
        }
    }

    private fun setupRecyclerView () {

        catatAdapter = CatatAdapter(
            arrayListOf(),
            object : CatatAdapter.OnAdapterListener {
                override fun onClick(catat: Catat) {
                    intentEdit2(Constant.TYPE_READ, catat.id)
                }

                override fun onUpdate(catat: Catat) {
                    intentEdit2(Constant.TYPE_UPDATE, catat.id)
                }

                override fun onDelete(catat: Catat) {
                    deleteAlert(catat)
                }

            })

        list_catat.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = catatAdapter
        }

    }

    private fun intentEdit2(intent_type2: Int, catat_id: Int) {
        startActivity(
            Intent(this, EditActivity2::class.java)
                .putExtra("intent_type2", intent_type2)
                .putExtra("catat_id", catat_id)
        )

    }

    private fun deleteAlert(catat: Catat){
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi Hapus")
            setMessage("Yakin hapus ${catat.title}?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                CoroutineScope(Dispatchers.IO).launch {
                    db.catatDao().deleteCatat(catat)
                    dialogInterface.dismiss()
                    loadData()
                }
            }
        }

        dialog.show()
    }
}