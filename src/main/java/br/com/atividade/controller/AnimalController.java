package br.com.atividade.controller;

import br.com.atividade.model.Animal;
import br.com.atividade.service.AnimalService;

import java.sql.SQLException;
import java.util.List;

public class AnimalController {

    private final AnimalService animalService;

    public AnimalController() {
        this.animalService = new AnimalService();
    }

    // Cadastrar um novo animal
    public void cadastrar(String nome, String especie, String raca, int tutorId) throws SQLException {
        Animal animal = new Animal(nome, especie, raca, tutorId);
        animalService.cadastrar(animal);
    }

    // Atualizar um animal
    public void atualizar(int id, String nome, String especie, String raca, int tutorId) throws SQLException {
        Animal animal = new Animal(id, nome, especie, raca, tutorId);
        animalService.atualizar(animal);
    }

    // Excluir um animal
    public void excluir(int id) throws SQLException {
        animalService.excluir(id);
    }

    // Buscar animal pelo ID
    public Animal buscarPorId(int id) throws SQLException {
        return animalService.buscarPorId(id);
    }

    // Listar todos os animais
    public List<Animal> listar() throws SQLException {
        return animalService.listar();
    }

    // Listar animais de um tutor
    public List<Animal> listarPorTutor(int tutorId) throws SQLException {
        return animalService.listarPorTutor(tutorId);
    }
}