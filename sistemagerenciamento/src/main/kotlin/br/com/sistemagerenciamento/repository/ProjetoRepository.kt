package br.com.sistemagerenciamento.repository

import br.com.sistemagerenciamento.model.Projeto

interface ProjetoRepository {

    fun findAll(): List<Projeto>

    fun findById(projetoId: Int): Projeto?

    fun inserir(projeto: Projeto): Boolean

}