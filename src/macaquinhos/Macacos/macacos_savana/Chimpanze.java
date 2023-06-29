package macaquinhos.Macacos.macacos_savana;

import macaquinhos.Ambientes.Ambiente;
import macaquinhos.Macacos.Macaco;

public class Chimpanze extends Macaco {
    public Chimpanze(String nome, int id) {
        super(nome, id);
        this.pedras = 7;
        this.taxaRoubo = 55;
        this.taxaColeta = 15;
        this.qntMaxRoubo = 60;
        this.taxaDefesa = this.defesaInicial = 8;
        this.ambiente = Ambiente.ambientes[2];
        this.tipo = 5;
    }
}
