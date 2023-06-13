package macaquinhos;

import java.util.InputMismatchException;
import java.util.Scanner;

import macaquinhos.classesMacacos.*;

public class Macaquinhos {

    static Scanner sc = new Scanner(System.in);
    static String arrayListaMacacos[] = {"ORANGOTANGO", "MICO-LEÃO-DOURADO", "MACACO PREGO"};
    public static void main(String[] args) {

        boolean continuar = true;
        
        do {
            System.out.println("\n::::::::::::::: MACAQUINHOS :::::::::::::::");
            
            System.out.println("\n 1 - JOGAR\n" + 
                           " 2 - VER MACACOS\n" +
                           " 3 - SAIR");

            int opc = leituraInt("\n >>> SELECIONE ALGUMA OPÇÃO : ", 1, 3);

            switch(opc) {
                case 1:
                    jogar();
                    break;
                case 2:
                    mostrarMacacos();
                    break;
                case 3:
                    continuar = false;
                    break;
            }

        } while (continuar);
    
        }

    public static int leituraInt(String msg, int min, int max) {
        do {
            try {

            System.out.print(msg);
            int num = sc.nextInt();

            if (num < min || num > max) {
                System.out.println("\nINSIRA UM VALOR VÁLIDO ... ");
                continue;
            }

            return num;

            } catch(InputMismatchException error) {
                System.out.println("\nINSIRA UM VALOR VÁLIDO ... ");
            }

        sc.nextLine();
        
        } while(true);
        
    }

    public static void jogar() {
        String confirmar;
        int escolha;
        
        do {
            System.out.println("\n::::::::::: CRIE SEU PERSONAGEM :::::::::::\n");

            for (int i = 0; i < arrayListaMacacos.length; i++) {
                System.out.println(" " + (i + 1) + " - " + arrayListaMacacos[i]);
            }

            escolha = leituraInt("\n>>> QUAL SUA ESCOLHA ? ", 1, arrayListaMacacos.length);

            System.out.print("\nCONFIRMAR? (S/N) : ");
            confirmar = sc.next();

            if(!confirmar.equalsIgnoreCase("S") && !confirmar.equalsIgnoreCase("N")) System.out.println("\nINSIRA UM VALOR VÁLIDO ... ");

            if(!confirmar.equalsIgnoreCase("S")) continue;

            break;

        } while(true);
        
        Macaco arrayMacacos[] = {};
    }

    public static void mostrarMacacos() {
        System.out.println();

        for(int i = 0; i < arrayListaMacacos.length; i++) {
            System.out.println(" " + (i + 1) + " - " + arrayListaMacacos[i]);
        }

        Macaco arrayMostrarMacacos[] = {
            new MacacoPrego(arrayListaMacacos[0]), 
            new Orangotango(arrayListaMacacos[1]), 
            new MicoLeaoDourado(arrayListaMacacos[2])
        };

        int opc = leituraInt("\n >>> SELECIONE ALGUMA OPÇÃO : ", 1, arrayListaMacacos.length);
        arrayMostrarMacacos[opc - 1].mostrarDados();
    }
}