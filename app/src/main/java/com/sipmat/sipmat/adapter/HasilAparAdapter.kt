package com.sipmat.sipmat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sipmat.sipmat.R
import com.sipmat.sipmat.model.AparModel
import com.sipmat.sipmat.model.ScheduleModel
import com.sipmat.sipmat.model.UsersModel

class HasilAparAdapter(
    private val notesList: MutableList<ScheduleModel>,
    private val context: Context,

    ) : RecyclerView.Adapter<HasilAparAdapter.ViewHolder>() {

    //database
    private var dialog: Dialog? = null


    interface Dialog {
        fun onClick(position: Int, note : ScheduleModel)
    }

    fun setDialog(dialog: Dialog) {
        this.dialog = dialog
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var kode: TextView
        internal var gedung: TextView
        internal var status: TextView
        internal var jadwal: TextView


        init {
            kode = view.findViewById(R.id.txtkode)
            gedung = view.findViewById(R.id.txtgedung)
            status = view.findViewById(R.id.txtstatus)
            jadwal = view.findViewById(R.id.txtjadwalcek)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_scheduleapar, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val note = notesList[position]
        holder.kode.text = "Kode : ${note.kodeApar}"
        holder.gedung.text = "Gedung : ${note.lokasi}"
        if (note.isStatus ==1){
            holder.status.text = "Status : Silahkan Approve"
        }else if (note.isStatus ==2){
            holder.status.text = "Status : Selesai"
        }
        holder.jadwal.text = "Jadwal : ${note.tanggalCek}"
    }

}
