package macaquinhos.classesMacacos;

public class Orangotango extends Macaco {

    public Orangotango(String nome) {
        super(nome);
        this.pedras = 35;
        this.taxaRoubo = 18;
        this.taxaColeta = 10;
        this.qntMaxRoubo = 80;
        this.taxaDefesa = this.defesaInicial = 20;
    }
    
}
