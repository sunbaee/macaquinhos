package macaquinhos.classesMacacos;

public class Orangotango extends Macaco {

    public Orangotango(String nome) {
        super(nome);
        this.pedras = 14;
        this.taxaRoubo = 18;
        this.taxaColeta = 15;
        this.qntMaxRoubo = 120;
        this.taxaDefesa = this.defesaInicial = 15;
    }
    
}
