package com.stackmobile.teste2.view.Pesquisa

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.stackmobile.teste2.R
import com.stackmobile.teste2.view.avaliacoes.avaliacoes
import com.stackmobile.teste2.view.cliente.Tela_cliente

class OrAdapter_Pesquisa(private val ordemList: ArrayList<Ordem_Pesquisa>) : RecyclerView.Adapter<OrAdapter_Pesquisa.OrAdapter>() {

    private val db = FirebaseFirestore.getInstance()

    inner class OrAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //aqui esta sendo setado os campos pegos da activit
        var email: TextView
        var nome: TextView
        var servico: TextView
        var telefone: TextView
        var status: TextView
        var valor:TextView
        var comentario:TextView
        //var avaliacao:Float = 0.0f

        init {
            email = itemView.findViewById(R.id.email)
            nome = itemView.findViewById(R.id.nome)
            servico = itemView.findViewById(R.id.servico)
            telefone = itemView.findViewById(R.id.telefone)
            status = itemView.findViewById(R.id.status)
            valor = itemView.findViewById(R.id.valor)
            comentario = itemView.findViewById(R.id.edit_comentario)
            //avaliacao = itemView.findViewById(R.id.avaliacao)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrAdapter {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_pesquisar, parent, false)
        return OrAdapter(itemView)
    }

    override fun getItemCount(): Int {
        return ordemList.size
    }

    override fun onBindViewHolder(holder: OrAdapter, position: Int) {
        holder.email.text = ordemList[position].Email
        holder.nome.text = ordemList[position].Nome
        holder.servico.text = ordemList[position].Servico
        holder.telefone.text = ordemList[position].Telefone
        holder.status.text = ordemList[position].Status
        holder.valor.text = ordemList[position].Valor
        holder.comentario.text = ordemList[position].Comentario
        //holder.avaliacao.dec() = ordemList[position].Avaliacao
    }

}
