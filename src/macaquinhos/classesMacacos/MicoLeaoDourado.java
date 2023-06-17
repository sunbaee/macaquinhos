package macaquinhos.classesMacacos;

public class MicoLeaoDourado extends Macaco {

    public MicoLeaoDourado(String nome) {
        super(nome);
        this.pedras = 5;
        this.taxaRoubo = 40;
        this.taxaColeta = 18;
        this.qntMaxRoubo = 60;
        this.taxaDefesa = this.defesaInicial = 12;
    }
    
}
