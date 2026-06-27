package br.com.atividade.controller;

import br.com.atividade.model.Curso;
import br.com.atividade.service.CursoService;

import java.sql.SQLException;
import java.util.List;

public class CursoController {

    private CursoService cursoService;

    public CursoController() {
        this.cursoService = new CursoService();
    }

    public void cadastrar(String nome, String descricao, int cargaHoraria, int vagasTotais, int vagasDisponiveis) throws SQLException {
        Curso curso = new Curso(nome, descricao, cargaHoraria, vagasTotais, vagasDisponiveis);
        cursoService.cadastrar(curso);
    }

    public void atualizar(int id, String nome, String descricao, int cargaHoraria, int vagasTotais, int vagasDisponiveis) throws SQLException {
        Curso curso = new Curso(id, nome, descricao, cargaHoraria, vagasTotais, vagasDisponiveis);
        cursoService.atualizar(curso);
    }

    public void excluir(int id) throws SQLException {
        cursoService.excluir(id);
    }

    public Curso buscarPorId(int id) throws SQLException {
        return cursoService.buscarPorId(id);
    }

    public List<Curso> listar() throws SQLException {
        return cursoService.listar();
    }
}