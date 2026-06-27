package br.com.atividade.controller;

import br.com.atividade.model.Tutor;
import br.com.atividade.service.TutorService;

import java.sql.SQLException;
import java.util.List;

public class TutorController {

    private final TutorService tutorService;

    public TutorController() {
        this.tutorService = new TutorService();
    }

    // Cadastrar um novo tutor
    public void cadastrar(String nome, String endereco, String telefone) throws SQLException {
        Tutor tutor = new Tutor(nome, endereco, telefone);
        tutorService.cadastrar(tutor);
    }

    // Atualizar um tutor existente
    public void atualizar(int id, String nome, String endereco, String telefone) throws SQLException {
        Tutor tutor = new Tutor(id, nome, endereco, telefone);
        tutorService.atualizar(tutor);
    }

    // Excluir um tutor
    public void excluir(int id) throws SQLException {
        tutorService.excluir(id);
    }

    // Buscar tutor pelo ID
    public Tutor buscarPorId(int id) throws SQLException {
        return tutorService.buscarPorId(id);
    }

    // Listar todos os tutores
    public List<Tutor> listar() throws SQLException {
        return tutorService.listar();
    }
}