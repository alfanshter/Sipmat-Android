package com.sipmat.sipmat.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sipmat.sipmat.R
import com.sipmat.sipmat.aparpelaksana.AparPelaksanaActivity
import com.sipmat.sipmat.apatpelaksana.CekApatActivity
import com.sipmat.sipmat.databinding.FragmentHomeBinding
import com.sipmat.sipmat.databinding.FragmentProfilBinding
import com.sipmat.sipmat.session.SessionManager
import org.jetbrains.anko.support.v4.startActivity

class HomeFragment : Fragment() {
    //copy 1
    lateinit var binding : FragmentHomeBinding
    lateinit var sessionManager : SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        binding.lifecycleOwner = this
        sessionManager = SessionManager(requireContext().applicationContext)

        binding.txtapar.setOnClickListener {
            startActivity<AparPelaksanaActivity>()
        }

        binding.txtapat.setOnClickListener {
            startActivity<CekApatActivity>()
        }
        return  binding.root
    }


}