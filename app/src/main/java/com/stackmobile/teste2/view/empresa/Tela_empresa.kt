package com.stackmobile.teste2.view.empresa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.stackmobile.teste2.databinding.ActivityTelaEmpresaBinding
import com.stackmobile.teste2.view.formlogin.form_login
import com.stackmobile.teste2.view.Pesquisa.pesquisar

class Tela_empresa : AppCompatActivity() {

        private lateinit var binding: ActivityTelaEmpresaBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityTelaEmpresaBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.btnSair.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                val voltarHome = Intent(this, form_login::class.java)
                startActivity(voltarHome)
                finish()
            }

            binding.btnBuscar.setOnClickListener {
                val empresa = Intent(this, empresa_visualizar_servico::class.java)
                startActivity(empresa)
                finish()
            }

            binding.btnPesquisar.setOnClickListener {
                val buscar = Intent(this, pesquisar::class.java)
                startActivity(buscar)
                finish()
            }
        }
    }
