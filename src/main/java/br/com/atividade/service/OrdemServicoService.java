package br.com.atividade.service;

import br.com.atividade.model.OrdemServico;
import br.com.atividade.repository.OrdemServicoRepository;
import br.com.atividade.repository.VeiculoRepository;

import java.sql.SQLException;
import java.util.List;

public class OrdemServicoService {

    private OrdemServicoRepository ordemServicoRepository;
    private VeiculoRepository veiculoRepository;

    public OrdemServicoService() {
        this.ordemServicoRepository = new OrdemServicoRepository();
        this.veiculoRepository = new VeiculoRepository();
    }

    public void cadastrar(OrdemServico ordemServico) throws SQLException {
        if (veiculoRepository.buscarPorId(ordemServico.getVeiculoId()) == null) {
            throw new IllegalArgumentException("Veículo não encontrado. Cadastre o veículo antes de abrir uma ordem de serviço.");
        }

        if (ordemServico.getDescricao() == null || ordemServico.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição da ordem de serviço não pode ser vazia.");
        }

        if (ordemServico.getValor() < 0) {
            throw new IllegalArgumentException("Valor da ordem de serviço não pode ser negativo.");
        }

        if (ordemServico.getStatus() == null || ordemServico.getStatus().trim().isEmpty()) {
            throw new IllegalArgumentException("Status da ordem de serviço não pode ser vazio.");
        }

        String status = ordemServico.getStatus().trim().toUpperCase();
        if (!status.equals("ABERTA") && !status.equals("CONCLUIDA")) {
            throw new IllegalArgumentException("Status inválido. Use ABERTA ou CONCLUIDA.");
        }

        ordemServico.setDescricao(ordemServico.getDescricao().trim());
        ordemServico.setStatus(status);

        ordemServicoRepository.inserir(ordemServico);
    }

    public void atualizar(OrdemServico ordemServico) throws SQLException {
        if (ordemServicoRepository.buscarPorId(ordemServico.getId()) == null) {
            throw new IllegalArgumentException("Ordem de serviço não encontrada.");
        }

        if (veiculoRepository.buscarPorId(ordemServico.getVeiculoId()) == null) {
            throw new IllegalArgumentException("Veículo não encontrado.");
        }

        if (ordemServico.getDescricao() == null || ordemServico.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição da ordem de serviço não pode ser vazia.");
        }

        if (ordemServico.getValor() < 0) {
            throw new IllegalArgumentException("Valor da ordem de serviço não pode ser negativo.");
        }

        if (ordemServico.getStatus() == null || ordemServico.getStatus().trim().isEmpty()) {
            throw new IllegalArgumentException("Status da ordem de serviço não pode ser vazio.");
        }

        String status = ordemServico.getStatus().trim().toUpperCase();
        if (!status.equals("ABERTA") && !status.equals("CONCLUIDA")) {
            throw new IllegalArgumentException("Status inválido. Use ABERTA ou CONCLUIDA.");
        }

        ordemServico.setDescricao(ordemServico.getDescricao().trim());
        ordemServico.setStatus(status);

        ordemServicoRepository.atualizar(ordemServico);
    }

    public void excluir(int id) throws SQLException {
        if (ordemServicoRepository.buscarPorId(id) == null) {
            throw new IllegalArgumentException("Ordem de serviço não encontrada.");
        }

        ordemServicoRepository.excluir(id);
    }

    public OrdemServico buscarPorId(int id) throws SQLException {
        OrdemServico ordemServico = ordemServicoRepository.buscarPorId(id);
        if (ordemServico == null) {
            throw new IllegalArgumentException("Ordem de serviço não encontrada.");
        }
        return ordemServico;
    }

    public List<OrdemServico> listar() throws SQLException {
        return ordemServicoRepository.listar();
    }

    public List<OrdemServico> listarPorVeiculo(int veiculoId) throws SQLException {
        if (veiculoRepository.buscarPorId(veiculoId) == null) {
            throw new IllegalArgumentException("Veículo não encontrado.");
        }

        return ordemServicoRepository.listarPorVeiculo(veiculoId);
    }
}