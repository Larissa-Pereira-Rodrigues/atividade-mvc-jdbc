package br.com.atividade.repository;

import br.com.atividade.model.OrdemServico;
import br.com.atividade.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdemServicoRepository {

    public void inserir(OrdemServico ordemServico) throws SQLException {
        String sql = "INSERT INTO ordem_servico (veiculo_id, descricao, valor, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ordemServico.getVeiculoId());
            stmt.setString(2, ordemServico.getDescricao());
            stmt.setDouble(3, ordemServico.getValor());
            stmt.setString(4, ordemServico.getStatus());
            stmt.executeUpdate();
        }
    }

    public void atualizar(OrdemServico ordemServico) throws SQLException {
        String sql = "UPDATE ordem_servico SET veiculo_id = ?, descricao = ?, valor = ?, status = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ordemServico.getVeiculoId());
            stmt.setString(2, ordemServico.getDescricao());
            stmt.setDouble(3, ordemServico.getValor());
            stmt.setString(4, ordemServico.getStatus());
            stmt.setInt(5, ordemServico.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM ordem_servico WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public OrdemServico buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM ordem_servico WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }
        }
        return null;
    }

    public List<OrdemServico> listar() throws SQLException {
        String sql = "SELECT * FROM ordem_servico ORDER BY id";
        List<OrdemServico> ordens = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ordens.add(mapear(rs));
            }
        }
        return ordens;
    }

    public List<OrdemServico> listarPorVeiculo(int veiculoId) throws SQLException {
        String sql = "SELECT * FROM ordem_servico WHERE veiculo_id = ? ORDER BY id";
        List<OrdemServico> ordens = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, veiculoId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ordens.add(mapear(rs));
                }
            }
        }
        return ordens;
    }

    private OrdemServico mapear(ResultSet rs) throws SQLException {
        return new OrdemServico(
                rs.getInt("id"),
                rs.getInt("veiculo_id"),
                rs.getString("descricao"),
                rs.getDouble("valor"),
                rs.getString("status")
        );
    }
}