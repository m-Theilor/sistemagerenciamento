package br.com.sistemagerenciamento.controller


import br.com.sistemagerenciamento.model.Projeto
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GerenciadorProjetos(
    private val namedParameterJdbcOperations: NamedParameterJdbcOperations
) {
    @GetMapping("/projetos")
    fun projetos(): List<Projeto> {
    val projetos = namedParameterJdbcOperations.query("select id, titulo, descricao from projeto", {rs, rows ->
        Projeto(
            id = rs.getLong("id"),
            titulo = rs.getString("titulo"),
            descricao = rs.getString("descricao")
        )
    })

    return projetos
    }
}