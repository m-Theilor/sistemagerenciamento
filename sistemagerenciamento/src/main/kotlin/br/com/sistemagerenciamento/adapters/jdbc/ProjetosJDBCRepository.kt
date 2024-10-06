package br.com.sistemagerenciamento.adapters.jdbc

import br.com.sistemagerenciamento.adapters.jdbc.ProjetoSQLExpressions.sqlSelectAll
import br.com.sistemagerenciamento.model.Projeto
import br.com.sistemagerenciamento.model.ProjetoStatus
import br.com.sistemagerenciamento.repository.ProjetoRepository
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository

@Repository
class ProjetosJDBCRepository(
    private val db: NamedParameterJdbcOperations
): ProjetoRepository {

    override fun findAll(): List<Projeto> {
        val projetos = db.query(sqlSelectAll(), { rs, _ ->
            Projeto(
                id = rs.getLong("id"),
                titulo = rs.getString("titulo"),
                descricao = rs.getString("descricao"),
                dataCriacao = rs.getTimestamp("criado").toLocalDateTime(),
                status = ProjetoStatus.valueOf(rs.getString("status"))
            )

        })
        return projetos
    }
}