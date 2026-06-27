package br.com.atividade.controller;

import br.com.atividade.model.OrdemServico;
import br.com.atividade.service.OrdemServicoService;

import java.sql.SQLException;
import java.util.List;

public class OrdemServicoController {

    private OrdemServicoService ordemServicoService;

    public OrdemServicoController() {
        this.ordemServicoService = new OrdemServicoService();
    }

    public void cadastrar(int veiculoId, String descricao, double valor, String status) throws SQLException {
        OrdemServico ordemServico = new OrdemServico(veiculoId, descricao, valor, status);
        ordemServicoService.cadastrar(ordemServico);
    }

    public void atualizar(int id, int veiculoId, String descricao, double valor, String status) throws SQLException {
        OrdemServico ordemServico = new OrdemServico(id, veiculoId, descricao, valor, status);
        ordemServicoService.atualizar(ordemServico);
    }

    public void excluir(int id) throws SQLException {
        ordemServicoService.excluir(id);
    }

    public OrdemServico buscarPorId(int id) throws SQLException {
        return ordemServicoService.buscarPorId(id);
    }

    public List<OrdemServico> listar() throws SQLException {
        return ordemServicoService.listar();
    }

    public List<OrdemServico> listarPorVeiculo(int veiculoId) throws SQLException {
        return ordemServicoService.listarPorVeiculo(veiculoId);
    }
}