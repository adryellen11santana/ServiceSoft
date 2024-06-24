package com.stackmobile.teste2.view.cliente

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.stackmobile.teste2.R
import com.stackmobile.teste2.databinding.ActivityAtualizarCadastroBinding

class atualizar_cadastro : AppCompatActivity() {

    private lateinit var binding: ActivityAtualizarCadastroBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAtualizarCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVoltar.setOnClickListener {
            val voltar = Intent(this, Tela_cliente::class.java)
            startActivity(voltar)
            finish()
        }

        binding.btnSalvar.setOnClickListener {view ->
            val telefone= binding.editTelefone.text.toString()
            val updatedData = mapOf(
                "Nome" to binding.editNome.text.toString(),
                "Telefone" to binding.editTelefone.text.toString(),
                "Email" to binding.editEmail.text.toString(),
                "Senha" to binding.editSenha.text.toString()
            )
            atualizar(telefone, updatedData)
        }

    }
    private fun atualizar(telefone: String, updatedData: Map<String, String>) {
        db.collection("Clientes").document(binding.editTelefone.text.toString())
            .update(updatedData)
            .addOnSuccessListener {
                // Sucesso ao atualizar
                showToast("Atualização realizada com sucesso!")
                binding.editNome.setText("")
                binding.editTelefone.setText("")
                binding.editEmail.setText("")
                binding.editSenha.setText("")
            }
            .addOnFailureListener { e ->
                // Falha ao atualizar
                showToast("ERRO: ${e.message}")
            }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

