package com.stackmobile.teste2.view.cliente

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore
import com.stackmobile.teste2.R
import com.stackmobile.teste2.databinding.ActivityAtualizarCadastroBinding

class atualizar_cadastro : AppCompatActivity() {

    private lateinit var binding: ActivityAtualizarCadastroBinding
    private val db = FirebaseFirestore.getInstance()
    var id: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtualizarCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        receberId()
        binding.btnVoltar.setOnClickListener {
            val voltar = Intent(this, Tela_cliente::class.java)
            startActivity(voltar)
            finish()
        }

        binding.btnSalvar.setOnClickListener {
            db.collection("Clientes").document(id)
                .update(
                    mapOf(
                        "Nome" to binding.editNome.text.toString(),
                        "Telefone" to binding.editTelefone.text.toString(),
                        "Email" to binding.editEmail.text.toString(),
                        "Senha" to binding.editSenha.text.toString()
                    )
                )
                .addOnSuccessListener {
                    Log.d(ContentValues.TAG, "Atualização salva com sucesso!")
                    binding.editNome.setText("")
                    binding.editEmail.setText("")
                    binding.editTelefone.setText("")
                    binding.editSenha.setText("")
                }
                .addOnFailureListener { e -> Log.w(ContentValues.TAG, "ERRO", e) }
        }
    }

    private fun receberId() {
        var telefone = intent.getStringExtra("Telefone")
        this.id = telefone.toString()
    }
}