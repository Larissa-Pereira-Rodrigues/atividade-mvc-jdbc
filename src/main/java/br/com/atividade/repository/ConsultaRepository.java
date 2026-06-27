package br.com.atividade.repository;

import br.com.atividade.model.Consulta;
import br.com.atividade.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaRepository {

    public void inserir(Consulta consulta) throws SQLException {
        String sql = "INSERT INTO consulta (animal_id, data, motivo, valor) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, consulta.getAnimalId());
            stmt.setDate(2, Date.valueOf(consulta.getData()));
            stmt.setString(3, consulta.getMotivo());
            stmt.setDouble(4, consulta.getValor());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Consulta consulta) throws SQLException {
        String sql = "UPDATE consulta SET animal_id = ?, data = ?, motivo = ?, valor = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, consulta.getAnimalId());
            stmt.setDate(2, Date.valueOf(consulta.getData()));
            stmt.setString(3, consulta.getMotivo());
            stmt.setDouble(4, consulta.getValor());
            stmt.setInt(5, consulta.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM consulta WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Consulta buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM consulta WHERE id = ?";

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

    public List<Consulta> listar() throws SQLException {
        String sql = "SELECT * FROM consulta ORDER BY id";
        List<Consulta> consultas = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                consultas.add(mapear(rs));
            }
        }
        return consultas;
    }

    public List<Consulta> listarPorAnimal(int animalId) throws SQLException {
        String sql = "SELECT * FROM consulta WHERE animal_id = ? ORDER BY data";
        List<Consulta> consultas = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, animalId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    consultas.add(mapear(rs));
                }
            }
        }
        return consultas;
    }

    private Consulta mapear(ResultSet rs) throws SQLException {
        return new Consulta(
                rs.getInt("id"),
                rs.getInt("animal_id"),
                rs.getDate("data").toLocalDate(),
                rs.getString("motivo"),
                rs.getDouble("valor")
        );
    }
}