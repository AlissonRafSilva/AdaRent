import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LocadoraService locadora = new LocadoraService();
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("\n*** Ada Rent's - Sistema de Locadora ***");
            System.out.println("1. Cadastrar Veículo");
            System.out.println("2. Cadastrar Cliente");
            System.out.println("3. Alugar Veículo");
            System.out.println("4. Devolver Veículo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarVeiculo(scanner, locadora);
                    break;
                case 2:
                    cadastrarCliente(scanner, locadora);
                    break;
                case 3:
                    alugarVeiculo(scanner, locadora);
                    break;
                case 4:
                    devolverVeiculo(scanner, locadora);
                    break;
                case 0:
                    System.out.println("Encerrando sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void cadastrarVeiculo(Scanner scanner, LocadoraService locadora) {
        System.out.println("\n*** Cadastro de Veículo ***");
        System.out.print("Informe o tipo de veículo (1. Carro, 2. Moto, 3. Caminhão): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Informe a placa do veículo: ");
        String placa = scanner.nextLine();

        System.out.print("Informe o modelo do veículo: ");
        String modelo = scanner.nextLine();

        switch (tipo) {
            case 1:
                locadora.cadastrarVeiculo(new Carro(placa, modelo));
                break;
            case 2:
                locadora.cadastrarVeiculo(new Moto(placa, modelo));
                break;
            case 3:
                locadora.cadastrarVeiculo(new Caminhao(placa, modelo));
                break;
            default:
                System.out.println("Tipo de veículo inválido.");
                return;
        }
        System.out.println("Veículo cadastrado com sucesso!");
    }

    private static void cadastrarCliente(Scanner scanner, LocadoraService locadora) {
        System.out.println("\n*** Cadastro de Cliente ***");
        System.out.print("Informe o tipo de cliente (1. Pessoa Física, 2. Pessoa Jurídica): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Informe o nome do cliente: ");
        String nome = scanner.nextLine();

        if (tipo == 1) {
            System.out.print("Informe o CPF do cliente: ");
            String cpf = scanner.nextLine();
            locadora.cadastrarCliente(new PessoaFisica(nome, cpf));
        } else if (tipo == 2) {
            System.out.print("Informe o CNPJ da empresa: ");
            String cnpj = scanner.nextLine();
            locadora.cadastrarCliente(new PessoaJuridica(nome, cnpj));
        } else {
            System.out.println("Tipo de cliente inválido.");
            return;
        }
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void alugarVeiculo(Scanner scanner, LocadoraService locadora) {
        System.out.println("\n*** Aluguel de Veículo ***");

        System.out.print("Informe a placa do veículo: ");
        String placa = scanner.nextLine();

        System.out.print("Informe o nome do cliente: ");
        String nomeCliente = scanner.nextLine();

        Cliente cliente = locadora.buscarClientePorNome(nomeCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.print("Informe a agência de retirada: ");
        String nomeAgencia = scanner.nextLine();
        Agencia agencia = locadora.buscarAgenciaPorNome(nomeAgencia);
        if (agencia == null) {
            System.out.println("Agência não encontrada.");
            return;
        }

        Aluguel aluguel = locadora.alugarVeiculo(placa, cliente, agencia, LocalDate.now());
        if (aluguel != null) {
            System.out.println("Veículo alugado com sucesso!");
        } else {
            System.out.println("Falha ao alugar veículo.");
        }
    }

    private static void devolverVeiculo(Scanner scanner, LocadoraService locadora) {
        System.out.println("\n*** Devolução de Veículo ***");

        System.out.print("Informe a placa do veículo: ");
        String placa = scanner.nextLine();

        Aluguel aluguel = locadora.buscarAluguelPorVeiculo(placa);
        if (aluguel == null) {
            System.out.println("Veículo não encontrado ou não está alugado.");
            return;
        }

        System.out.print("Informe a agência de devolução: ");
        String nomeAgencia = scanner.nextLine();
        Agencia agenciaDevolucao = locadora.buscarAgenciaPorNome(nomeAgencia);
        if (agenciaDevolucao == null) {
            System.out.println("Agência não encontrada.");
            return;
        }

        System.out.print("Informe o número de dias de aluguel: ");
        int dias = scanner.nextInt();
        scanner.nextLine();

        aluguel.devolver(agenciaDevolucao, LocalDate.now().plusDays(dias));
        System.out.println("Veículo devolvido com sucesso! Valor total: R$ " + aluguel.getValorTotal());
    }
}
