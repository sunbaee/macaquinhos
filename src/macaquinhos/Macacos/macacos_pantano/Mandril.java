package macaquinhos.Macacos.macacos_pantano;

import macaquinhos.Ambientes.Ambiente;
import macaquinhos.Macacos.Macaco;

public class Mandril extends Macaco {
    public Mandril(String nome, int id) {
        super(nome, id);
        this.pedras = 2;
        this.taxaRoubo = 48;
        this.taxaColeta = 14;
        this.qntMaxRoubo = 92;
        this.taxaDefesa = this.defesaInicial = 22;
        this.ambiente = Ambiente.ambientes[3];
        this.tipo = 9;
    }
}
