import java.time.LocalDate;

public class LocadoraService {
    private Repositorio repositorio = new Repositorio();

    public void cadastrarVeiculo(Veiculo veiculo) {
        repositorio.adicionarVeiculo(veiculo);
    }

    public void cadastrarAgencia(Agencia agencia) {
        repositorio.adicionarAgencia(agencia);
    }

    public void cadastrarCliente(Cliente cliente) {
        repositorio.adicionarCliente(cliente);
    }

    public Aluguel alugarVeiculo(String placa, Cliente cliente, Agencia agencia, LocalDate dataAluguel) {
        Veiculo veiculo = buscarVeiculoPorPlaca(placa);
        if (veiculo == null || !veiculo.isDisponivel()) {
            System.out.println("Veículo não disponível.");
            return null;
        }
        veiculo.setDisponivel(false);
        Aluguel aluguel = new Aluguel(veiculo, cliente, agencia, dataAluguel);
        repositorio.registrarAluguel(aluguel);
        return aluguel;
    }

    public void devolverVeiculo(Aluguel aluguel, Agencia agenciaDevolucao, LocalDate dataDevolucao) {
        aluguel.devolver(agenciaDevolucao, dataDevolucao);
        System.out.println(aluguel);
    }

    public Veiculo buscarVeiculoPorPlaca(String placa) {
        for (Veiculo v : repositorio.buscarVeiculoPorNome("")) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null;
    }

    public Cliente buscarClientePorNome(String nomeCliente) {
        return null;
    }

    public Agencia buscarAgenciaPorNome(String nomeAgencia) {
        return null;
    }

    public Aluguel buscarAluguelPorVeiculo(String placa) {
        return null;
    }
}
