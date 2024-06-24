package com.stackmobile.teste2.view.Pesquisa

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.stackmobile.teste2.R
import com.stackmobile.teste2.databinding.ActivityPesquisarBinding
import com.stackmobile.teste2.view.Pesquisa.OrAdapter_Pesquisa
import com.stackmobile.teste2.view.empresa.Tela_empresa

class pesquisar : AppCompatActivity() {

    private  lateinit var recyclerView: RecyclerView
    private lateinit var ordemList: ArrayList<Ordem_Pesquisa>
    lateinit var binding: ActivityPesquisarBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPesquisarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVoltar.setOnClickListener {
            val voltar = Intent(this, Tela_empresa::class.java)
            startActivity(voltar)
            finish()
        }

        binding.btnPesquisar.setOnClickListener {
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
                            val ordem: Ordem_Pesquisa? = data.toObject(Ordem_Pesquisa::class.java)
                            // se não for null, adiciona na lista
                            if(ordem !=null){
                                ordemList.add(ordem)
                            }
                        }
                        recyclerView.adapter = OrAdapter_Pesquisa(ordemList)
                    }
                }

                .addOnFailureListener{
                    Toast.makeText(this,it.toString(), Toast.LENGTH_SHORT).show()
                }
        }

        }


    }
}