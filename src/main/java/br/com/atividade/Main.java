package br.com.atividade;

import br.com.atividade.controller.AlunoController;
import br.com.atividade.controller.CursoController;
import br.com.atividade.controller.MatriculaController;
import br.com.atividade.model.Aluno;
import br.com.atividade.model.Curso;
import br.com.atividade.model.Matricula;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static AlunoController alunoController = new AlunoController();
    static CursoController cursoController = new CursoController();
    static MatriculaController matriculaController = new MatriculaController();

    public static void main(String[] args) {
        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            opcao = lerOpcao();

            switch (opcao) {
                case 1 -> cadastrarAluno();
                case 2 -> cadastrarCurso();
                case 3 -> realizarMatricula();
                case 4 -> listarAlunos();
                case 5 -> listarCursos();
                case 6 -> listarMatriculas();
                case 7 -> listarMatriculasPorAluno();
                case 8 -> listarMatriculasPorCurso();
                case 0 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    static void exibirMenu() {
        System.out.println("\nSistema Acadêmico");
        System.out.println("\n1 - Cadastrar Aluno");
        System.out.println("2 - Cadastrar Curso");
        System.out.println("3 - Realizar Matrícula");
        System.out.println("4 - Listar Alunos");
        System.out.println("5 - Listar Cursos");
        System.out.println("6 - Listar Matrículas");
        System.out.println("7 - Listar Matrículas por Aluno");
        System.out.println("8 - Listar Matrículas por Curso");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    static void cadastrarAluno() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();

            alunoController.cadastrar(nome, email, telefone);
            System.out.println("Aluno cadastrado com sucesso!");

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
        }
    }

    static void cadastrarCurso() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();

            System.out.print("Carga Horária: ");
            int cargaHoraria = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Vagas Totais: ");
            int vagasTotais = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Vagas Disponíveis: ");
            int vagasDisponiveis = Integer.parseInt(scanner.nextLine().trim());

            cursoController.cadastrar(nome, descricao, cargaHoraria, vagasTotais, vagasDisponiveis);
            System.out.println("Curso cadastrado com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("Valor inválido. Digite um número inteiro.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar curso: " + e.getMessage());
        }
    }

    static void realizarMatricula() {
        try {
            System.out.print("ID do Aluno: ");
            int alunoId = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("ID do Curso: ");
            int cursoId = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Data da Matrícula (AAAA-MM-DD): ");
            LocalDate dataMatricula = LocalDate.parse(scanner.nextLine().trim());

            System.out.print("Valor: ");
            double valor = Double.parseDouble(scanner.nextLine().trim());

            matriculaController.cadastrar(alunoId, cursoId, dataMatricula, valor);
            System.out.println("Matrícula realizada com sucesso!");

        } catch (DateTimeParseException e) {
            System.out.println("Data inválida. Use o formato AAAA-MM-DD.");
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido. Digite um número.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao realizar matrícula: " + e.getMessage());
        }
    }

    static void listarAlunos() {
        try {
            List<Aluno> alunos = alunoController.listar();

            if (alunos.isEmpty()) {
                System.out.println("Nenhum aluno cadastrado.");
                return;
            }

            System.out.println("\n===== Alunos =====");
            for (Aluno a : alunos) {
                System.out.println("ID: " + a.getId()
                        + " | Nome: " + a.getNome()
                        + " | Email: " + a.getEmail()
                        + " | Telefone: " + a.getTelefone());
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar alunos: " + e.getMessage());
        }
    }

    static void listarCursos() {
        try {
            List<Curso> cursos = cursoController.listar();

            if (cursos.isEmpty()) {
                System.out.println("Nenhum curso cadastrado.");
                return;
            }

            System.out.println("\n===== Cursos =====");
            for (Curso c : cursos) {
                System.out.println("ID: " + c.getId()
                        + " | Nome: " + c.getNome()
                        + " | Descrição: " + c.getDescricao()
                        + " | Carga Horária: " + c.getCargaHoraria() + "h"
                        + " | Vagas Totais: " + c.getVagasTotais()
                        + " | Vagas Disponíveis: " + c.getVagasDisponiveis());
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar cursos: " + e.getMessage());
        }
    }

    static void listarMatriculas() {
        try {
            List<Matricula> matriculas = matriculaController.listar();

            if (matriculas.isEmpty()) {
                System.out.println("Nenhuma matrícula registrada.");
                return;
            }

            System.out.println("\n===== Matrículas =====");
            for (Matricula m : matriculas) {
                System.out.println("ID: " + m.getId()
                        + " | Aluno ID: " + m.getAlunoId()
                        + " | Curso ID: " + m.getCursoId()
                        + " | Data: " + m.getDataMatricula()
                        + " | Valor: R$ " + String.format("%.2f", m.getValor()));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar matrículas: " + e.getMessage());
        }
    }

    static void listarMatriculasPorAluno() {
        try {
            System.out.print("ID do Aluno: ");
            int alunoId = Integer.parseInt(scanner.nextLine().trim());

            List<Matricula> matriculas = matriculaController.listarPorAluno(alunoId);

            if (matriculas.isEmpty()) {
                System.out.println("Nenhuma matrícula encontrada para este aluno.");
                return;
            }

            System.out.println("\n===== Matrículas do Aluno ID " + alunoId + " =====");
            for (Matricula m : matriculas) {
                System.out.println("ID: " + m.getId()
                        + " | Curso ID: " + m.getCursoId()
                        + " | Data: " + m.getDataMatricula()
                        + " | Valor: R$ " + String.format("%.2f", m.getValor()));
            }

        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Digite um número inteiro.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao listar matrículas por aluno: " + e.getMessage());
        }
    }

    static void listarMatriculasPorCurso() {
        try {
            System.out.print("ID do Curso: ");
            int cursoId = Integer.parseInt(scanner.nextLine().trim());

            List<Matricula> matriculas = matriculaController.listarPorCurso(cursoId);

            if (matriculas.isEmpty()) {
                System.out.println("Nenhuma matrícula encontrada para este curso.");
                return;
            }

            System.out.println("\n===== Matrículas do Curso ID " + cursoId + " =====");
            for (Matricula m : matriculas) {
                System.out.println("ID: " + m.getId()
                        + " | Aluno ID: " + m.getAlunoId()
                        + " | Data: " + m.getDataMatricula()
                        + " | Valor: R$ " + String.format("%.2f", m.getValor()));
            }

        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Digite um número inteiro.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao listar matrículas por curso: " + e.getMessage());
        }
    }
}