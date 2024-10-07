package br.com.sistemagerenciamento.exceptions

sealed class ProjetoException(message: String): Exception(message) {
    abstract val projetoId: Int?
}

data class ProjetoNaoEncontradoException(
    override val projetoId: Int?,
): ProjetoException("Projeto $projetoId nao encontrado")
