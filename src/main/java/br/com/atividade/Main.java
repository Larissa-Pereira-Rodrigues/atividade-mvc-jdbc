package br.com.atividade;

import br.com.atividade.util.Conexao;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try (Connection conexao = Conexao.getConnection()) {
            System.out.println("Conectado ao PostgreSQL!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar.");
            e.printStackTrace();
        }
    }
}