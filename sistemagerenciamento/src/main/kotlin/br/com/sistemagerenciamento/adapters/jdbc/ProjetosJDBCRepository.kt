package br.com.sistemagerenciamento.adapters.jdbc

import br.com.sistemagerenciamento.adapters.jdbc.ProjetoSQLExpressions.sqlSelectAll
import br.com.sistemagerenciamento.adapters.jdbc.ProjetoSQLExpressions.sqlSelectById
import br.com.sistemagerenciamento.model.Projeto
import br.com.sistemagerenciamento.model.ProjetoStatus
import br.com.sistemagerenciamento.repository.ProjetoRepository
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository

@Repository
class ProjetosJDBCRepository(
    private val db: NamedParameterJdbcOperations
): ProjetoRepository {

    override fun findAll(): List<Projeto> {
        val projetos = db.query(sqlSelectAll(), rowMapper())
        return projetos
    }

    override fun findById(projetoId: Int): Projeto? {
        val params = MapSqlParameterSource("id", projetoId)
        val projeto = db.query(sqlSelectById(), params, rowMapper()).firstOrNull()
        return projeto
    }

    private fun rowMapper(): RowMapper<Projeto> = RowMapper() {rs, _ ->
        Projeto(
            id = rs.getLong("id"),
            titulo = rs.getString("titulo"),
            descricao = rs.getString("descricao"),
            dataCriacao = rs.getTimestamp("criado").toLocalDateTime(),
            status = ProjetoStatus.valueOf(rs.getString("status"))
        )
    }

}