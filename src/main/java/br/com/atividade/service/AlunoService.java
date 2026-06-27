package br.com.atividade.service;

import br.com.atividade.model.Aluno;
import br.com.atividade.repository.AlunoRepository;

import java.sql.SQLException;
import java.util.List;

public class AlunoService {

    private AlunoRepository alunoRepository;

    public AlunoService() {
        this.alunoRepository = new AlunoRepository();
    }

    public void cadastrar(Aluno aluno) throws SQLException {
        if (aluno.getNome() == null || aluno.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do aluno não pode ser vazio.");
        }

        if (aluno.getEmail() == null || aluno.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email do aluno não pode ser vazio.");
        }

        if (aluno.getTelefone() == null || aluno.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone do aluno não pode ser vazio.");
        }

        aluno.setNome(aluno.getNome().trim());
        aluno.setEmail(aluno.getEmail().trim());
        aluno.setTelefone(aluno.getTelefone().trim());

        alunoRepository.inserir(aluno);
    }

    public void atualizar(Aluno aluno) throws SQLException {
        if (aluno.getNome() == null || aluno.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do aluno não pode ser vazio.");
        }

        if (aluno.getEmail() == null || aluno.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email do aluno não pode ser vazio.");
        }

        if (aluno.getTelefone() == null || aluno.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone do aluno não pode ser vazio.");
        }

        if (alunoRepository.buscarPorId(aluno.getId()) == null) {
            throw new IllegalArgumentException("Aluno não encontrado.");
        }

        aluno.setNome(aluno.getNome().trim());
        aluno.setEmail(aluno.getEmail().trim());
        aluno.setTelefone(aluno.getTelefone().trim());

        alunoRepository.atualizar(aluno);
    }

    public void excluir(int id) throws SQLException {
        if (alunoRepository.buscarPorId(id) == null) {
            throw new IllegalArgumentException("Aluno não encontrado.");
        }

        alunoRepository.excluir(id);
    }

    public Aluno buscarPorId(int id) throws SQLException {
        Aluno aluno = alunoRepository.buscarPorId(id);
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno não encontrado.");
        }
        return aluno;
    }

    public List<Aluno> listar() throws SQLException {
        return alunoRepository.listar();
    }
}