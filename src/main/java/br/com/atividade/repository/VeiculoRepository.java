package br.com.atividade.repository;

import br.com.atividade.model.Veiculo;
import br.com.atividade.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoRepository {

    public void inserir(Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO veiculo (placa, modelo, ano, cliente_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAno());
            stmt.setInt(4, veiculo.getClienteId());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Veiculo veiculo) throws SQLException {
        String sql = "UPDATE veiculo SET placa = ?, modelo = ?, ano = ?, cliente_id = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAno());
            stmt.setInt(4, veiculo.getClienteId());
            stmt.setInt(5, veiculo.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM veiculo WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Veiculo buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM veiculo WHERE id = ?";

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

    public List<Veiculo> listar() throws SQLException {
        String sql = "SELECT * FROM veiculo ORDER BY id";
        List<Veiculo> veiculos = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                veiculos.add(mapear(rs));
            }
        }
        return veiculos;
    }

    public List<Veiculo> listarPorCliente(int clienteId) throws SQLException {
        String sql = "SELECT * FROM veiculo WHERE cliente_id = ? ORDER BY id";
        List<Veiculo> veiculos = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clienteId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    veiculos.add(mapear(rs));
                }
            }
        }
        return veiculos;
    }

    private Veiculo mapear(ResultSet rs) throws SQLException {
        return new Veiculo(
                rs.getInt("id"),
                rs.getString("placa"),
                rs.getString("modelo"),
                rs.getInt("ano"),
                rs.getInt("cliente_id")
        );
    }
}