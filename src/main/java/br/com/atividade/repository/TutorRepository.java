package br.com.atividade.repository;

import br.com.atividade.model.Tutor;
import br.com.atividade.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TutorRepository {

    public void inserir(Tutor tutor) throws SQLException {
        String sql = "INSERT INTO tutor (nome, endereco, telefone) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tutor.getNome());
            stmt.setString(2, tutor.getEndereco());
            stmt.setString(3, tutor.getTelefone());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Tutor tutor) throws SQLException {
        String sql = "UPDATE tutor SET nome = ?, endereco = ?, telefone = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tutor.getNome());
            stmt.setString(2, tutor.getEndereco());
            stmt.setString(3, tutor.getTelefone());
            stmt.setInt(4, tutor.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM tutor WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Tutor buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM tutor WHERE id = ?";

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

    public List<Tutor> listar() throws SQLException {
        String sql = "SELECT * FROM tutor ORDER BY id";
        List<Tutor> tutores = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tutores.add(mapear(rs));
            }
        }
        return tutores;
    }

    private Tutor mapear(ResultSet rs) throws SQLException {
        return new Tutor(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("endereco"),
                rs.getString("telefone")
        );
    }
}