package br.com.sistemagerenciamento.service

import br.com.sistemagerenciamento.exceptions.ProjetoNaoEncontradoException
import br.com.sistemagerenciamento.model.Projeto
import br.com.sistemagerenciamento.repository.ProjetoRepository
import org.springframework.stereotype.Service

@Service
class ProjetoService(
    private val projetoRepository: ProjetoRepository
) {

    fun findAll(): List<Projeto>{
        return projetoRepository.findAll()
    }

    fun findById(projetoId: Int): Projeto{
        return projetoRepository.findById(projetoId) ?: throw ProjetoNaoEncontradoException(projetoId)

    }

    fun inserir(projeto: ProjetoCreateCommand): Projeto{
        val projetoDomain = projeto.toProjeto()
        projetoRepository.inserir(projeto = projetoDomain)

        return findById(projetoDomain.id?.toInt() ?: throw IllegalArgumentException("ID inválido"))
    }

}