package br.com.atividade.repository;

import br.com.atividade.model.Curso;
import br.com.atividade.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoRepository {

    public void inserir(Curso curso) throws SQLException {
        String sql = "INSERT INTO curso (nome, descricao, carga_horaria, vagas_totais, vagas_disponiveis) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getDescricao());
            stmt.setInt(3, curso.getCargaHoraria());
            stmt.setInt(4, curso.getVagasTotais());
            stmt.setInt(5, curso.getVagasDisponiveis());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Curso curso) throws SQLException {
        String sql = "UPDATE curso SET nome = ?, descricao = ?, carga_horaria = ?, " +
                "vagas_totais = ?, vagas_disponiveis = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getDescricao());
            stmt.setInt(3, curso.getCargaHoraria());
            stmt.setInt(4, curso.getVagasTotais());
            stmt.setInt(5, curso.getVagasDisponiveis());
            stmt.setInt(6, curso.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM curso WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Curso buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM curso WHERE id = ?";

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

    public List<Curso> listar() throws SQLException {
        String sql = "SELECT * FROM curso ORDER BY id";
        List<Curso> cursos = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                cursos.add(mapear(rs));
            }
        }
        return cursos;
    }

    public void atualizarVagasDisponiveis(int cursoId, int vagasDisponiveis) throws SQLException {
        String sql = "UPDATE curso SET vagas_disponiveis = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vagasDisponiveis);
            stmt.setInt(2, cursoId);
            stmt.executeUpdate();
        }
    }

    private Curso mapear(ResultSet rs) throws SQLException {
        return new Curso(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("descricao"),
                rs.getInt("carga_horaria"),
                rs.getInt("vagas_totais"),
                rs.getInt("vagas_disponiveis")
        );
    }
}