package macaquinhos.Macacos;

import macaquinhos.Ambientes.Ambiente;

public class Custom extends Macaco {
    public Custom(String nome, int pedras, int taxaColeta, int taxaRoubo, int qntMaxRoubo, int defesaInicial, int id) {
        super(nome, pedras, taxaColeta, taxaRoubo, qntMaxRoubo, defesaInicial, id);
        this.ambiente = Ambiente.ambientes[0];
        this.tipo = 0;
    }
}
