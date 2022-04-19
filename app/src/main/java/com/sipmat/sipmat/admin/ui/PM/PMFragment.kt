package com.sipmat.sipmat.admin.ui.PM

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sipmat.sipmat.admin.apar.AparActivity
import com.sipmat.sipmat.R
import com.sipmat.sipmat.admin.apat.ApatActivity
import com.sipmat.sipmat.databinding.FragmentPmBinding
import org.jetbrains.anko.support.v4.startActivity

class PMFragment : Fragment() {
    //copy 1
    lateinit var binding : FragmentPmBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pm,container,false)
        binding.lifecycleOwner

        binding.txtapar.setOnClickListener {
            startActivity<AparActivity>()
        }
        binding.txtapat.setOnClickListener {
            startActivity<ApatActivity>()
        }
        //copy3
        return  binding.root
    }
}