package br.com.atividade.controller;

import br.com.atividade.model.Consulta;
import br.com.atividade.service.ConsultaService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ConsultaController {

    private ConsultaService consultaService;

    public ConsultaController() {
        this.consultaService = new ConsultaService();
    }

    public void cadastrar(int animalId, LocalDate data, String motivo, double valor) throws SQLException {
        Consulta consulta = new Consulta(animalId, data, motivo, valor);
        consultaService.cadastrar(consulta);
    }

    public void atualizar(int id, int animalId, LocalDate data, String motivo, double valor) throws SQLException {
        Consulta consulta = new Consulta(id, animalId, data, motivo, valor);
        consultaService.atualizar(consulta);
    }

    public void excluir(int id) throws SQLException {
        consultaService.excluir(id);
    }

    public Consulta buscarPorId(int id) throws SQLException {
        return consultaService.buscarPorId(id);
    }

    public List<Consulta> listar() throws SQLException {
        return consultaService.listar();
    }

    public List<Consulta> listarPorAnimal(int animalId) throws SQLException {
        return consultaService.listarPorAnimal(animalId);
    }
}