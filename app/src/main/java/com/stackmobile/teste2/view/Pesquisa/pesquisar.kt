package com.stackmobile.teste2.view.Pesquisa

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.play.integrity.internal.i
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
    private var mostrar:String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPesquisarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVoltar.setOnClickListener {
            val voltar = Intent(this, Tela_empresa::class.java)
            startActivity(voltar)
            finish()
        }

        val itens = listOf("Cadastros", "Feedback", "Serviços")

        val autoComplete: AutoCompleteTextView = findViewById(R.id.auto_complete)

        val adapter = ArrayAdapter(this, R.layout.lista_item, itens)

        autoComplete.setAdapter(adapter)

        autoComplete.onItemClickListener = AdapterView.OnItemClickListener {
                            adapterView, view, i, l ->

        val itemSelected = adapterView.getItemAtPosition(i)
        Toast.makeText(this, "Item: $itemSelected", Toast.LENGTH_SHORT).show()
            mostrar = itemSelected.toString()
            ordemList.clear()
            pesquisa()
        }
    }
    private fun pesquisa(){

        db.collection("Clientes")
            .get()
            .addOnSuccessListener{
                if (!it.isEmpty){
                    for (data in it.documents){
                        val ordem:Ordem_Pesquisa? = data.toObject(Ordem_Pesquisa::class.java)
                        if (ordem != null && (ordem.Nome.toString() != null && ordem.Telefone.toString() != null && ordem.Email.toString() != null))
                            if (mostrar != ""){
                                ordemList.add(ordem)
                            }
                                //if (mostrar == ordem.Nome && ordem.Telefone && ordem.Email)
                    }
                    recyclerView.adapter = OrAdapter_Pesquisa(ordemList)
                }
            }
            .addOnFailureListener{
                Toast.makeText(this,it.toString(), Toast.LENGTH_SHORT).show()
            }
    }

}
