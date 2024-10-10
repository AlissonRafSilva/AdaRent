public abstract class Veiculo {
    protected String placa;
    protected String modelo;
    protected boolean disponivel;
    protected double valorDiaria;

    public Veiculo(String placa, String modelo, double valorDiaria) {
        this.placa = placa;
        this.modelo = modelo;
        this.disponivel = true;
        this.valorDiaria = valorDiaria;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    @Override
    public String toString() {
        return modelo + " (" + placa + ")";
    }
}

class Carro extends Veiculo {
    public Carro(String placa, String modelo) {
        super(placa, modelo, 150.0);
    }
}

class Moto extends Veiculo {
    public Moto(String placa, String modelo) {
        super(placa, modelo, 100.0);
    }
}

class Caminhao extends Veiculo {
    public Caminhao(String placa, String modelo) {
        super(placa, modelo, 200.0);
    }
}