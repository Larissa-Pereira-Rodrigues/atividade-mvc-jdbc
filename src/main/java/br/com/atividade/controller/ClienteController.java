package br.com.atividade.controller;

import br.com.atividade.model.Cliente;
import br.com.atividade.service.ClienteService;

import java.sql.SQLException;
import java.util.List;

public class ClienteController {

    private ClienteService clienteService;

    public ClienteController() {
        this.clienteService = new ClienteService();
    }

    public void cadastrar(String nome, String telefone) throws SQLException {
        Cliente cliente = new Cliente(nome, telefone);
        clienteService.cadastrar(cliente);
    }

    public void atualizar(int id, String nome, String telefone) throws SQLException {
        Cliente cliente = new Cliente(id, nome, telefone);
        clienteService.atualizar(cliente);
    }

    public void excluir(int id) throws SQLException {
        clienteService.excluir(id);
    }

    public Cliente buscarPorId(int id) throws SQLException {
        return clienteService.buscarPorId(id);
    }

    public List<Cliente> listar() throws SQLException {
        return clienteService.listar();
    }
}