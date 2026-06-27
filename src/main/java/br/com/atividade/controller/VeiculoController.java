package br.com.atividade.controller;

import br.com.atividade.model.Veiculo;
import br.com.atividade.service.VeiculoService;

import java.sql.SQLException;
import java.util.List;

public class VeiculoController {

    private VeiculoService veiculoService;

    public VeiculoController() {
        this.veiculoService = new VeiculoService();
    }

    public void cadastrar(String placa, String modelo, int ano, int clienteId) throws SQLException {
        Veiculo veiculo = new Veiculo(placa, modelo, ano, clienteId);
        veiculoService.cadastrar(veiculo);
    }

    public void atualizar(int id, String placa, String modelo, int ano, int clienteId) throws SQLException {
        Veiculo veiculo = new Veiculo(id, placa, modelo, ano, clienteId);
        veiculoService.atualizar(veiculo);
    }

    public void excluir(int id) throws SQLException {
        veiculoService.excluir(id);
    }

    public Veiculo buscarPorId(int id) throws SQLException {
        return veiculoService.buscarPorId(id);
    }

    public List<Veiculo> listar() throws SQLException {
        return veiculoService.listar();
    }

    public List<Veiculo> listarPorCliente(int clienteId) throws SQLException {
        return veiculoService.listarPorCliente(clienteId);
    }
}