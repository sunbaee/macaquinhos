package macaquinhos.classesMacacos;

public class MacacoPrego extends Macaco {

    public MacacoPrego(String nome) {
        super(nome);
        this.pedras = 5;
        this.taxaRoubo = 50;
        this.taxaColeta = 12;
        this.qntMaxRoubo = 20;
        this.taxaDefesa = this.defesaInicial = 13;
    }
    
}
