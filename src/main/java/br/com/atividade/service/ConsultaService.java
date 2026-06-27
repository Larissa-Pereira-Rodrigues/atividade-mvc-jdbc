package br.com.atividade.service;

import br.com.atividade.model.Consulta;
import br.com.atividade.repository.AnimalRepository;
import br.com.atividade.repository.ConsultaRepository;

import java.sql.SQLException;
import java.util.List;

public class ConsultaService {

    private ConsultaRepository consultaRepository;
    private AnimalRepository animalRepository;

    public ConsultaService() {
        this.consultaRepository = new ConsultaRepository();
        this.animalRepository = new AnimalRepository();
    }

    public void cadastrar(Consulta consulta) throws SQLException {
        if (animalRepository.buscarPorId(consulta.getAnimalId()) == null) {
            throw new IllegalArgumentException("Animal não encontrado. Cadastre o animal antes de registrar a consulta.");
        }

        if (consulta.getData() == null) {
            throw new IllegalArgumentException("Data da consulta não pode ser nula.");
        }

        if (consulta.getMotivo() == null || consulta.getMotivo().trim().isEmpty()) {
            throw new IllegalArgumentException("Motivo da consulta não pode ser vazio.");
        }

        if (consulta.getValor() < 0) {
            throw new IllegalArgumentException("Valor da consulta não pode ser negativo.");
        }

        consulta.setMotivo(consulta.getMotivo().trim());

        consultaRepository.inserir(consulta);
    }

    public void atualizar(Consulta consulta) throws SQLException {
        if (consultaRepository.buscarPorId(consulta.getId()) == null) {
            throw new IllegalArgumentException("Consulta não encontrada.");
        }

        if (animalRepository.buscarPorId(consulta.getAnimalId()) == null) {
            throw new IllegalArgumentException("Animal não encontrado.");
        }

        if (consulta.getData() == null) {
            throw new IllegalArgumentException("Data da consulta não pode ser nula.");
        }

        if (consulta.getMotivo() == null || consulta.getMotivo().trim().isEmpty()) {
            throw new IllegalArgumentException("Motivo da consulta não pode ser vazio.");
        }

        if (consulta.getValor() < 0) {
            throw new IllegalArgumentException("Valor da consulta não pode ser negativo.");
        }

        consulta.setMotivo(consulta.getMotivo().trim());

        consultaRepository.atualizar(consulta);
    }

    public void excluir(int id) throws SQLException {
        if (consultaRepository.buscarPorId(id) == null) {
            throw new IllegalArgumentException("Consulta não encontrada.");
        }

        consultaRepository.excluir(id);
    }

    public Consulta buscarPorId(int id) throws SQLException {
        Consulta consulta = consultaRepository.buscarPorId(id);
        if (consulta == null) {
            throw new IllegalArgumentException("Consulta não encontrada.");
        }
        return consulta;
    }

    public List<Consulta> listar() throws SQLException {
        return consultaRepository.listar();
    }

    public List<Consulta> listarPorAnimal(int animalId) throws SQLException {
        if (animalRepository.buscarPorId(animalId) == null) {
            throw new IllegalArgumentException("Animal não encontrado.");
        }

        return consultaRepository.listarPorAnimal(animalId);
    }
}