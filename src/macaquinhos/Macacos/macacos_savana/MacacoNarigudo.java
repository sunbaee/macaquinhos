package macaquinhos.Macacos.macacos_savana;

import macaquinhos.Ambientes.Ambiente;
import macaquinhos.Macacos.Macaco;

public class MacacoNarigudo extends Macaco {
    public MacacoNarigudo(String nome, int id) {
        super(nome, id);
        this.pedras = 3;
        this.taxaRoubo = 60;
        this.taxaColeta = 17;
        this.qntMaxRoubo = 40;
        this.taxaDefesa = this.defesaInicial = 10;
        this.ambiente = Ambiente.ambientes[2];
        this.tipo = 6;
    }
}
