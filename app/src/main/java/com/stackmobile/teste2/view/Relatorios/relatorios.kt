package com.stackmobile.teste2.view.Relatorios

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.stackmobile.teste2.databinding.ActivityRelatoriosBinding
import com.stackmobile.teste2.view.empresa.Tela_empresa

class relatorios : AppCompatActivity() {

    private  lateinit var recyclerView: RecyclerView
    private lateinit var ordemList: ArrayList<Ordem_Relatorio>
    lateinit var binding: ActivityRelatoriosBinding
    private val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRelatoriosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVoltar.setOnClickListener {
            val voltarHome = Intent(this, Tela_empresa::class.java)
            startActivity(voltarHome)
            finish()
        }

        binding.btnFinalizados.setOnClickListener{
            recyclerView = binding.recyclerview
            recyclerView.layoutManager = LinearLayoutManager(this)

            ordemList = arrayListOf()

            db.collection("Clientes")
                .whereEqualTo("Status", "Finalizado")
                .get()
                .addOnSuccessListener {
                    //verifica se achou algo no banco
                    if(!it.isEmpty){
                        //se achou, pega cada item e joga no data
                        for(data in it.documents){
                            val ordem:Ordem_Relatorio? = data.toObject(Ordem_Relatorio::class.java)
                            // se não for null, adiciona na lista
                            if(ordem !=null){
                                ordemList.add(ordem)
                            }
                        }
                        recyclerView.adapter = OrAdapter_Relatorio(ordemList)
                    }
                }

                .addOnFailureListener{
                    Toast.makeText(this,it.toString(), Toast.LENGTH_SHORT).show()
                }
        }

        binding.btnCancelados.setOnClickListener{
            recyclerView = binding.recyclerview
            recyclerView.layoutManager = LinearLayoutManager(this)

            ordemList = arrayListOf()

            db.collection("Clientes")
                .whereEqualTo("Status", "Recusado")
                .get()
                .addOnSuccessListener {
                    //verifica se achou algo no banco
                    if(!it.isEmpty){
                        //se achou, pega cada item e joga no data
                        for(data in it.documents){
                            val ordem:Ordem_Relatorio? = data.toObject(Ordem_Relatorio::class.java)
                            // se não for null, adiciona na lista
                            if(ordem !=null){
                                ordemList.add(ordem)
                            }
                        }
                        recyclerView.adapter = OrAdapter_Relatorio(ordemList)
                    }
                }

                .addOnFailureListener{
                    Toast.makeText(this,it.toString(), Toast.LENGTH_SHORT).show()
                }
        }
    }
}