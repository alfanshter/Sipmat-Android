package com.sipmat.sipmat.aparpelaksana

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sipmat.sipmat.QRcodeActivity
import com.sipmat.sipmat.QRcodeActivity.Companion.hasilqrcode
import com.sipmat.sipmat.R
import com.sipmat.sipmat.databinding.ActivityAparPelaksanaBinding
import com.sipmat.sipmat.databinding.ActivityCekAparBinding
import com.sipmat.sipmat.session.SessionManager
import com.sipmat.sipmat.webservice.ApiClient
import org.jetbrains.anko.startActivity

class CekAparActivity : AppCompatActivity() {
    lateinit var binding : ActivityCekAparBinding
    var api = ApiClient.instance()
    lateinit var sessionManager: SessionManager
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_cek_apar)
        binding.lifecycleOwner = this
        sessionManager = SessionManager(this)
        progressDialog = ProgressDialog(this)

        binding.txtshift.text = "Shift : ${sessionManager.getNama()}"
        binding.btnscan.setOnClickListener {
            startActivity<QRcodeActivity>()
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

}