package br.com.atividade.service;

import br.com.atividade.model.Tutor;
import br.com.atividade.repository.TutorRepository;

import java.sql.SQLException;
import java.util.List;

public class TutorService {

    private TutorRepository tutorRepository;

    public TutorService() {
        this.tutorRepository = new TutorRepository();
    }

    public void cadastrar(Tutor tutor) throws SQLException {
        if (tutor.getNome() == null || tutor.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do tutor não pode ser vazio.");
        }

        if (tutor.getEndereco() == null || tutor.getEndereco().trim().isEmpty()) {
            throw new IllegalArgumentException("Endereço do tutor não pode ser vazio.");
        }

        if (tutor.getTelefone() == null || tutor.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone do tutor não pode ser vazio.");
        }

        tutor.setNome(tutor.getNome().trim());
        tutor.setEndereco(tutor.getEndereco().trim());
        tutor.setTelefone(tutor.getTelefone().trim());

        tutorRepository.inserir(tutor);
    }

    public void atualizar(Tutor tutor) throws SQLException {
        if (tutor.getNome() == null || tutor.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do tutor não pode ser vazio.");
        }

        if (tutor.getEndereco() == null || tutor.getEndereco().trim().isEmpty()) {
            throw new IllegalArgumentException("Endereço do tutor não pode ser vazio.");
        }

        if (tutor.getTelefone() == null || tutor.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone do tutor não pode ser vazio.");
        }

        Tutor existente = tutorRepository.buscarPorId(tutor.getId());
        if (existente == null) {
            throw new IllegalArgumentException("Tutor não encontrado.");
        }

        tutor.setNome(tutor.getNome().trim());
        tutor.setEndereco(tutor.getEndereco().trim());
        tutor.setTelefone(tutor.getTelefone().trim());

        tutorRepository.atualizar(tutor);
    }

    public void excluir(int id) throws SQLException {
        Tutor existente = tutorRepository.buscarPorId(id);
        if (existente == null) {
            throw new IllegalArgumentException("Tutor não encontrado.");
        }

        tutorRepository.excluir(id);
    }

    public Tutor buscarPorId(int id) throws SQLException {
        Tutor tutor = tutorRepository.buscarPorId(id);
        if (tutor == null) {
            throw new IllegalArgumentException("Tutor não encontrado.");
        }
        return tutor;
    }

    public List<Tutor> listar() throws SQLException {
        return tutorRepository.listar();
    }
}