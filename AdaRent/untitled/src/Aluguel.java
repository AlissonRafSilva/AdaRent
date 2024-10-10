import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Aluguel {
    private Veiculo veiculo;
    private Cliente cliente;
    private Agencia agenciaRetirada;
    private Agencia agenciaDevolucao;
    private LocalDate dataAluguel;
    private LocalDate dataDevolucao;
    private double valorTotal;

    public Aluguel(Veiculo veiculo, Cliente cliente, Agencia agenciaRetirada, LocalDate dataAluguel) {
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.agenciaRetirada = agenciaRetirada;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = null;
        this.valorTotal = 0.0;
    }

    public void devolver(Agencia agenciaDevolucao, LocalDate dataDevolucao) {
        this.agenciaDevolucao = agenciaDevolucao;
        this.dataDevolucao = dataDevolucao;
        calcularValorAluguel();
        veiculo.setDisponivel(true);
    }

    private void calcularValorAluguel() {
        long diasAlugados = ChronoUnit.DAYS.between(dataAluguel, dataDevolucao);
        valorTotal = diasAlugados * veiculo.getValorDiaria();

        if (cliente instanceof PessoaFisica && diasAlugados > 5) {
            valorTotal *= 0.95;
        } else if (cliente instanceof PessoaJuridica && diasAlugados > 3) {
            valorTotal *= 0.90;
        }
    }

    public double getValorTotal() {
        return valorTotal;
    }

    @Override
    public String toString() {
        return "Aluguel de " + veiculo + " para " + cliente + " na agência " + agenciaRetirada +
                " no dia " + dataAluguel + ". Devolução em " + agenciaDevolucao +
                " no dia " + dataDevolucao + ". Valor total: R$ " + valorTotal;
    }
}
