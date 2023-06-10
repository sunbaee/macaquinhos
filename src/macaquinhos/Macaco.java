package macaquinhos;

public class Macaco implements Acoes {

    protected String nome;
    private int pedras;

    private int taxaRoubo;   // 0 50
    private int taxaDefesa;  // 0 25
    private int taxaColeta;  // 0 - 10
    private int qntMaxRoubo; 
    private int defesaInicial;

    public Macaco(String nome, int pedras, int taxaRoubo, int taxaColeta, int qntMaxRoubo, int defesaInicial) {
        this.nome = nome;
        this.pedras = pedras;
        this.taxaRoubo = taxaRoubo;
        this.taxaColeta = taxaColeta;
        this.qntMaxRoubo = qntMaxRoubo;
        this.taxaDefesa = this.defesaInicial = defesaInicial;
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

    public int Random(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    public void Roubar(Macaco macaco) {

        int r = this.Random(0, 100);

        if (r <= this.taxaRoubo && r >= macaco.getTaxaDefesa()) {

            int min = this.qntMaxRoubo % 2 == 0 ? this.qntMaxRoubo / 2 : (int) (this.qntMaxRoubo / 2);

            int qtdPedrasRoubadas = Random(min, this.qntMaxRoubo);

            if (qtdPedrasRoubadas > macaco.getPedras()) qtdPedrasRoubadas = macaco.getPedras();

            this.pedras += qtdPedrasRoubadas;
            macaco.setPedras(macaco.getPedras() - qtdPedrasRoubadas);

            System.out.println("Você roubou com sucesso " + qtdPedrasRoubadas + " pedrinhas.");

        } else {
            System.out.println("Roubar é errado! Você não conseguiu roubar nenhuma pedrinha :(");
        }

        macaco.setTaxaDefesa(macaco.getDefesaInicial());
    }

    public void Coletar() {
        int numPedras = Random(1, taxaColeta);
        this.pedras += numPedras;

        System.out.println("Parabéns! Você coletou " + numPedras + " pedrinhas.");
    }

    public void Distrair(int pedrinhas) {
        this.pedras -= pedrinhas;   
        this.taxaDefesa += pedrinhas;

        System.out.println("Você usou " + pedrinhas + " pedrinhas para distrair o próximo macaquinho que tentar te roubar!");
    }

}
