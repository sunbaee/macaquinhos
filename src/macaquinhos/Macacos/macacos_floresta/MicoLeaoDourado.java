package macaquinhos.Macacos.macacos_floresta;

import macaquinhos.Ambientes.Ambiente;
import macaquinhos.Macacos.Macaco;

public class MicoLeaoDourado extends Macaco {

    public MicoLeaoDourado(String nome, int id) {
        super(nome, id);
        this.pedras = 5;
        this.taxaRoubo = 40;
        this.taxaColeta = 18;
        this.qntMaxRoubo = 60;
        this.taxaDefesa = this.defesaInicial = 12;
        this.ambiente = Ambiente.ambientes[1];
        this.tipo = 2;
    }
    
}
