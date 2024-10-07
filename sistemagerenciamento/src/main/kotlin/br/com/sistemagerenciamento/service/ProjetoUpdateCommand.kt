package br.com.sistemagerenciamento.service

import br.com.sistemagerenciamento.model.Projeto
import br.com.sistemagerenciamento.model.ProjetoStatus
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ProjetoUpdateCommand(
    val titulo: String,
    val descricao: String,
    val criado: LocalDateTime = LocalDateTime.now(),
    // val tarefas: Set<TarefaUpdateCommand>
    val status: ProjetoStatus
)

fun ProjetoUpdateCommand.toProjeto(id: Int?) = Projeto(
    titulo = titulo,
    descricao = descricao,
    criado = criado,
    status = status,
    tarefas = setOf()
)
