package br.com.atividade.repository;

import br.com.atividade.model.Matricula;
import br.com.atividade.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatriculaRepository {

    public void inserir(Matricula matricula) throws SQLException {
        String sql = "INSERT INTO matricula (aluno_id, curso_id, data_matricula, valor) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, matricula.getAlunoId());
            stmt.setInt(2, matricula.getCursoId());
            stmt.setDate(3, Date.valueOf(matricula.getDataMatricula()));
            stmt.setDouble(4, matricula.getValor());
            stmt.executeUpdate();
        }
    }

    public void atualizar(Matricula matricula) throws SQLException {
        String sql = "UPDATE matricula SET aluno_id = ?, curso_id = ?, data_matricula = ?, valor = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, matricula.getAlunoId());
            stmt.setInt(2, matricula.getCursoId());
            stmt.setDate(3, Date.valueOf(matricula.getDataMatricula()));
            stmt.setDouble(4, matricula.getValor());
            stmt.setInt(5, matricula.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM matricula WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Matricula buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM matricula WHERE id = ?";

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

    public List<Matricula> listar() throws SQLException {
        String sql = "SELECT * FROM matricula ORDER BY id";
        List<Matricula> matriculas = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                matriculas.add(mapear(rs));
            }
        }
        return matriculas;
    }

    public List<Matricula> listarPorAluno(int alunoId) throws SQLException {
        String sql = "SELECT * FROM matricula WHERE aluno_id = ? ORDER BY id";
        List<Matricula> matriculas = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, alunoId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    matriculas.add(mapear(rs));
                }
            }
        }
        return matriculas;
    }

    public List<Matricula> listarPorCurso(int cursoId) throws SQLException {
        String sql = "SELECT * FROM matricula WHERE curso_id = ? ORDER BY id";
        List<Matricula> matriculas = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cursoId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    matriculas.add(mapear(rs));
                }
            }
        }
        return matriculas;
    }

    public boolean existeMatricula(int alunoId, int cursoId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM matricula WHERE aluno_id = ? AND curso_id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, alunoId);
            stmt.setInt(2, cursoId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    private Matricula mapear(ResultSet rs) throws SQLException {
        return new Matricula(
                rs.getInt("id"),
                rs.getInt("aluno_id"),
                rs.getInt("curso_id"),
                rs.getDate("data_matricula").toLocalDate(),
                rs.getDouble("valor")
        );
    }
}