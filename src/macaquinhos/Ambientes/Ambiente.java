package macaquinhos.Ambientes;

public abstract class Ambiente {

    public static final Ambiente ambientes[] = {new Floresta("Floresta"), new Savana("Savana"), new Pantano("Pântano")};
    protected String nome;
    protected int dificuldade;

    public Ambiente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

}
