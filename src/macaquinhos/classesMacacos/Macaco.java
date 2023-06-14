package macaquinhos.classesMacacos;

import macaquinhos.Acoes;

public class Macaco implements Acoes {

    protected String nome;
    protected int pedras; // 0 - 30

    protected int taxaRoubo;   // 0 - 50
    protected int taxaDefesa;  // 0 - 25
    protected int taxaColeta;  // 5 - 20 pedrinhas
    protected int qntMaxRoubo; // 10 - 80 pedrinhas
    protected int defesaInicial; // =taxaDefesa

    // CONSTRUTOR

    public Macaco(String nome) {
        this.nome = nome;
    }

    // GETTERS E SETTERS

    public int getPedras() {
        return pedras;
    }

    public void setPedras(int pedras) {
        this.pedras = pedras;
    }

    public int getTaxaDefesa() {
        return taxaDefesa;
    }

    public void setTaxaDefesa(int taxaDefesa) {
        this.taxaDefesa = taxaDefesa;
    }

    public int getDefesaInicial() {
        return defesaInicial;
    }

    public void setDefesaInicial(int defesaInicial) {
        this.defesaInicial = defesaInicial;
    }

    // MÉTODOS

    public int random(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    public void roubar(Macaco macaco) {

        int r = this.random(0, 100);

        if (r <= this.taxaRoubo && r >= macaco.getTaxaDefesa()) {

            int min = this.qntMaxRoubo % 2 == 0 ? this.qntMaxRoubo / 2 : (int) (this.qntMaxRoubo / 2);

            int qtdPedrasRoubadas = random(min, this.qntMaxRoubo);

            if (qtdPedrasRoubadas > macaco.getPedras()) qtdPedrasRoubadas = macaco.getPedras();

            this.pedras += qtdPedrasRoubadas;
            macaco.setPedras(macaco.getPedras() - qtdPedrasRoubadas);

            System.out.println("Você roubou com sucesso " + qtdPedrasRoubadas + " pedrinhas.");

        } else {
            System.out.println("Roubar é errado! Você não conseguiu roubar nenhuma pedrinha :(");
        }

        macaco.setTaxaDefesa(macaco.getDefesaInicial());
    }

    public void coletar() {
        int numPedras = random(1, taxaColeta);
        this.pedras += numPedras;

        System.out.println("Parabéns! Você coletou " + numPedras + " pedrinhas.");
    }

    public void distrair(int pedrinhas) {

        this.pedras -= pedrinhas;   
        this.taxaDefesa += pedrinhas / 5;

        System.out.println("Você usou " + pedrinhas + " pedrinhas para distrair o próximo macaquinho que tentar te roubar!");
    }

    public void mostrarDados() {
        System.out.println("\n   " + this.nome + "\n" +
                           "\n > PEDRAS INICIAIS: " + this.pedras + 
                           "\n > TAXA DE COLETA: " + this.taxaColeta +
                           "\n > CHANCE DE ROUBO: " + this.taxaRoubo + 
                           "\n > PEDRAS DO ROUBO: " + this.qntMaxRoubo / 2 + " ~ " + this.qntMaxRoubo +
                           "\n > DEFESA INICIAL: " + this.defesaInicial);
    }
}
