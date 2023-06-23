package macaquinhos.Macacos;

import macaquinhos.Ambientes.Ambiente;

public class Custom extends Macaco {
    public Custom(String nome, int pedras, int taxaColeta, int taxaRoubo, int qntMaxRoubo, int defesaInicial) {
        super(nome, pedras, taxaColeta, taxaRoubo, qntMaxRoubo, defesaInicial);
        this.ambiente = Ambiente.ambientes[0];
        this.tipo = 0;
    }
}
