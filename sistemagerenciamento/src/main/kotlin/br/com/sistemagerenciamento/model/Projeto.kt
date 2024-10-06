package br.com.sistemagerenciamento.model

import java.time.LocalDateTime

data class Projeto(

    val id: Long? = null,
    val titulo: String,
    val descricao: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    val tarefas: List<Tarefa> = ArrayList(),
    val status: StatusProjeto = StatusProjeto.SEM_TAREFAS

)