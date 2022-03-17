package com.sipmat.sipmat.admin.ui.PELAKSANA

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sipmat.sipmat.R
import com.sipmat.sipmat.databinding.FragmentArsipBinding
import com.sipmat.sipmat.databinding.FragmentPelaksanaBinding

class PelaksanaFragment : Fragment() {
    //copy 1
    lateinit var binding : FragmentPelaksanaBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pelaksana,container,false)
        binding.lifecycleOwner


        //copy3
        return  binding.root
    }
}