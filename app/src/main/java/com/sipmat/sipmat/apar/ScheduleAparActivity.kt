package com.sipmat.sipmat.apar

import android.app.DatePickerDialog
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sipmat.sipmat.R
import com.sipmat.sipmat.adapter.AparAdapter
import com.sipmat.sipmat.adapter.ScheduleAparAdapter
import com.sipmat.sipmat.model.*
import com.sipmat.sipmat.webservice.ApiClient
import kotlinx.android.synthetic.main.activity_item_apar.*
import kotlinx.android.synthetic.main.activity_schedule_apar.*
import kotlinx.android.synthetic.main.activity_schedule_apar.btntambah
import org.jetbrains.anko.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class ScheduleAparActivity : AppCompatActivity(),AnkoLogger {
    private lateinit var mAdapter: ScheduleAparAdapter
    lateinit var progressDialog: ProgressDialog
    var api = ApiClient.instance()
    var triwulan : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_apar)
        progressDialog = ProgressDialog(this)

        tw()

        btntambah.setOnClickListener {
            startActivity<TambahScheduleActivity>()
        }

        btncari.setOnClickListener {
            val tahun = edttahun.text.toString().trim()
            if (triwulan!=null && tahun.isNotEmpty()){
                getschedule(triwulan!!,tahun)
            }
        }
    }

    private fun tw() {
        val jenis_tw = arrayOf("I","II","III","IV")
        val spinner = find<Spinner>(R.id.spntw)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, jenis_tw
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    triwulan = jenis_tw[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }

        }
    }

    fun getschedule(tw : String,tahun : String){
        rvscheduleapar.layoutManager = LinearLayoutManager(this)
        rvscheduleapar.setHasFixedSize(true)
        (rvscheduleapar.layoutManager as LinearLayoutManager).orientation =
            LinearLayoutManager.VERTICAL
        api.getapar_pick(tw, tahun)
            .enqueue(object : Callback<ScheduleResponse> {
                override fun onResponse(
                    call: Call<ScheduleResponse>,
                    response: Response<ScheduleResponse>
                ) {
                    try {
                        if (response.isSuccessful) {
                            val notesList = mutableListOf<ScheduleModel>()
                            val data = response.body()
                            for (hasil in data!!.data!!) {
                                notesList.add(hasil)
                                mAdapter = ScheduleAparAdapter(notesList, this@ScheduleAparActivity)
                                rvscheduleapar.adapter = mAdapter
/*
                                mAdapter.setDialog(object : ScheduleAparAdapter.Dialog{
                                    override fun onClick(position: Int, note : AparModel) {
                                        val builder = AlertDialog.Builder(this@ScheduleAparActivity)
                                        builder.setTitle("Data APAR")
                                        builder.setMessage("Hapus gaji bulan ini ? ")
                                        builder.setPositiveButton("Edit APAR") { dialog, which ->
                                            startActivity<TambahAparActivity>(
                                                "jenis" to note.jenis,
                                                "id" to note.id,
                                                "kode" to note.kode,
                                                "lokasi" to note.lokasi,
                                                "tanggal" to note.tglPengisian
                                            )
                                        }


                                        builder.setNegativeButton("Hapus APAR ?") { dialog, which ->
                                            api.hapusapar(note.id!!).enqueue(object :
                                                Callback<PostDataResponse> {
                                                override fun onResponse(
                                                    call: Call<PostDataResponse>,
                                                    response: Response<PostDataResponse>
                                                ) {
                                                    try {
                                                        if (response.body()!!.sukses == 1) {
                                                            loading(false)
                                                            toast("hapus apar berhasil")
                                                            onStart()
                                                        } else {
                                                            loading(false)
                                                            toast("hapus apar gagal")
                                                        }


                                                    }catch (e : Exception){
                                                        progressDialog.dismiss()
                                                        info { "dinda ${e.message }${response.code()} " }
                                                    }

                                                }

                                                override fun onFailure(call: Call<PostDataResponse>, t: Throwable) {
                                                    loading(false)
                                                    toast("kesalahan jaringan")

                                                }

                                            })
                                        }

                                        builder.show()

                                    }

                                })
*/
                                mAdapter.notifyDataSetChanged()
                            }
                        } else {
                            toast("gagal mendapatkan response")
                        }
                    } catch (e: Exception) {
                        info { "dinda ${e.message}" }
                    }
                }

                override fun onFailure(call: Call<ScheduleResponse>, t: Throwable) {
                    info { "dinda ${t.message}" }
                }

            })



    }



}