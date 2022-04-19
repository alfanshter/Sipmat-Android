package com.sipmat.sipmat.aparpelaksana

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.sipmat.sipmat.R
import com.sipmat.sipmat.adapter.ScheduleAparAdapter
import com.sipmat.sipmat.adapter.apar.PagerAparPelaksana
import com.sipmat.sipmat.adapter.pelaksana.AparPelaksanaAdapter
import com.sipmat.sipmat.aparpelaksana.uiapar.CekAparFragment
import com.sipmat.sipmat.aparpelaksana.uiapar.KadaluarsaAparFragment
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

        val fragmentAdapter = PagerAparPelaksana(supportFragmentManager)

        binding.viewPesanan.adapter =fragmentAdapter
        binding.tabPesanan.setupWithViewPager(binding.viewPesanan)

        setUpViewPager(binding.viewPesanan)
        binding.tabPesanan.setupWithViewPager(binding.viewPesanan)
        binding.tabPesanan.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {}
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })


    }

    private fun setUpViewPager(viewPager: ViewPager) {
        val adapter = PagerAparPelaksana(supportFragmentManager)
        adapter.addFragment(CekAparFragment(), "Apar")
        adapter.addFragment(KadaluarsaAparFragment(), "Apar Kadaluarsa")
        viewPager.adapter = adapter
    }




    override fun onStart() {
        super.onStart()
    }

}