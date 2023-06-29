package macaquinhos.Macacos.macacos_pantano;

import macaquinhos.Ambientes.Ambiente;
import macaquinhos.Macacos.Macaco;

public class Gorila extends Macaco {
    public Gorila(String nome, int id) {
        super(nome, id);
        this.pedras = 1;
        this.taxaRoubo = 47;
        this.taxaColeta = 20;
        this.qntMaxRoubo = 90;
        this.taxaDefesa = this.defesaInicial = 20;
        this.ambiente = Ambiente.ambientes[3];
        this.tipo = 8;
    }
}
