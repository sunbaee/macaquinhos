package macaquinhos.classesMacacos;

public class MacacoPrego extends Macaco {

    public MacacoPrego(String nome) {
        super(nome);
        this.pedras = 8;
        this.taxaRoubo = 25;
        this.taxaColeta = 12;
        this.qntMaxRoubo = 70;
        this.taxaDefesa = this.defesaInicial = 10;
    }
    
}
