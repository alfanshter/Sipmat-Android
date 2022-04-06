package com.sipmat.sipmat.apar

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.gcacace.signaturepad.views.SignaturePad
import com.sipmat.sipmat.R
import com.sipmat.sipmat.adapter.HasilAparAdapter
import com.sipmat.sipmat.adapter.ScheduleAparAdapter
import com.sipmat.sipmat.databinding.FragmentSinkronHasilBinding
import com.sipmat.sipmat.model.ScheduleModel
import com.sipmat.sipmat.model.ScheduleResponse
import com.sipmat.sipmat.webservice.ApiClient
import kotlinx.android.synthetic.main.activity_hasil_apar.*
import kotlinx.android.synthetic.main.activity_hasil_apar.btncari
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.find
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HasilAparActivity : AppCompatActivity(),AnkoLogger {
    private lateinit var mAdapter: HasilAparAdapter
    lateinit var progressDialog: ProgressDialog
    var api = ApiClient.instance()
    var triwulan : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hasil_apar)

        tw()

        btncari.setOnClickListener {
            val tahun = edttahun.text.toString().trim()
            if (triwulan!=null && tahun.isNotEmpty()){
                gethasil(triwulan!!,tahun)
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

    fun gethasil(tw : String,tahun : String){
        rvhasilapar.layoutManager = LinearLayoutManager(this)
        rvhasilapar.setHasFixedSize(true)
        (rvhasilapar.layoutManager as LinearLayoutManager).orientation =
            LinearLayoutManager.VERTICAL
        api.getapar_hasil(tw, tahun)
            .enqueue(object : Callback<ScheduleResponse> {
                override fun onResponse(
                    call: Call<ScheduleResponse>,
                    response: Response<ScheduleResponse>
                ) {
                    try {
                        if (response.isSuccessful) {
                            val notesList = mutableListOf<ScheduleModel>()
                            val data = response.body()
                            if (data!!.data!!.isEmpty()){
                                toast("data kosong")
                            }else{
                                btnsinkron.visibility = View.VISIBLE
                                btnsinkron.setOnClickListener {
                                    showcustomdialog()
                                }
                                for (hasil in data.data!!) {
                                    notesList.add(hasil)
                                    mAdapter = HasilAparAdapter(notesList, this@HasilAparActivity)
                                    rvhasilapar.adapter = mAdapter
/*
                                mAdapter.setDialog(object : HasilAparAdapter.Dialog{
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

    fun showcustomdialog(){
        val dialogBinding: FragmentSinkronHasilBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(this),
                R.layout.fragment_sinkron_hasil,
                null,
                false
            )

        val customDialog =
            AlertDialog.Builder(this, 0).create()

        customDialog.apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setView(dialogBinding?.root)
            setCancelable(false)
        }.show()

        dialogBinding.signaturePad.setOnSignedListener(object : SignaturePad.OnSignedListener{
            override fun onStartSigning() {
                TODO("Not yet implemented")
            }

            override fun onSigned() {
                TODO("Not yet implemented")
            }

            override fun onClear() {
                TODO("Not yet implemented")
            }

        })


    }



}