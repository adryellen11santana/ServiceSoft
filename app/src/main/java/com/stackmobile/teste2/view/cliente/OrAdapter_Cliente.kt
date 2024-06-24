package com.stackmobile.teste2.view.cliente

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.stackmobile.teste2.R
import com.stackmobile.teste2.view.cliente.Ordem_Cliente

class OrAdapter_Cliente(private val ordemList: ArrayList<Ordem_Cliente>) : RecyclerView.Adapter<OrAdapter_Cliente.OrAdapter>() {

    private val db = FirebaseFirestore.getInstance()


    inner class OrAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //aqui esta sendo setado os campos pegos da activit
        var servico: TextView
        var valor:TextView
        var status: TextView
        var btn_finalizar: Button
        var Telefone:String

        init {
            servico = itemView.findViewById(R.id.servico)
            valor = itemView.findViewById(R.id.valor)
            status = itemView.findViewById(R.id.status)
            Telefone = ""
            btn_finalizar = itemView.findViewById(R.id.btn_finalizar)
            btn_finalizar.setOnClickListener {
                //só vai atualizar, se o ID do document foi achado! verificar isso.
                //no OrAdapter_Empresa, foi usado como ID o Telefone. Porém aqui, o telefone não foi criado.
                val statusRef = db.collection("Clientes").document(Telefone.toString())

                statusRef
                    .update("Status", "Finalizado")
                    .addOnSuccessListener { Log.d(ContentValues.TAG, "Atualizado com sucesso!")
                        status.setText("Finalizado")
                    }
                    .addOnFailureListener { e -> Log.w(ContentValues.TAG, "ERRO ao atualizar", e) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrAdapter {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_lista_cliente, parent, false)
        return OrAdapter(itemView)
    }

    override fun getItemCount(): Int {
        return ordemList.size
    }

    override fun onBindViewHolder(holder: OrAdapter, position: Int) {
        holder.servico.text = ordemList[position].Servico
        holder.valor.text = ordemList[position].Valor
        holder.status.text = ordemList[position].Status
        holder.Telefone = ordemList[position].Telefone.toString()
    }

}