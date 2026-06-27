package br.com.atividade.service;

import br.com.atividade.model.Curso;
import br.com.atividade.repository.CursoRepository;

import java.sql.SQLException;
import java.util.List;

public class CursoService {

    private CursoRepository cursoRepository;

    public CursoService() {
        this.cursoRepository = new CursoRepository();
    }

    public void cadastrar(Curso curso) throws SQLException {
        if (curso.getNome() == null || curso.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do curso não pode ser vazio.");
        }

        if (curso.getDescricao() == null || curso.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição do curso não pode ser vazia.");
        }

        if (curso.getCargaHoraria() <= 0) {
            throw new IllegalArgumentException("Carga horária deve ser maior que zero.");
        }

        if (curso.getVagasTotais() <= 0) {
            throw new IllegalArgumentException("Vagas totais devem ser maior que zero.");
        }

        if (curso.getVagasDisponiveis() < 0) {
            throw new IllegalArgumentException("Vagas disponíveis não podem ser negativas.");
        }

        if (curso.getVagasDisponiveis() > curso.getVagasTotais()) {
            throw new IllegalArgumentException("Vagas disponíveis não podem ser maiores que as vagas totais.");
        }

        curso.setNome(curso.getNome().trim());
        curso.setDescricao(curso.getDescricao().trim());

        cursoRepository.inserir(curso);
    }

    public void atualizar(Curso curso) throws SQLException {
        if (curso.getNome() == null || curso.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do curso não pode ser vazio.");
        }

        if (curso.getDescricao() == null || curso.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição do curso não pode ser vazia.");
        }

        if (curso.getCargaHoraria() <= 0) {
            throw new IllegalArgumentException("Carga horária deve ser maior que zero.");
        }

        if (curso.getVagasTotais() <= 0) {
            throw new IllegalArgumentException("Vagas totais devem ser maior que zero.");
        }

        if (curso.getVagasDisponiveis() < 0) {
            throw new IllegalArgumentException("Vagas disponíveis não podem ser negativas.");
        }

        if (curso.getVagasDisponiveis() > curso.getVagasTotais()) {
            throw new IllegalArgumentException("Vagas disponíveis não podem ser maiores que as vagas totais.");
        }

        if (cursoRepository.buscarPorId(curso.getId()) == null) {
            throw new IllegalArgumentException("Curso não encontrado.");
        }

        curso.setNome(curso.getNome().trim());
        curso.setDescricao(curso.getDescricao().trim());

        cursoRepository.atualizar(curso);
    }

    public void excluir(int id) throws SQLException {
        if (cursoRepository.buscarPorId(id) == null) {
            throw new IllegalArgumentException("Curso não encontrado.");
        }

        cursoRepository.excluir(id);
    }

    public Curso buscarPorId(int id) throws SQLException {
        Curso curso = cursoRepository.buscarPorId(id);
        if (curso == null) {
            throw new IllegalArgumentException("Curso não encontrado.");
        }
        return curso;
    }

    public List<Curso> listar() throws SQLException {
        return cursoRepository.listar();
    }
}