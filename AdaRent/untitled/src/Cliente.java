public abstract class Cliente {
    protected String nome;
    protected String documento;

    public Cliente(String nome, String documento) {
        this.nome = nome;
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    @Override
    public String toString() {
        return nome + " (" + documento + ")";
    }
}

class PessoaFisica extends Cliente {
    public PessoaFisica(String nome, String cpf) {
        super(nome, cpf);
    }
}

class PessoaJuridica extends Cliente {
    public PessoaJuridica(String nome, String cnpj) {
        super(nome, cnpj);
    }
}
