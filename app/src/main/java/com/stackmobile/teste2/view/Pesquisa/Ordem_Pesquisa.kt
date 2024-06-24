package com.stackmobile.teste2.view.Pesquisa

import android.media.Rating

data class Ordem_Pesquisa(
    val Email:String?=null,
    val Nome:String?=null,
    val Servico:String?=null,
    val Telefone:String?=null,
    val Status:String?=null,
    val Valor:String?=null,
    val Avaliacao:Float = 0.0F,
    val Comentario:String?=null
)
