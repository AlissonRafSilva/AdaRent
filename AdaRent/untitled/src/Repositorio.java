import java.util.ArrayList;
import java.util.List;

public class Repositorio {
    private List<Veiculo> veiculos = new ArrayList<>();
    private List<Agencia> agencias = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private List<Aluguel> alugueis = new ArrayList<>();

    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public void adicionarAgencia(Agencia agencia) {
        agencias.add(agencia);
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void registrarAluguel(Aluguel aluguel) {
        alugueis.add(aluguel);
    }

    public List<Veiculo> buscarVeiculoPorNome(String nome) {
        List<Veiculo> resultado = new ArrayList<>();
        for (Veiculo v : veiculos) {
            if (v.getModelo().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(v);
            }
        }
        return resultado;
    }

    public List<Agencia> buscarAgenciaPorNomeOuLogradouro(String busca) {
        List<Agencia> resultado = new ArrayList<>();
        for (Agencia a : agencias) {
            if (a.getNome().toLowerCase().contains(busca.toLowerCase()) ||
                    a.getLogradouro().toLowerCase().contains(busca.toLowerCase())) {
                resultado.add(a);
            }
        }
        return resultado;
    }
}
