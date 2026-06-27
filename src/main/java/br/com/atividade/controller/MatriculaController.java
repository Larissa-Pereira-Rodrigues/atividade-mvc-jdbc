package br.com.atividade.controller;

import br.com.atividade.model.Matricula;
import br.com.atividade.service.MatriculaService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class MatriculaController {

    private MatriculaService matriculaService;

    public MatriculaController() {
        this.matriculaService = new MatriculaService();
    }

    public void cadastrar(int alunoId, int cursoId, LocalDate dataMatricula, double valor) throws SQLException {
        Matricula matricula = new Matricula(alunoId, cursoId, dataMatricula, valor);
        matriculaService.cadastrar(matricula);
    }

    public void atualizar(int id, int alunoId, int cursoId, LocalDate dataMatricula, double valor) throws SQLException {
        Matricula matricula = new Matricula(id, alunoId, cursoId, dataMatricula, valor);
        matriculaService.atualizar(matricula);
    }

    public void excluir(int id) throws SQLException {
        matriculaService.excluir(id);
    }

    public Matricula buscarPorId(int id) throws SQLException {
        return matriculaService.buscarPorId(id);
    }

    public List<Matricula> listar() throws SQLException {
        return matriculaService.listar();
    }

    public List<Matricula> listarPorAluno(int alunoId) throws SQLException {
        return matriculaService.listarPorAluno(alunoId);
    }

    public List<Matricula> listarPorCurso(int cursoId) throws SQLException {
        return matriculaService.listarPorCurso(cursoId);
    }
}