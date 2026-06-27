package br.com.atividade.service;

import br.com.atividade.model.Cliente;
import br.com.atividade.repository.ClienteRepository;

import java.sql.SQLException;
import java.util.List;

public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService() {
        this.clienteRepository = new ClienteRepository();
    }

    public void cadastrar(Cliente cliente) throws SQLException {
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser vazio.");
        }

        if (cliente.getTelefone() == null || cliente.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone do cliente não pode ser vazio.");
        }

        cliente.setNome(cliente.getNome().trim());
        cliente.setTelefone(cliente.getTelefone().trim());

        clienteRepository.inserir(cliente);
    }

    public void atualizar(Cliente cliente) throws SQLException {
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser vazio.");
        }

        if (cliente.getTelefone() == null || cliente.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone do cliente não pode ser vazio.");
        }

        if (clienteRepository.buscarPorId(cliente.getId()) == null) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }

        cliente.setNome(cliente.getNome().trim());
        cliente.setTelefone(cliente.getTelefone().trim());

        clienteRepository.atualizar(cliente);
    }

    public void excluir(int id) throws SQLException {
        if (clienteRepository.buscarPorId(id) == null) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }

        clienteRepository.excluir(id);
    }

    public Cliente buscarPorId(int id) throws SQLException {
        Cliente cliente = clienteRepository.buscarPorId(id);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
        return cliente;
    }

    public List<Cliente> listar() throws SQLException {
        return clienteRepository.listar();
    }
}