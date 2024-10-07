package br.com.sistemagerenciamento.adapters.jdbc

import br.com.sistemagerenciamento.adapters.jdbc.ProjetoSQLExpressions.sqlDeleteById
import br.com.sistemagerenciamento.adapters.jdbc.ProjetoSQLExpressions.sqlInsertProjeto
import br.com.sistemagerenciamento.adapters.jdbc.ProjetoSQLExpressions.sqlSelectAll
import br.com.sistemagerenciamento.adapters.jdbc.ProjetoSQLExpressions.sqlSelectById
import br.com.sistemagerenciamento.adapters.jdbc.ProjetoSQLExpressions.sqlUpdateProjeto
import br.com.sistemagerenciamento.model.Projeto
import br.com.sistemagerenciamento.model.ProjetoStatus
import br.com.sistemagerenciamento.repository.ProjetoRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import java.sql.SQLException

@Repository
class ProjetosJDBCRepository(
    private val db: NamedParameterJdbcOperations
): ProjetoRepository {

    private companion object {
        val LOGGER = KotlinLogging.logger { }
    }

    override fun findAll(): List<Projeto> {
        val projetos = try {
            db.query(sqlSelectAll(), rowMapper())
        } catch (ex: Exception) {
            LOGGER.error {"Houve um erro ao consultar os projetos: ${ex.message}"}
            throw ex
        }
        return projetos
    }

    override fun findById(projetoId: Int): Projeto? {
        val projeto = try {
            val params = MapSqlParameterSource("id", projetoId)
            db.query(sqlSelectById(), params, rowMapper()).firstOrNull()
        } catch (ex: Exception) {
            LOGGER.error {"Houve um erro ao consultar o projeto: ${ex.message}"}
            throw ex
        }
        return projeto
    }

    override fun inserir(projeto: Projeto): Boolean {
        try {
            val params = parametros(projeto)
            val linhasAfetadas = db.update(sqlInsertProjeto(), params)
            return linhasAfetadas > 0

        } catch (ex: Exception) {
            LOGGER.error {"Houve um erro ao inserir o projeto: ${ex.message}"}
            throw ex
        }
    }

    override fun atualizar(projeto: Projeto): Boolean {
        try {
            val params = parametros(projeto)
            val linhasAfetadas = db.update(sqlUpdateProjeto(), params)
            return linhasAfetadas > 0

        } catch (ex: Exception) {
            LOGGER.error {"Houve um erro ao atualizar o projeto: ${ex.message}"}
            throw ex
        }
    }

    override fun remover(projetoId: Int): Boolean {
        try{
            val params = MapSqlParameterSource ("id", projetoId)
            val linhasRemovidas = db.update(sqlDeleteById(), params)
            return linhasRemovidas == 1

        } catch (ex: Exception) {
            LOGGER.error {"Houve um erro ao remover o projeto: ${ex.message}"}
            throw ex
        }
    }

    private fun rowMapper(): RowMapper<Projeto> = RowMapper() {rs, _ ->
        val projetoId = rs.getLong("id")
        Projeto(
            id = projetoId,
            titulo = rs.getString("titulo"),
            descricao = rs.getString("descricao"),
            status = ProjetoStatus.valueOf(rs.getString("status")),
            criado = rs.getTimestamp("criado").toLocalDateTime(),
            tarefas = setOf()
        )
    }

    private fun parametros(projeto: Projeto): MapSqlParameterSource {
        val params = MapSqlParameterSource()
        params.addValue("id", projeto.id)
        params.addValue("titulo", projeto.titulo)
        params.addValue("descricao", projeto.descricao)
        params.addValue("status", projeto.status)
        params.addValue("criado", projeto.criado)
        return params
    }

}