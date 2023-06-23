package macaquinhos.Macacos.macacos_floresta;

import macaquinhos.Ambientes.Ambiente;
import macaquinhos.Macacos.Macaco;

public class Orangotango extends Macaco {

    public Orangotango(String nome) {
        super(nome);
        this.pedras = 14;
        this.taxaRoubo = 28;
        this.taxaColeta = 15;
        this.qntMaxRoubo = 120;
        this.taxaDefesa = this.defesaInicial = 15;
        this.ambiente = Ambiente.ambientes[1];
        this.tipo = 3;
    }
    
}
