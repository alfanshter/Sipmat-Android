package com.sipmat.sipmat.aparpelaksana

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.sipmat.sipmat.R
import com.sipmat.sipmat.adapter.ScheduleAparAdapter
import com.sipmat.sipmat.adapter.pelaksana.AparPelaksanaAdapter
import com.sipmat.sipmat.databinding.ActivityAparPelaksanaBinding
import com.sipmat.sipmat.model.ScheduleAparPelaksanaModel
import com.sipmat.sipmat.model.ScheduleAparPelaksanaResponse
import com.sipmat.sipmat.model.ScheduleModel
import com.sipmat.sipmat.model.ScheduleResponse
import com.sipmat.sipmat.webservice.ApiClient
import kotlinx.android.synthetic.main.activity_schedule_apar.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AparPelaksanaActivity : AppCompatActivity(),AnkoLogger {
    lateinit var binding : ActivityAparPelaksanaBinding
    var api = ApiClient.instance()
    private lateinit var mAdapter: AparPelaksanaAdapter
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_apar_pelaksana)
        binding.lifecycleOwner = this


    }

    override fun onStart() {
        super.onStart()
        getaparpelaksana()
    }


    fun getaparpelaksana(){
        binding.rvaparpelaksana.layoutManager = LinearLayoutManager(this)
        binding.rvaparpelaksana.setHasFixedSize(true)
        (binding.rvaparpelaksana.layoutManager as LinearLayoutManager).orientation =
            LinearLayoutManager.VERTICAL
        api.getschedule_pelaksana()
            .enqueue(object : Callback<ScheduleAparPelaksanaResponse> {
                override fun onResponse(
                    call: Call<ScheduleAparPelaksanaResponse>,
                    response: Response<ScheduleAparPelaksanaResponse>
                ) {
                    try {
                        if (response.isSuccessful) {
                            val notesList = mutableListOf<ScheduleAparPelaksanaModel>()
                            val data = response.body()
                            for (hasil in data!!.data!!) {
                                notesList.add(hasil)
                                mAdapter = AparPelaksanaAdapter(notesList, this@AparPelaksanaActivity)
                                binding.rvaparpelaksana.adapter = mAdapter
                                mAdapter.setDialog(object : AparPelaksanaAdapter.Dialog{
                                    override fun onClick(position: Int, note : ScheduleAparPelaksanaModel) {
                                        val builder = AlertDialog.Builder(this@AparPelaksanaActivity)
                                        builder.setMessage("Cek apar ? ")
                                        builder.setPositiveButton("Cek APAR") { dialog, which ->
                                            startActivity<CekAparActivity>()
                                        }


                                        builder.setNegativeButton("Cancel ?") { dialog, which ->

                                        }

                                        builder.show()

                                    }

                                })
                                mAdapter.notifyDataSetChanged()
                            }
                        } else {
                            toast("gagal mendapatkan response")
                        }
                    } catch (e: Exception) {
                        info { "dinda ${e.message}" }
                    }
                }

                override fun onFailure(call: Call<ScheduleAparPelaksanaResponse>, t: Throwable) {
                    info { "dinda ${t.message}" }
                }

            })



    }

}