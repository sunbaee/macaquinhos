package macaquinhos.Macacos.macacos_floresta;

import macaquinhos.Ambientes.Ambiente;
import macaquinhos.Macacos.Macaco;

public class MacacoPrego extends Macaco {

    public MacacoPrego(String nome) {
        super(nome);
        this.pedras = 8;
        this.taxaRoubo = 35;
        this.taxaColeta = 12;
        this.qntMaxRoubo = 70;
        this.taxaDefesa = this.defesaInicial = 10;
        this.ambiente = Ambiente.ambientes[1];
        this.tipo = 1;
    }
    
}
