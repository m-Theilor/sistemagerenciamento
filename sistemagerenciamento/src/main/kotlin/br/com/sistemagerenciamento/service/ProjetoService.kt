package br.com.sistemagerenciamento.service

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

}