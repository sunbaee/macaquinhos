package macaquinhos.Macacos;

import java.sql.ResultSet;
import java.util.ArrayList;
import macaquinhos.Acoes;
import macaquinhos.Ambientes.Ambiente;
import macaquinhos.Conexao;
import macaquinhos.Macacos.macacos_floresta.*;

public abstract class Macaco implements Acoes {
        
    protected String nome;
    protected int pedras; // 0 - 30
    protected int pedrasIniciais; // =pedras
    protected int tipo;

    protected int taxaRoubo;   // 0 - 60
    protected int taxaDefesa;  // 0 - 25
    protected int defesaInicial; // =taxaDefesa
    protected int taxaColeta;  // 5 - 20 pedrinhas
    protected int qntMaxRoubo; // 10 - 80 pedrinhas
    protected Ambiente ambiente;

    // CONSTRUTOR

    public Macaco(String nome) {
        this.nome = nome;
    }

    public Macaco(String nome, int pedras, int taxaColeta, int taxaRoubo, int qntMaxRoubo, int defesaInicial) {
        this.nome = nome;
        
        this.pedras = pedras;
        
        this.taxaRoubo = taxaRoubo;
        this.taxaColeta = taxaColeta;
        this.qntMaxRoubo = qntMaxRoubo;
        this.taxaDefesa = this.defesaInicial = defesaInicial;
    }

    // GETTERS E SETTERS

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }
    
    // MÉTODOS

    public static int random(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    @Override
    public int roubar(Macaco macaco) {

        int r = Macaco.random(0, 100);
        macaco.setTaxaDefesa(macaco.getDefesaInicial());

        if (r <= this.taxaRoubo && r >= macaco.getTaxaDefesa()) {

            int min = (int) (this.qntMaxRoubo / 2);

            int qtdPedrasRoubadas = Macaco.random(min, this.qntMaxRoubo);

            if (qtdPedrasRoubadas > macaco.getPedras()) qtdPedrasRoubadas = macaco.getPedras();

            this.pedras += qtdPedrasRoubadas;
            macaco.setPedras(macaco.getPedras() - qtdPedrasRoubadas);

            return qtdPedrasRoubadas;

        } else {
            return 0;
        }
    }

    @Override
    public int coletar() {
        int numPedras = Macaco.random(1, taxaColeta);
        this.pedras += numPedras;

        return numPedras;
    }

    @Override
    public int distrair(int pedrinhas) {

        this.pedras -= pedrinhas;
        
        int aumentoDefesa = pedrinhas / 5;
        this.taxaDefesa += aumentoDefesa;

        return aumentoDefesa;
    }

    public void mostrarDados() {
        System.out.println("\n   " + this.nome + "\n" +
                           "\n > PEDRAS INICIAIS: " + this.pedras + 
                           "\n > TAXA DE COLETA: " + this.taxaColeta +
                           "\n > CHANCE DE ROUBO: " + this.taxaRoubo + 
                           "\n > PEDRAS DO ROUBO: " + this.qntMaxRoubo / 2 + " ~ " + this.qntMaxRoubo +
                           "\n > DEFESA INICIAL: " + this.defesaInicial +
                           "\n > AMBIENTE: " + this.ambiente.getNome());
    }

    public void resetar() {
        this.taxaDefesa = this.defesaInicial;
        this.pedras = this.pedrasIniciais;
    }
    
    // CONEXAO BANCO DE DADOS
    
    public void cadastrar(){ 
      
        String sql =  "insert into Macaco (nome, pedras, taxaRoubo, qntMaxRoubo, taxaColeta, taxaDefesa, idAmbiente, tipo) values"
                + "('" + this.nome + "', "
                + this.pedras      + ", "
                + this.taxaRoubo   + ", "
                + this.qntMaxRoubo + ", "
                + this.taxaColeta  + ", "
                + this.taxaDefesa  + ", "
                + (this.ambiente.getDificuldade() + 1) + ", "
                + this.tipo + ")";

        //System.out.println(sql);
        Conexao.executar( sql );
        
    }
    
    public void editar(int id){
        String sql =  "update Macaco set "
                    + " nome        = '" + this.nome +   "' ,  "
                    + " pedras      = "  + this.pedras +  " ,  "
                    + " taxaRoubo   = "  + this.taxaRoubo +  " ,  "
                    + " qntMaxRoubo = "  + this.qntMaxRoubo +  " ,  "
                    + " taxaDefesa  = "  + this.taxaDefesa +  " ,  "
                    + " taxaColeta  = "  + this.taxaColeta +  " ,  "
                    + " idAmbiente  = "  + (this.ambiente.getDificuldade() + 1) + " ,  "
                    + " tipo = " + this.tipo + ""
                    + " where id = " + id;
        Conexao.executar( sql );
    }
    
    public static void excluir(int id){
        String sql =  "delete from macaco where id = " + id;
        Conexao.executar( sql );
    }

    public static ArrayList<Macaco> getMacacos(){
        ArrayList<Macaco> lista = new ArrayList<>();
        
        String sql = "select id, nome, pedras, taxaRoubo, qntMaxRoubo, taxaDefesa, taxaColeta, tipo"
                + " from macaco order by id ";
        
        ResultSet rs = Conexao.consultar( sql );
        
        if( rs != null){
            
            try{
                while ( rs.next() ) {                
                    String nome = rs.getString( 2 );
                    int pedras = rs.getInt( 3 );
                    int taxaRoubo = rs.getInt( 4 );
                    int qntMaxRoubo = rs.getInt( 5 );
                    int defesaInicial = rs.getInt( 6 );
                    int taxaColeta = rs.getInt( 7 );
                    int tipo = rs.getInt(8);
                    
                    Macaco macaco;
                    
                    switch(tipo) {
                        case 0: 
                            macaco = new Custom(nome, pedras, taxaColeta, taxaRoubo, qntMaxRoubo, defesaInicial);
                            break;
                        case 1:
                            macaco = new MacacoPrego(nome);
                            break;
                        case 2:
                            macaco = new MicoLeaoDourado(nome);
                            break;
                        case 3: 
                            macaco = new Orangotango(nome);
                            break;
                        default:
                            macaco = new Custom(nome, pedras, taxaColeta, taxaRoubo, qntMaxRoubo, defesaInicial);
                    }
   
                    lista.add( macaco );
                }
            }catch(Exception e){
                System.out.println("==>" + e);
            }
            
        }
  
        return lista;
    }
}
