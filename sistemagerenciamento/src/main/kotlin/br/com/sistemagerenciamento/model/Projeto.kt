package br.com.sistemagerenciamento.model

import java.time.LocalDateTime

data class Projeto(

    val id: Long? = null,
    val titulo: String,
    val descricao: String,
    val criado: LocalDateTime = LocalDateTime.now(),
    val tarefas: List<Tarefa> = ArrayList(),
    val status: ProjetoStatus
)