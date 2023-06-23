package macaquinhos;

import java.util.ArrayList;
import java.util.Scanner;

import macaquinhos.Macacos.*;

public class Macaquinhos {

    public static Scanner sc = new Scanner(System.in);
    public static final ArrayList<Macaco> listaMacacos = Macaco.getMacacos();
    public static void main(String[] args) {

        Jogo jogo = new Jogo(listaMacacos);
        boolean continuar = true;
        
        do {
            System.out.println("\n::::::::::::::: MACAQUINHOS :::::::::::::::");
            
            System.out.println("\n 1 - JOGAR\n" + 
                           " 2 - EDITAR\n" +
                           " 3 - SAIR");

            int opc = jogo.leituraInt("\n >>> SELECIONE ALGUMA OPÇÃO : ", 1, 3);

            switch(opc) {
                case 1:
                    jogo.jogar();
                    break;
                case 2:
                    jogo.editar();
                    break;
                case 3:
                    continuar = false;
                    break;
            }

        } while (continuar);

    }

}