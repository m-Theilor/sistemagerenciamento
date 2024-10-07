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

    fun sqlSelectById() = """
        SELECT id,
            titulo,
            descricao,
            status,
            criado
        FROM projeto
        WHERE id = :id
    """.trimIndent()

    fun sqlInsertProjeto() = """
       INSERT INTO projeto (
           id,
           titulo,
           descricao,
           status,
           criado) 
           VALUES (
           :id,
           :titulo,
           :descricao,
           :status,
           :criado
       )
    """.trimIndent()

}