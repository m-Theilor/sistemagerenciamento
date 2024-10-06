package br.com.sistemagerenciamento.adapters.jdbc

object ProjetoSQLExpressions {

    fun sqlSelectAll() = """
        SELECT id,
            titulo,
            descricao,
            status,
            criado
        FROM projeto
    """.trimIndent()

}