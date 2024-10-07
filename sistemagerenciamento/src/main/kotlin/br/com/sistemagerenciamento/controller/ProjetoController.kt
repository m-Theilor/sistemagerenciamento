package br.com.sistemagerenciamento.controller


import br.com.sistemagerenciamento.model.Projeto
import br.com.sistemagerenciamento.service.ProjetoCreateCommand
import br.com.sistemagerenciamento.service.ProjetoUpdateCommand
import org.springframework.http.ResponseEntity
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.web.bind.annotation.*

@RestController
class ProjetoController(
    private val projetoHandler: ProjetoHandler
) {
    @GetMapping("/projetos")
    fun findAll(): ResponseEntity<List<Projeto>>{
        return projetoHandler.findAll()
    }

    @GetMapping("/projetos/{projetoId}")
    fun findById(@PathVariable projetoId: Int): ResponseEntity<Projeto>{
        return projetoHandler.findById(projetoId)
    }

    @PostMapping("/projetos")
    fun inserir(@RequestBody projeto: ProjetoCreateCommand): ResponseEntity<Projeto>{
        return projetoHandler.inserir(projeto)
    }

    @PutMapping("/projetos/{projetoId}")
    fun atualizar(@RequestBody projeto: ProjetoUpdateCommand, @PathVariable projetoId: Int): ResponseEntity<Projeto>{
        return projetoHandler.atualizar(projeto, projetoId)
    }

    @DeleteMapping("/projetos/{projetoId}")
    fun remover(@PathVariable projetoId: Int): ResponseEntity<String>{
        return projetoHandler.remover(projetoId)
    }
}