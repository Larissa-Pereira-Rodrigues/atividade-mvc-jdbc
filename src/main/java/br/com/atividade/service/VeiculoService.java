package br.com.atividade.service;

import br.com.atividade.model.Veiculo;
import br.com.atividade.repository.ClienteRepository;
import br.com.atividade.repository.VeiculoRepository;

import java.sql.SQLException;
import java.util.List;

public class VeiculoService {

    private VeiculoRepository veiculoRepository;
    private ClienteRepository clienteRepository;

    public VeiculoService() {
        this.veiculoRepository = new VeiculoRepository();
        this.clienteRepository = new ClienteRepository();
    }

    public void cadastrar(Veiculo veiculo) throws SQLException {
        if (veiculo.getPlaca() == null || veiculo.getPlaca().trim().isEmpty()) {
            throw new IllegalArgumentException("Placa do veículo não pode ser vazia.");
        }

        if (veiculo.getModelo() == null || veiculo.getModelo().trim().isEmpty()) {
            throw new IllegalArgumentException("Modelo do veículo não pode ser vazio.");
        }

        if (veiculo.getAno() <= 0) {
            throw new IllegalArgumentException("Ano do veículo deve ser maior que zero.");
        }

        if (clienteRepository.buscarPorId(veiculo.getClienteId()) == null) {
            throw new IllegalArgumentException("Cliente não encontrado. Cadastre o cliente antes de cadastrar o veículo.");
        }

        veiculo.setPlaca(veiculo.getPlaca().trim());
        veiculo.setModelo(veiculo.getModelo().trim());

        veiculoRepository.inserir(veiculo);
    }

    public void atualizar(Veiculo veiculo) throws SQLException {
        if (veiculo.getPlaca() == null || veiculo.getPlaca().trim().isEmpty()) {
            throw new IllegalArgumentException("Placa do veículo não pode ser vazia.");
        }

        if (veiculo.getModelo() == null || veiculo.getModelo().trim().isEmpty()) {
            throw new IllegalArgumentException("Modelo do veículo não pode ser vazio.");
        }

        if (veiculo.getAno() <= 0) {
            throw new IllegalArgumentException("Ano do veículo deve ser maior que zero.");
        }

        if (veiculoRepository.buscarPorId(veiculo.getId()) == null) {
            throw new IllegalArgumentException("Veículo não encontrado.");
        }

        if (clienteRepository.buscarPorId(veiculo.getClienteId()) == null) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }

        veiculo.setPlaca(veiculo.getPlaca().trim());
        veiculo.setModelo(veiculo.getModelo().trim());

        veiculoRepository.atualizar(veiculo);
    }

    public void excluir(int id) throws SQLException {
        if (veiculoRepository.buscarPorId(id) == null) {
            throw new IllegalArgumentException("Veículo não encontrado.");
        }

        veiculoRepository.excluir(id);
    }

    public Veiculo buscarPorId(int id) throws SQLException {
        Veiculo veiculo = veiculoRepository.buscarPorId(id);
        if (veiculo == null) {
            throw new IllegalArgumentException("Veículo não encontrado.");
        }
        return veiculo;
    }

    public List<Veiculo> listar() throws SQLException {
        return veiculoRepository.listar();
    }

    public List<Veiculo> listarPorCliente(int clienteId) throws SQLException {
        if (clienteRepository.buscarPorId(clienteId) == null) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }

        return veiculoRepository.listarPorCliente(clienteId);
    }
}