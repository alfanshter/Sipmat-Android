package com.sipmat.sipmat.apar

import android.app.DatePickerDialog
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.android.material.snackbar.Snackbar
import com.sipmat.sipmat.R
import com.sipmat.sipmat.model.AparPickResponse
import com.sipmat.sipmat.model.AparResponse
import com.sipmat.sipmat.model.PostDataResponse
import com.sipmat.sipmat.webservice.ApiClient
import kotlinx.android.synthetic.main.activity_tambah_schedule.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class TambahScheduleActivity : AppCompatActivity(),AnkoLogger {
    var triwulan : String? = null
    var tanggal_cek : String? = null
    lateinit var progressDialog: ProgressDialog
    var api = ApiClient.instance()
    var lokasi_apar : String? = null
    var nama_apar : String? = null
    var id_apar : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_schedule)
        progressDialog = ProgressDialog(this)

        tw()
        txt_tanggalcek.setOnClickListener {
            tanggalmulai()
        }

        btntambah.setOnClickListener {
            val tahun = edttahun.text.toString().trim()
                if (triwulan!=null && tanggal_cek!=null && nama_apar!=null && tahun.isNotEmpty()){
                    loading(true)
                    api.schedule_apar(nama_apar!!,triwulan!!,tahun,tanggal_cek!!).enqueue(object :Callback<PostDataResponse>{
                        override fun onResponse(
                            call: Call<PostDataResponse>,
                            response: Response<PostDataResponse>
                        ) {
                            try {
                                if (response.body()!!.sukses == 1) {
                                    loading(false)
                                    toast("tambah schedule berhasil")
                                    finish()
                                } else {
                                    loading(false)
                                    Snackbar.make(it, "tambah schedule gagal", Snackbar.LENGTH_SHORT)
                                        .show()
                                }


                            } catch (e: Exception) {
                                progressDialog.dismiss()
                                info { "dinda ${e.message}${response.code()} " }
                            }

                        }

                        override fun onFailure(call: Call<PostDataResponse>, t: Throwable) {
                            loading(false)
                            Snackbar.make(it, "Kesalahan jaringan", Snackbar.LENGTH_SHORT).show()

                        }

                    })
                }else{
                    Snackbar.make(it,"Jangan kosongi kolom",3000).show()

                }

        }



        pilihapar()

    }

    private fun pilihapar() {
        api.getapar_pick().enqueue(object : Callback<AparPickResponse> {
            override fun onFailure(call: Call<AparPickResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<AparPickResponse>,
                response: Response<AparPickResponse>
            ) {
                if (response.isSuccessful) {
                    val apar = AparPickResponse.Apar()
                    apar.lokasi = ""
                    apar.kode = "Pilih APAR"
                    apar.id = -1
                    var spResponse: MutableList<AparPickResponse.Apar> =
                        response.body()!!.data as MutableList<AparPickResponse.Apar>
                    spResponse.add(0, apar)
                    val adapter: ArrayAdapter<AparPickResponse.Apar> =
                        ArrayAdapter<AparPickResponse.Apar>(
                            this@TambahScheduleActivity,
                            android.R.layout.simple_spinner_item,
                            spResponse
                        )
                    spn_apar.adapter = adapter
                    spn_apar.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onNothingSelected(parent: AdapterView<*>?) {
                            }

                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {
                                if (position == 0) {
                                    nama_apar = null
                                }

                                if (position > 0) {
                                    nama_apar = spResponse[position].kode
                                    id_apar = spResponse[position].id
                                    lokasi_apar = "${spResponse[position].kode} - ${spResponse[position].lokasi}"
                                }
                            }

                        }

                }
            }

        })
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

    fun loading(status : Boolean){
        if (status){
            progressDialog.setTitle("Loading...")
            progressDialog.setCanceledOnTouchOutside(false)
            progressDialog.show()
        }else{
            progressDialog.dismiss()
        }
    }


    private fun tanggalmulai() {
        val cal = Calendar.getInstance()
        val calend = Calendar.getInstance()
        val datesetLogger = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            calend.set(Calendar.YEAR, year)
            calend.set(Calendar.MONTH, month)
            calend.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            txt_tanggalcek.text = SimpleDateFormat("yyyy-MM-dd").format(cal.time)
            tanggal_cek = SimpleDateFormat("yyyy-MM-dd").format(cal.time)

        }

        DatePickerDialog(
            this, datesetLogger,
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
        ).show()

    }

}