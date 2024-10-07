package br.com.sistemagerenciamento.model

import java.time.LocalDateTime

data class Tarefa(

    val id: Long? = null,
    val titulo: String,
    val descricao: String,
    val criado: LocalDateTime = LocalDateTime.now(),
    val status: TarefaStatus

)