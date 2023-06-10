package macaquinhos;

public class Macaquinhos {

    public static void main(String[] args) {
        Macaco macaco = new Macaco("jose", 10, 25, 15, 30, 5);
        Macaco m2 = new Macaco("jose", 40, 20, 5, 50, 15);
        m2.Distrair(10);
        macaco.Roubar(m2);
        macaco.Roubar(m2);
    }
}