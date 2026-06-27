package br.com.atividade;

import br.com.atividade.controller.AnimalController;
import br.com.atividade.controller.ConsultaController;
import br.com.atividade.controller.TutorController;
import br.com.atividade.model.Animal;
import br.com.atividade.model.Consulta;
import br.com.atividade.model.Tutor;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static TutorController tutorController = new TutorController();
    static AnimalController animalController = new AnimalController();
    static ConsultaController consultaController = new ConsultaController();

    public static void main(String[] args) {
        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            opcao = lerOpcao();

            switch (opcao) {
                case 1 -> cadastrarTutor();
                case 2 -> cadastrarAnimal();
                case 3 -> registrarConsulta();
                case 4 -> listarTutores();
                case 5 -> listarAnimais();
                case 6 -> listarConsultas();
                case 7 -> listarAnimaisPorTutor();
                case 8 -> listarConsultasPorAnimal();
                case 0 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    static void exibirMenu() {
        System.out.println("\n===== Clínica Veterinária =====");
        System.out.println("1 - Cadastrar Tutor");
        System.out.println("2 - Cadastrar Animal");
        System.out.println("3 - Registrar Consulta");
        System.out.println("4 - Listar Tutores");
        System.out.println("5 - Listar Animais");
        System.out.println("6 - Listar Consultas");
        System.out.println("7 - Listar Animais por Tutor");
        System.out.println("8 - Listar Consultas por Animal");
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

    static void cadastrarTutor() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Endereço: ");
            String endereco = scanner.nextLine();

            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();

            tutorController.cadastrar(nome, endereco, telefone);
            System.out.println("Tutor cadastrado com sucesso!");

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar tutor: " + e.getMessage());
        }
    }

    static void cadastrarAnimal() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Espécie: ");
            String especie = scanner.nextLine();

            System.out.print("Raça: ");
            String raca = scanner.nextLine();

            System.out.print("ID do Tutor: ");
            int tutorId = Integer.parseInt(scanner.nextLine().trim());

            animalController.cadastrar(nome, especie, raca, tutorId);
            System.out.println("Animal cadastrado com sucesso!");

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar animal: " + e.getMessage());
        }
    }

    static void registrarConsulta() {
        try {
            System.out.print("ID do Animal: ");
            int animalId = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Data (AAAA-MM-DD): ");
            LocalDate data = LocalDate.parse(scanner.nextLine().trim());

            System.out.print("Motivo: ");
            String motivo = scanner.nextLine();

            System.out.print("Valor: ");
            double valor = Double.parseDouble(scanner.nextLine().trim());

            consultaController.cadastrar(animalId, data, motivo, valor);
            System.out.println("Consulta registrada com sucesso!");

        } catch (DateTimeParseException e) {
            System.out.println("Data inválida. Use o formato AAAA-MM-DD.");
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido. Digite um número.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao registrar consulta: " + e.getMessage());
        }
    }

    static void listarTutores() {
        try {
            List<Tutor> tutores = tutorController.listar();

            if (tutores.isEmpty()) {
                System.out.println("Nenhum tutor cadastrado.");
                return;
            }

            System.out.println("\n===== Tutores =====");
            for (Tutor t : tutores) {
                System.out.println("ID: " + t.getId()
                        + " | Nome: " + t.getNome()
                        + " | Endereço: " + t.getEndereco()
                        + " | Telefone: " + t.getTelefone());
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar tutores: " + e.getMessage());
        }
    }

    static void listarAnimais() {
        try {
            List<Animal> animais = animalController.listar();

            if (animais.isEmpty()) {
                System.out.println("Nenhum animal cadastrado.");
                return;
            }

            System.out.println("\n===== Animais =====");
            for (Animal a : animais) {
                System.out.println("ID: " + a.getId()
                        + " | Nome: " + a.getNome()
                        + " | Espécie: " + a.getEspecie()
                        + " | Raça: " + a.getRaca()
                        + " | Tutor ID: " + a.getTutorId());
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar animais: " + e.getMessage());
        }
    }

    static void listarConsultas() {
        try {
            List<Consulta> consultas = consultaController.listar();

            if (consultas.isEmpty()) {
                System.out.println("Nenhuma consulta registrada.");
                return;
            }

            System.out.println("\n===== Consultas =====");
            for (Consulta c : consultas) {
                System.out.println("ID: " + c.getId()
                        + " | Animal ID: " + c.getAnimalId()
                        + " | Data: " + c.getData()
                        + " | Motivo: " + c.getMotivo()
                        + " | Valor: R$ " + String.format("%.2f", c.getValor()));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar consultas: " + e.getMessage());
        }
    }

    static void listarAnimaisPorTutor() {
        try {
            System.out.print("ID do Tutor: ");
            int tutorId = Integer.parseInt(scanner.nextLine().trim());

            List<Animal> animais = animalController.listarPorTutor(tutorId);

            if (animais.isEmpty()) {
                System.out.println("Nenhum animal encontrado para este tutor.");
                return;
            }

            System.out.println("\n===== Animais do Tutor ID " + tutorId + " =====");
            for (Animal a : animais) {
                System.out.println("ID: " + a.getId()
                        + " | Nome: " + a.getNome()
                        + " | Espécie: " + a.getEspecie()
                        + " | Raça: " + a.getRaca());
            }

        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Digite um número inteiro.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao listar animais por tutor: " + e.getMessage());
        }
    }

    static void listarConsultasPorAnimal() {
        try {
            System.out.print("ID do Animal: ");
            int animalId = Integer.parseInt(scanner.nextLine().trim());

            List<Consulta> consultas = consultaController.listarPorAnimal(animalId);

            if (consultas.isEmpty()) {
                System.out.println("Nenhuma consulta encontrada para este animal.");
                return;
            }

            System.out.println("\n===== Consultas do Animal ID " + animalId + " =====");
            for (Consulta c : consultas) {
                System.out.println("ID: " + c.getId()
                        + " | Data: " + c.getData()
                        + " | Motivo: " + c.getMotivo()
                        + " | Valor: R$ " + String.format("%.2f", c.getValor()));
            }

        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Digite um número inteiro.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao listar consultas por animal: " + e.getMessage());
        }
    }
}