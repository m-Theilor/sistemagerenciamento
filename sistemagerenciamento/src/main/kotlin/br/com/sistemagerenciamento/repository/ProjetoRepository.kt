package br.com.sistemagerenciamento.repository

import br.com.sistemagerenciamento.model.Projeto

interface ProjetoRepository {

    fun findAll(): List<Projeto>

}