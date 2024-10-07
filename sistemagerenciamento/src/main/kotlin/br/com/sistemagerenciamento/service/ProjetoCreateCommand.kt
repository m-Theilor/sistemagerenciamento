package br.com.sistemagerenciamento.service
import br.com.sistemagerenciamento.model.Projeto
import br.com.sistemagerenciamento.model.ProjetoStatus
import br.com.sistemagerenciamento.model.Tarefa
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ProjetoCreateCommand(
    val titulo: String,
    val descricao: String,
    val criado: LocalDateTime = LocalDateTime.now(),
   // val tarefas: List<Tarefa> = ArrayList(),
    val status: ProjetoStatus
)

fun ProjetoCreateCommand.toProjeto() = Projeto(
    titulo = titulo,
    descricao = descricao,
    criado = criado,
    status = status
)