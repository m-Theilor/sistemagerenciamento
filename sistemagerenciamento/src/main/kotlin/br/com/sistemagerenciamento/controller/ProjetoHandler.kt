package br.com.sistemagerenciamento.controller

import br.com.sistemagerenciamento.model.Projeto
import br.com.sistemagerenciamento.service.ProjetoCreateCommand
import br.com.sistemagerenciamento.service.ProjetoService
import br.com.sistemagerenciamento.service.ProjetoUpdateCommand
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class ProjetoHandler(
    private val projetoService: ProjetoService
) {

    fun findAll(): ResponseEntity<List<Projeto>>{
        val projetos = projetoService.findAll()
        return ResponseEntity.ok(projetos)
    }

    fun findById(projetoId: Int): ResponseEntity<Projeto>{
        val projeto = projetoService.findById(projetoId)
        return ResponseEntity.ok(projeto)
    }

    fun inserir(projetoCreateCommand: ProjetoCreateCommand): ResponseEntity<Projeto> {
        var projeto = projetoService.inserir(projetoCreateCommand)
        return ResponseEntity.status(HttpStatus.CREATED).body(projeto)
    }

    fun atualizar(projetoUpdateCommand: ProjetoUpdateCommand, projetoId: Int): ResponseEntity<Projeto> {
        val projeto = projetoService.atualizar(projetoUpdateCommand, projetoId)
        return ResponseEntity.ok(projeto)
    }

    fun remover(projetoId: Int): ResponseEntity<String> {
        projetoService.remover(projetoId = projetoId)
        return ResponseEntity.noContent().build()
    }

}