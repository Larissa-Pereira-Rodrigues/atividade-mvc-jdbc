package br.com.atividade.service;

import br.com.atividade.model.Animal;
import br.com.atividade.repository.AnimalRepository;
import br.com.atividade.repository.TutorRepository;

import java.sql.SQLException;
import java.util.List;

public class AnimalService {

    private AnimalRepository animalRepository;
    private TutorRepository tutorRepository;

    public AnimalService() {
        this.animalRepository = new AnimalRepository();
        this.tutorRepository = new TutorRepository();
    }

    public void cadastrar(Animal animal) throws SQLException {
        if (animal.getNome() == null || animal.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do animal não pode ser vazio.");
        }

        if (animal.getEspecie() == null || animal.getEspecie().trim().isEmpty()) {
            throw new IllegalArgumentException("Espécie do animal não pode ser vazia.");
        }

        if (animal.getRaca() == null || animal.getRaca().trim().isEmpty()) {
            throw new IllegalArgumentException("Raça do animal não pode ser vazia.");
        }

        if (tutorRepository.buscarPorId(animal.getTutorId()) == null) {
            throw new IllegalArgumentException("Tutor não encontrado. Cadastre o tutor antes de cadastrar o animal.");
        }

        animal.setNome(animal.getNome().trim());
        animal.setEspecie(animal.getEspecie().trim());
        animal.setRaca(animal.getRaca().trim());

        animalRepository.inserir(animal);
    }

    public void atualizar(Animal animal) throws SQLException {
        if (animal.getNome() == null || animal.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do animal não pode ser vazio.");
        }

        if (animal.getEspecie() == null || animal.getEspecie().trim().isEmpty()) {
            throw new IllegalArgumentException("Espécie do animal não pode ser vazia.");
        }

        if (animal.getRaca() == null || animal.getRaca().trim().isEmpty()) {
            throw new IllegalArgumentException("Raça do animal não pode ser vazia.");
        }

        if (animalRepository.buscarPorId(animal.getId()) == null) {
            throw new IllegalArgumentException("Animal não encontrado.");
        }

        if (tutorRepository.buscarPorId(animal.getTutorId()) == null) {
            throw new IllegalArgumentException("Tutor não encontrado.");
        }

        animal.setNome(animal.getNome().trim());
        animal.setEspecie(animal.getEspecie().trim());
        animal.setRaca(animal.getRaca().trim());

        animalRepository.atualizar(animal);
    }

    public void excluir(int id) throws SQLException {
        if (animalRepository.buscarPorId(id) == null) {
            throw new IllegalArgumentException("Animal não encontrado.");
        }

        animalRepository.excluir(id);
    }

    public Animal buscarPorId(int id) throws SQLException {
        Animal animal = animalRepository.buscarPorId(id);
        if (animal == null) {
            throw new IllegalArgumentException("Animal não encontrado.");
        }
        return animal;
    }

    public List<Animal> listar() throws SQLException {
        return animalRepository.listar();
    }

    public List<Animal> listarPorTutor(int tutorId) throws SQLException {
        if (tutorRepository.buscarPorId(tutorId) == null) {
            throw new IllegalArgumentException("Tutor não encontrado.");
        }

        return animalRepository.listarPorTutor(tutorId);
    }
}