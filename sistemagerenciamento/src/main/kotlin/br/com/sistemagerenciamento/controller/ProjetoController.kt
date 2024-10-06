package br.com.sistemagerenciamento.controller


import br.com.sistemagerenciamento.model.Projeto
import org.springframework.http.ResponseEntity
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProjetoController(
    private val projetoHandler: ProjetoHandler
) {
    @GetMapping("/projetos")
    fun findAll(): ResponseEntity<List<Projeto>>{
        return projetoHandler.findAll()
    }
}