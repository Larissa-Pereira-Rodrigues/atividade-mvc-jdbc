package br.com.atividade;

import br.com.atividade.controller.ClienteController;
import br.com.atividade.controller.OrdemServicoController;
import br.com.atividade.controller.VeiculoController;
import br.com.atividade.model.Cliente;
import br.com.atividade.model.OrdemServico;
import br.com.atividade.model.Veiculo;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static ClienteController clienteController = new ClienteController();
    static VeiculoController veiculoController = new VeiculoController();
    static OrdemServicoController ordemServicoController = new OrdemServicoController();

    public static void main(String[] args) {
        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            opcao = lerOpcao();

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> cadastrarVeiculo();
                case 3 -> abrirOrdemServico();
                case 4 -> listarClientes();
                case 5 -> listarVeiculos();
                case 6 -> listarOrdensServico();
                case 7 -> listarVeiculosPorCliente();
                case 8 -> listarOrdensPorVeiculo();
                case 0 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    static void exibirMenu() {
        System.out.println("\n===== Oficina Mecânica =====");
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Cadastrar Veículo");
        System.out.println("3 - Abrir Ordem de Serviço");
        System.out.println("4 - Listar Clientes");
        System.out.println("5 - Listar Veículos");
        System.out.println("6 - Listar Ordens de Serviço");
        System.out.println("7 - Listar Veículos por Cliente");
        System.out.println("8 - Listar Ordens por Veículo");
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

    static void cadastrarCliente() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();

            clienteController.cadastrar(nome, telefone);
            System.out.println("Cliente cadastrado com sucesso!");

        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    static void cadastrarVeiculo() {
        try {
            System.out.print("Placa: ");
            String placa = scanner.nextLine();

            System.out.print("Modelo: ");
            String modelo = scanner.nextLine();

            System.out.print("Ano: ");
            int ano = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("ID do Cliente: ");
            int clienteId = Integer.parseInt(scanner.nextLine().trim());

            veiculoController.cadastrar(placa, modelo, ano, clienteId);
            System.out.println("Veículo cadastrado com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("Valor inválido. Digite um número inteiro.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar veículo: " + e.getMessage());
        }
    }

    static void abrirOrdemServico() {
        try {
            System.out.print("ID do Veículo: ");
            int veiculoId = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();

            System.out.print("Valor: ");
            double valor = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("Status (ABERTA ou CONCLUIDA): ");
            String status = scanner.nextLine();

            ordemServicoController.cadastrar(veiculoId, descricao, valor, status);
            System.out.println("Ordem de serviço aberta com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("Valor inválido. Digite um número.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao abrir ordem de serviço: " + e.getMessage());
        }
    }

    static void listarClientes() {
        try {
            List<Cliente> clientes = clienteController.listar();

            if (clientes.isEmpty()) {
                System.out.println("Nenhum cliente cadastrado.");
                return;
            }

            System.out.println("\n===== Clientes =====");
            for (Cliente c : clientes) {
                System.out.println("ID: " + c.getId()
                        + " | Nome: " + c.getNome()
                        + " | Telefone: " + c.getTelefone());
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }
    }

    static void listarVeiculos() {
        try {
            List<Veiculo> veiculos = veiculoController.listar();

            if (veiculos.isEmpty()) {
                System.out.println("Nenhum veículo cadastrado.");
                return;
            }

            System.out.println("\n===== Veículos =====");
            for (Veiculo v : veiculos) {
                System.out.println("ID: " + v.getId()
                        + " | Placa: " + v.getPlaca()
                        + " | Modelo: " + v.getModelo()
                        + " | Ano: " + v.getAno()
                        + " | Cliente ID: " + v.getClienteId());
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar veículos: " + e.getMessage());
        }
    }

    static void listarOrdensServico() {
        try {
            List<OrdemServico> ordens = ordemServicoController.listar();

            if (ordens.isEmpty()) {
                System.out.println("Nenhuma ordem de serviço registrada.");
                return;
            }

            System.out.println("\n===== Ordens de Serviço =====");
            for (OrdemServico o : ordens) {
                System.out.println("ID: " + o.getId()
                        + " | Veículo ID: " + o.getVeiculoId()
                        + " | Descrição: " + o.getDescricao()
                        + " | Valor: R$ " + String.format("%.2f", o.getValor())
                        + " | Status: " + o.getStatus());
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar ordens de serviço: " + e.getMessage());
        }
    }

    static void listarVeiculosPorCliente() {
        try {
            System.out.print("ID do Cliente: ");
            int clienteId = Integer.parseInt(scanner.nextLine().trim());

            List<Veiculo> veiculos = veiculoController.listarPorCliente(clienteId);

            if (veiculos.isEmpty()) {
                System.out.println("Nenhum veículo encontrado para este cliente.");
                return;
            }

            System.out.println("\n===== Veículos do Cliente ID " + clienteId + " =====");
            for (Veiculo v : veiculos) {
                System.out.println("ID: " + v.getId()
                        + " | Placa: " + v.getPlaca()
                        + " | Modelo: " + v.getModelo()
                        + " | Ano: " + v.getAno());
            }

        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Digite um número inteiro.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao listar veículos por cliente: " + e.getMessage());
        }
    }

    static void listarOrdensPorVeiculo() {
        try {
            System.out.print("ID do Veículo: ");
            int veiculoId = Integer.parseInt(scanner.nextLine().trim());

            List<OrdemServico> ordens = ordemServicoController.listarPorVeiculo(veiculoId);

            if (ordens.isEmpty()) {
                System.out.println("Nenhuma ordem de serviço encontrada para este veículo.");
                return;
            }

            System.out.println("\n===== Ordens de Serviço do Veículo ID " + veiculoId + " =====");
            for (OrdemServico o : ordens) {
                System.out.println("ID: " + o.getId()
                        + " | Descrição: " + o.getDescricao()
                        + " | Valor: R$ " + String.format("%.2f", o.getValor())
                        + " | Status: " + o.getStatus());
            }

        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Digite um número inteiro.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao listar ordens por veículo: " + e.getMessage());
        }
    }
}