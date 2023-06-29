package macaquinhos.Macacos;

import macaquinhos.Ambientes.Ambiente;

public class Custom extends Macaco {
    public Custom(String nome, int pedras, int taxaColeta, int taxaRoubo, int qntMaxRoubo, int defesaInicial, int id) {
        super(nome, pedras, taxaColeta, taxaRoubo, qntMaxRoubo, defesaInicial, id);
        this.ambiente = Ambiente.ambientes[0];
        this.tipo = 0;
    }
    
    @Override
    public int coletar() {
        int numPedras = Macaco.random(1, taxaColeta);
        this.pedras += numPedras;
        this.taxaDefesa += 1;

        return numPedras;
    }
    
    @Override
    public void mostrarDados() {
        System.out.println("\n   " + this.nome + "\n" +
                           "\n > PEDRAS INICIAIS: " + this.pedras + 
                           "\n > TAXA DE COLETA: " + this.taxaColeta +
                           "\n > CHANCE DE ROUBO: " + this.taxaRoubo + 
                           "\n > PEDRAS DO ROUBO: " + this.qntMaxRoubo / 2 + " ~ " + this.qntMaxRoubo +
                           "\n > DEFESA INICIAL: " + this.defesaInicial +
                           "\n > AMBIENTE: " + this.ambiente.getNome() + 
                           "\n > PASSIVA DE CUSTOM: Ao coletar, ganha +1 DEF até tentarem roubar-lhe.");
    }
}
