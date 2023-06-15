package macaquinhos;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import macaquinhos.classesMacacos.*;

public class Macaquinhos {

    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<Macaco> ListaMacacos = new ArrayList<Macaco>();
    public static void main(String[] args) {
        
        ListaMacacos.add(new Orangotango("Orangotango (default)"));
        ListaMacacos.add(new MicoLeaoDourado("Mico-Leão-Dourado (default)"));
        ListaMacacos.add(new MacacoPrego("Macaco Prego (default)"));

        boolean continuar = true;
        
        do {
            System.out.println("\n::::::::::::::: MACAQUINHOS :::::::::::::::");
            
            System.out.println("\n 1 - JOGAR\n" + 
                           " 2 - EDITAR\n" +
                           " 3 - SAIR");

            int opc = leituraInt("\n >>> SELECIONE ALGUMA OPÇÃO : ", 1, 3);

            switch(opc) {
                case 1:
                    jogar();
                    break;
                case 2:
                    editar();
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
                System.out.println("\n INSIRA UM VALOR VÁLIDO ... ");
                continue;
            }

            return num;

            } catch(InputMismatchException error) {
                System.out.println("\n INSIRA UM VALOR VÁLIDO ... ");
            }

        sc.nextLine();
        
        } while(true);
        
    }

    public static void mostrarListaMacacos() {
        for(int i = 0; i < ListaMacacos.size(); i++) {
            System.out.println(" " + (i + 1) + " - " + ListaMacacos.get(i).getNome());
        }
    }
    
    public static void jogar() {
        String confirmar;
        int escolha;
        
        do {
            System.out.println("\n :::::::::: SELECIONE SEU MACACO ::::::::::\n");

            for (int i = 0; i < ListaMacacos.size(); i++) {
                System.out.println(" " + (i + 1) + " - " + ListaMacacos.get(i).getNome());
            }

            escolha = leituraInt("\n >>> QUAL SUA ESCOLHA ? ", 1, ListaMacacos.size());

            System.out.print("\n CONFIRMAR? (S/N) : ");
            confirmar = sc.next();

            if(!confirmar.equalsIgnoreCase("S") && !confirmar.equalsIgnoreCase("N")) System.out.println("\nINSIRA UM VALOR VÁLIDO ... ");

            if(!confirmar.equalsIgnoreCase("S")) continue;

            break;

        } while(true);

    }

    public static void editar() {
        boolean continuar = true;

        do {
            System.out.println("\n ::::::::::::::::: EDIÇÃO :::::::::::::::::\n");

            System.out.println("\n 1 - ADICIONAR MACACO\n" + 
                            " 2 - REMOVER MACACO\n" +
                            " 3 - EDITAR MACACO\n" + 
                            " 4 - VER MACACOS\n" + 
                            " 5 - SAIR");

            int opc = leituraInt("\n >>> SELECIONE ALGUMA OPÇÃO : ", 1, 5);

            switch (opc) {
                case 1:
                    adicionarMacaco();
                    break;
                case 2:
                    removerMacaco();
                    break;
                case 3:
                    editarMacaco();
                    break;
                case 4:
                    mostrarMacacos();
                    break;
                case 5:
                    continuar = false;
                    break;
            }
        } while (continuar);
    }

    public static void mostrarMacacos() {
        System.out.println("\n ::::::::::::::: VER MACACOS :::::::::::::::\n");

        mostrarListaMacacos();

        int opc = leituraInt("\n >>> SELECIONE ALGUMA OPÇÃO : ", 1, ListaMacacos.size());
        ListaMacacos.get(opc - 1).mostrarDados();
        System.out.print("\n ESCREVA ALGO E APERTE ENTER PARA CONTINUAR ... ");
        sc.next();
        sc.nextLine();
    }

    public static void adicionarMacaco() {

        String nome, listaAtributos[] = {" > PEDRAS INICIAIS (0~30): ", " > TAXA DE COLETA (5~20): ", " > CHANCE DE ROUBO (0~50)(2x): ", " > PEDRAS DO ROUBO (10~80)(\u00F72): ", " > DEFESA INICIAL (0~25): "};
        int atributosMacaco[] = new int[5];

        do {
            System.out.println("\n :::::::::::::: ADICIONAR MACACO ::::::::::::::");
            System.out.print("\n NOME (SEM ESPAÇOS): ");
            nome = sc.next();
            sc.nextLine();

            if (nome.length() >= 30) {
                System.out.println("\n NOME DEVE TER MENOS QUE 30 CARACTERES.");
                continue;
            }

            break;

        } while (true);

        do {

            int sum = 0;
            System.out.println("\n (A SOMA DOS ATRIBUTOS NÃO DEVE ULTRAPASSAR 140) \n");

            atributosMacaco[0] = leituraInt(listaAtributos[0], 0, 30);
            atributosMacaco[1] = leituraInt(listaAtributos[1], 5, 20);
            atributosMacaco[2] = leituraInt(listaAtributos[2], 0, 50) * 2;
            atributosMacaco[3] = leituraInt(listaAtributos[3], 10, 80) / 2;
            atributosMacaco[4] = leituraInt(listaAtributos[4], 0, 25);

            for(int num : atributosMacaco) {
                sum += num;
            }

            if (sum > 140) {
                continue;
            }

            atributosMacaco[2] /= 2;
            atributosMacaco[3] *= 2;

            break;

        } while (true);

        ListaMacacos.add(new Macaco(nome, atributosMacaco[0], atributosMacaco[1], atributosMacaco[2], atributosMacaco[3], atributosMacaco[4]));
    }

    public static void removerMacaco() {
        if (ListaMacacos.size() <= 3) {
            System.out.println(" \n NÃO EXISTEM MACACOS PARA SEREM REMOVIDOS.");
            return;
        }

        System.out.println();

        mostrarListaMacacos();

        int rem = leituraInt("\n >>> SELECIONE O MACACO A SER REMOVIDO (EXCETO DEFAULT): ", 4, ListaMacacos.size());
        ListaMacacos.remove(rem - 1);

        System.out.println("\n MACACO REMOVIDO COM SUCESSO.");

    }

    public static void editarMacaco() {

        if (ListaMacacos.size() <= 3) {
            System.out.println(" \n NÃO EXISTEM MACACOS PARA SEREM EDITADOS.");
            return;
        }

        System.out.println();

        mostrarListaMacacos();

        int edit = leituraInt("\n INSIRA O MACACO A SER EDITADO (EXCETO DEFAULT): ", 4, ListaMacacos.size());

        String nome, listaAtributos[] = {" > PEDRAS INICIAIS (0~30): ", " > TAXA DE COLETA (5~20): ", " > CHANCE DE ROUBO (0~50)(2x): ", " > PEDRAS DO ROUBO (10~80)(\u00F72): ", " > DEFESA INICIAL (0~25): "};
        int atributosMacaco[] = new int[5];

        do {
            System.out.println("\n ::::::::::::::: EDITAR MACACO :::::::::::::::");
            System.out.print("\n NOME (SEM ESPAÇOS): ");
            nome = sc.next();
            sc.nextLine();

            if (nome.length() >= 30) {
                System.out.println("\n NOME DEVE TER MENOS QUE 30 CARACTERES.");
                continue;
            }

            break;

        } while (true);

        do {

            int sum = 0;
            System.out.println("\n (A SOMA DOS ATRIBUTOS NÃO DEVE ULTRAPASSAR 140) \n");

            atributosMacaco[0] = leituraInt(listaAtributos[0], 0, 30);
            atributosMacaco[1] = leituraInt(listaAtributos[1], 5, 20);
            atributosMacaco[2] = leituraInt(listaAtributos[2], 0, 50) * 2;
            atributosMacaco[3] = leituraInt(listaAtributos[3], 10, 80) / 2;
            atributosMacaco[4] = leituraInt(listaAtributos[4], 0, 25);

            for(int num : atributosMacaco) {
                sum += num;
            }

            if (sum > 140) {
                continue;
            }

            atributosMacaco[2] /= 2;
            atributosMacaco[3] *= 2;

            break;

        } while (true);

        ListaMacacos.set(edit - 1, new Macaco(nome, atributosMacaco[0], atributosMacaco[1], atributosMacaco[2], atributosMacaco[3], atributosMacaco[4]));
    }
}