package com.stackmobile.teste2.view.Relatorios

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stackmobile.teste2.R

class OrAdapter_Relatorio(private val ordemList: ArrayList<Ordem_Relatorio>) : RecyclerView.Adapter<OrAdapter_Relatorio.OrAdapter>() {

        inner class OrAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //aqui esta sendo setado os campos pegos da activit
        var servico: TextView
        var valor:TextView
        var status: TextView

        init {
            servico = itemView.findViewById(R.id.servico)
            valor = itemView.findViewById(R.id.valor)
            status = itemView.findViewById(R.id.status)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrAdapter {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_lista_relatorio, parent, false)
        return OrAdapter(itemView)
    }

    override fun getItemCount(): Int {
        return ordemList.size
    }

    override fun onBindViewHolder(holder: OrAdapter, position: Int) {
        holder.servico.text = ordemList[position].Servico
        holder.valor.text = ordemList[position].Valor
        holder.status.text = ordemList[position].Status
    }

}