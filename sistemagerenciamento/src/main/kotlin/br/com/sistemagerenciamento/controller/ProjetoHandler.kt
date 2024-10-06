package br.com.sistemagerenciamento.controller

import br.com.sistemagerenciamento.model.Projeto
import br.com.sistemagerenciamento.service.ProjetoService
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

}