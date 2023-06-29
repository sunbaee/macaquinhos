package macaquinhos.Macacos.macacos_savana;

import macaquinhos.Ambientes.Ambiente;
import macaquinhos.Macacos.Macaco;

public class Bonobo extends Macaco {
    public Bonobo(String nome, int id) {
        super(nome, id);
        this.pedras = 5;
        this.taxaRoubo = 45;
        this.taxaColeta = 15;
        this.qntMaxRoubo = 90;
        this.taxaDefesa = this.defesaInicial = 15;
        this.ambiente = Ambiente.ambientes[2];
        this.tipo = 4;
    }
}
