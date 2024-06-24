package com.stackmobile.teste2.view.Pesquisa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stackmobile.teste2.databinding.ActivityPesquisarBinding
import com.stackmobile.teste2.view.empresa.Tela_empresa

class pesquisar : AppCompatActivity() {
    private lateinit var binding: ActivityPesquisarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesquisarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVoltar.setOnClickListener {
            val voltar = Intent(this, Tela_empresa::class.java)
            startActivity(voltar)
            finish()
        }

    }
}