package br.com.atividade.controller;

import br.com.atividade.model.Aluno;
import br.com.atividade.service.AlunoService;

import java.sql.SQLException;
import java.util.List;

public class AlunoController {

    private AlunoService alunoService;

    public AlunoController() {
        this.alunoService = new AlunoService();
    }

    public void cadastrar(String nome, String email, String telefone) throws SQLException {
        Aluno aluno = new Aluno(nome, email, telefone);
        alunoService.cadastrar(aluno);
    }

    public void atualizar(int id, String nome, String email, String telefone) throws SQLException {
        Aluno aluno = new Aluno(id, nome, email, telefone);
        alunoService.atualizar(aluno);
    }

    public void excluir(int id) throws SQLException {
        alunoService.excluir(id);
    }

    public Aluno buscarPorId(int id) throws SQLException {
        return alunoService.buscarPorId(id);
    }

    public List<Aluno> listar() throws SQLException {
        return alunoService.listar();
    }
}