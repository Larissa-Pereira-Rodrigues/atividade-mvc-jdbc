package br.com.atividade.repository;

import br.com.atividade.model.Animal;
import br.com.atividade.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalRepository {

    public void inserir(Animal animal) throws SQLException {
        String sql = "INSERT INTO animal (nome, especie, raca, tutor_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getEspecie());
            stmt.setString(3, animal.getRaca());
            stmt.setInt(4, animal.getTutorId());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Animal animal) throws SQLException {
        String sql = "UPDATE animal SET nome = ?, especie = ?, raca = ?, tutor_id = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getEspecie());
            stmt.setString(3, animal.getRaca());
            stmt.setInt(4, animal.getTutorId());
            stmt.setInt(5, animal.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM animal WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Animal buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM animal WHERE id = ?";

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

    public List<Animal> listar() throws SQLException {
        String sql = "SELECT * FROM animal ORDER BY id";
        List<Animal> animais = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                animais.add(mapear(rs));
            }
        }
        return animais;
    }

    public List<Animal> listarPorTutor(int tutorId) throws SQLException {
        String sql = "SELECT * FROM animal WHERE tutor_id = ? ORDER BY id";
        List<Animal> animais = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, tutorId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    animais.add(mapear(rs));
                }
            }
        }
        return animais;
    }

    private Animal mapear(ResultSet rs) throws SQLException {
        return new Animal(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("especie"),
                rs.getString("raca"),
                rs.getInt("tutor_id")
        );
    }
}