package br.com.atividade.service;

import br.com.atividade.model.Curso;
import br.com.atividade.model.Matricula;
import br.com.atividade.repository.AlunoRepository;
import br.com.atividade.repository.CursoRepository;
import br.com.atividade.repository.MatriculaRepository;

import java.sql.SQLException;
import java.util.List;

public class MatriculaService {

    private MatriculaRepository matriculaRepository;
    private AlunoRepository alunoRepository;
    private CursoRepository cursoRepository;

    public MatriculaService() {
        this.matriculaRepository = new MatriculaRepository();
        this.alunoRepository = new AlunoRepository();
        this.cursoRepository = new CursoRepository();
    }

    public void cadastrar(Matricula matricula) throws SQLException {
        if (alunoRepository.buscarPorId(matricula.getAlunoId()) == null) {
            throw new IllegalArgumentException("Aluno não encontrado. Cadastre o aluno antes de realizar a matrícula.");
        }

        Curso curso = cursoRepository.buscarPorId(matricula.getCursoId());
        if (curso == null) {
            throw new IllegalArgumentException("Curso não encontrado. Cadastre o curso antes de realizar a matrícula.");
        }

        if (matricula.getDataMatricula() == null) {
            throw new IllegalArgumentException("Data da matrícula não pode ser nula.");
        }

        if (matricula.getValor() < 0) {
            throw new IllegalArgumentException("Valor da matrícula não pode ser negativo.");
        }

        if (matriculaRepository.existeMatricula(matricula.getAlunoId(), matricula.getCursoId())) {
            throw new IllegalArgumentException("Aluno já está matriculado neste curso.");
        }

        if (curso.getVagasDisponiveis() == 0) {
            throw new IllegalArgumentException("Não há vagas disponíveis neste curso.");
        }

        matriculaRepository.inserir(matricula);

        int novasVagas = curso.getVagasDisponiveis() - 1;
        cursoRepository.atualizarVagasDisponiveis(curso.getId(), novasVagas);
    }

    public void atualizar(Matricula matricula) throws SQLException {
        if (matriculaRepository.buscarPorId(matricula.getId()) == null) {
            throw new IllegalArgumentException("Matrícula não encontrada.");
        }

        if (alunoRepository.buscarPorId(matricula.getAlunoId()) == null) {
            throw new IllegalArgumentException("Aluno não encontrado.");
        }

        if (cursoRepository.buscarPorId(matricula.getCursoId()) == null) {
            throw new IllegalArgumentException("Curso não encontrado.");
        }

        if (matricula.getDataMatricula() == null) {
            throw new IllegalArgumentException("Data da matrícula não pode ser nula.");
        }

        if (matricula.getValor() < 0) {
            throw new IllegalArgumentException("Valor da matrícula não pode ser negativo.");
        }

        matriculaRepository.atualizar(matricula);
    }

    public void excluir(int id) throws SQLException {
        if (matriculaRepository.buscarPorId(id) == null) {
            throw new IllegalArgumentException("Matrícula não encontrada.");
        }

        matriculaRepository.excluir(id);
    }

    public Matricula buscarPorId(int id) throws SQLException {
        Matricula matricula = matriculaRepository.buscarPorId(id);
        if (matricula == null) {
            throw new IllegalArgumentException("Matrícula não encontrada.");
        }
        return matricula;
    }

    public List<Matricula> listar() throws SQLException {
        return matriculaRepository.listar();
    }

    public List<Matricula> listarPorAluno(int alunoId) throws SQLException {
        if (alunoRepository.buscarPorId(alunoId) == null) {
            throw new IllegalArgumentException("Aluno não encontrado.");
        }

        return matriculaRepository.listarPorAluno(alunoId);
    }

    public List<Matricula> listarPorCurso(int cursoId) throws SQLException {
        if (cursoRepository.buscarPorId(cursoId) == null) {
            throw new IllegalArgumentException("Curso não encontrado.");
        }

        return matriculaRepository.listarPorCurso(cursoId);
    }
}