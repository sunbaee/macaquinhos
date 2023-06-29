package macaquinhos.Macacos.macacos_pantano;

import macaquinhos.Ambientes.Ambiente;
import macaquinhos.Macacos.Macaco;

public class Cesar extends Macaco {
    public Cesar(String nome, int id) {
        super(nome, id);
        this.pedras = 0;
        this.taxaRoubo = 45; // * 2
        this.taxaColeta = 10;
        this.qntMaxRoubo = 110; // / 2
        this.taxaDefesa = this.defesaInicial = 25;
        this.ambiente = Ambiente.ambientes[3];
        this.tipo = 7;
    }
}
