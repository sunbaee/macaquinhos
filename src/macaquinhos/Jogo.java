package macaquinhos;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import macaquinhos.classesMacacos.Macaco;

public class Jogo {

    ArrayList<Macaco> listaMacacos;

    public Jogo(ArrayList<Macaco> ListaMacacos) {
        this.listaMacacos = ListaMacacos;
    }

    public static Scanner sc = new Scanner(System.in);

    public int leituraInt(String msg, int min, int max) {
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

    public int leituraInt(String msg, int min) {
        do {
            try {

            System.out.print(msg);
            int num = sc.nextInt();

            if (num < min) {
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
    
    public void jogar() {
        String confirmar;
        int escolha;
        
        do {
            System.out.println("\n :::::: !!! QUE COMEÇEM OS JOGOS !!! :::::: ");
            System.out.println("\n > QUEM SERÁ O REI DOS MACACOS ?");
            System.out.println("\n :::::::::: SELECIONE SEU MACACO ::::::::::\n");

            for (int i = 0; i < this.listaMacacos.size(); i++) {
                System.out.println(" " + (i + 1) + " - " + this.listaMacacos.get(i).getNome());
            }

            escolha = this.leituraInt("\n >>> QUAL SUA ESCOLHA ? ", 1, this.listaMacacos.size());

            System.out.print("\n CONFIRMAR? (S/N) : ");
            confirmar = sc.next();

            if(!confirmar.equalsIgnoreCase("S") && !confirmar.equalsIgnoreCase("N")) System.out.println("\nINSIRA UM VALOR VÁLIDO ... ");

            if(!confirmar.equalsIgnoreCase("S")) continue;
            
            int numMacacos = this.leituraInt("\n >>> DESEJA JOGAR CONTRA QUANTOS MACACOS (MIN: 4) ? ", 4);            
            
            // DEFININDO MACACO USER

            Macaco macacosJogando[] = new Macaco[numMacacos + 1];
            macacosJogando[0] = listaMacacos.get(escolha - 1);

            // DEFININDO MACACOS INIMIGOS

            for(int i = 1; i < macacosJogando.length; i++) {
                macacosJogando[i] = listaMacacos.get(Macaco.random(0, listaMacacos.size()));
            }

            // MENU DE ATAQUE

            int rodadas = this.leituraInt("\n >>> DESEJA QUE O JOGO TENHA QUANTAS RODADAS (MIN: 4) ? ", 4);

            System.out.println("\n :::::::::::: !!! BOM JOGO !!! :::::::::::: ");

            for(int i = 0; i < rodadas; i++) {

                System.out.println("\n RODADA " + (i + 1));
                System.out.println("\n num pedras: " + macacosJogando[0].getPedras());
                int acao;

                do {
                    System.out.println("\n 1 - COLETAR" + 
                                       "\n 2 - ROUBAR"  +
                                       "\n 3 - DISTRAIR");

                    acao = this.leituraInt("\n >>> ESCOLHA SUA AÇÃO : ", 1, 3);

                    if(acao == 3 && macacosJogando[0].getPedras() < 5) {
                        System.out.println("\n VOCÊ NÃO POSSUI PEDRAS O SUFICIENTE PARA DISTRAIR OUTROS MACACOS.");
                        continue;
                    }

                    break;

                } while (true);

                switch (acao) {
                    case 1:

                        int coleta = macacosJogando[0].coletar();
                        System.out.println("\n ::::::::::::::: TELA DE AÇÕES ::::::::::::::: \n" +
                                           "\n " + macacosJogando[0].getNome() + " (Você) coletou " + coleta + " pedrinhas.");
                        break;

                    case 2:

                        System.out.println();

                        for(int j = 1; j < macacosJogando.length; j++) {
                            System.out.println(" " + j + " - " + macacosJogando[j].getNome() + "(" + j +")");
                        }

                        int macacoRoubado = this.leituraInt("\n >>> QUEM TENTARÁ ROUBAR ? ", 1, macacosJogando.length);
                        int roubo = macacosJogando[0].roubar(macacosJogando[macacoRoubado]);

                        System.out.println("\n ::::::::::::::: TELA DE AÇÕES ::::::::::::::: \n");
                        if (roubo == 0) {
                            System.out.println("\n Roubar é errado! Você não conseguiu roubar nenhuma pedrinha :(");
                        } else {
                            System.out.println("\n " + macacosJogando[0].getNome() + " (Você) roubou " + roubo + " pedrinhas de " + macacosJogando[macacoRoubado].getNome() + " (" + macacoRoubado + ").");
                        }

                        break;

                    case 3:

                        int pedrinhas;

                        do {
                            pedrinhas = leituraInt("\n INSIRA UM MÚLTIPLO DE 5 (MIN: 5, MAX: 50) : ", 5, 50);

                            if (pedrinhas % 5 != 0) {
                                System.out.println("\n INSIRA SOMENTE MÚLTIPLOS DE 5 .");
                                continue;
                            }
                            if (macacosJogando[0].getPedras() < pedrinhas) {
                                System.out.println("\n PEDRAS INSUFICIENTES.");
                                continue;
                            }
                            
                            break;
                        } while(true);

                        int aumentoDefesa = macacosJogando[0].distrair(pedrinhas);

                        System.out.println("\n ::::::::::::::: TELA DE AÇÕES ::::::::::::::: \n" +
                                           "\n " + macacosJogando[0].getNome() + " (Você) usou " + pedrinhas + " pedrinhas para distrair o próximo macaquinho que tentar roubá-lo! (+ " + aumentoDefesa +"DEF)");
                        break;

                }

            // ACAO DOS MACACOS INIMIGOS (BOTS)

                for(int j = 1; j < macacosJogando.length; j++) {

                    Macaco macaco = macacosJogando[j];
                    int acaoRandom = Macaco.random(0, 3);

                    if (macaco.getPedras() < 5) {
                        acaoRandom = Macaco.random(0, 2);
                    }
                    
                    switch (acaoRandom) {
                        case 0:

                            int numRandom = Macaco.random(0, macacosJogando.length);
                            Macaco macacoRandom = macacosJogando[numRandom];

                            int roubo = macaco.roubar(macacoRandom);

                            if (roubo == 0) {
                                if(numRandom == 0) {
                                    System.out.println(" " + macaco.getNome() + "(" + j + ") tentou roubar de você, mas falhou!");
                                } else {
                                    System.out.println(" " + macaco.getNome() + "(" + j + ") tentou roubar de " + macacoRandom.getNome() + "(" + numRandom + "), mas falhou!");
                                }
                            } else if (numRandom == 0) {
                                    System.out.println(" " + macaco.getNome() + "(" + j + ") roubou " + roubo + " pedrinhas de você! Boa sorte recuperando as pedrinhas :P");
                            } else {
                                    System.out.println(" " + macaco.getNome() + "(" + j + ") roubou " + roubo + " pedrinhas de " + macacoRandom.getNome() + "(" + numRandom + ").");
                            }   
                            
                            break;

                        case 1:

                            int coleta = macaco.coletar();
                            System.out.println(" " + macaco.getNome() + "(" + j + ") coletou " + coleta + " pedrinhas.");
                            break;

                        case 2:

                            int pedrinhas = Macaco.random(1, (int) ((macaco.getPedras() + 5) / 5)) * 5;
                            
                            int aumentoDefesa = macaco.distrair(pedrinhas);
                            System.out.println(" " + macaco.getNome() + "(" + j + ") usou " + pedrinhas + " pedrinhas para distrair o próximo macaquinho que tentar roubá-lo! (+ " + aumentoDefesa + "DEF).");
                            break;
                    }
                }
            }

            // DEFININDO MACACO REI

            Macaco rei = null;
            int max = -1;
            int id = 0;

            for(int i = 0; i < macacosJogando.length; i++) {
                if (macacosJogando[i].getPedras() > max) {
                    max = macacosJogando[i].getPedras();
                    rei = macacosJogando[i];
                    id = i;
                }
            }

            System.out.println("\n ::::::::: !!! A RAINHA DECIDIU !!! ::::::::: ");
            if (id != 0) {
                System.out.println("\n O REI DOS MACACOS É " + rei.getNome() + "(" + id + ") !!! ");
                break;
                }

            System.out.println("\n VOCÊ É O REI DOS MACACOS !!! ");
            break;

        } while(true);
    }

    public void editar() {
        boolean continuar = true;

        do {
            System.out.println("\n ::::::::::::::::: EDIÇÃO :::::::::::::::::\n");

            System.out.println("\n 1 - ADICIONAR MACACO\n" + 
                            " 2 - REMOVER MACACO\n" +
                            " 3 - EDITAR MACACO\n" + 
                            " 4 - VER MACACOS\n" + 
                            " 5 - SAIR");

            int opc = this.leituraInt("\n >>> SELECIONE ALGUMA OPÇÃO : ", 1, 5);

            switch (opc) {
                case 1:
                    this.adicionarMacaco();
                    break;
                case 2:
                    this.removerMacaco();
                    break;
                case 3:
                    this.editarMacaco();
                    break;
                case 4:
                    this.mostrarMacacos();
                    break;
                case 5:
                    continuar = false;
                    break;
            }
        } while (continuar);
    }

    public void mostrarMacacos() {
        System.out.println("\n ::::::::::::::: VER MACACOS :::::::::::::::\n");

        this.mostrarListaMacacos();

        int opc = this.leituraInt("\n >>> SELECIONE ALGUMA OPÇÃO : ", 1, this.listaMacacos.size());
        this.listaMacacos.get(opc - 1).mostrarDados();
        System.out.print("\n ESCREVA ALGO E APERTE ENTER PARA CONTINUAR ... ");
        sc.next();
        sc.nextLine();
    }

    public void mostrarListaMacacos() {
        for(int i = 0; i < this.listaMacacos.size(); i++) {
            System.out.println(" " + (i + 1) + " - " + this.listaMacacos.get(i).getNome());
        }
    }

    public void adicionarMacaco() {

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

            atributosMacaco[0] = this.leituraInt(listaAtributos[0], 0, 30);
            atributosMacaco[1] = this.leituraInt(listaAtributos[1], 5, 20);
            atributosMacaco[2] = this.leituraInt(listaAtributos[2], 0, 50) * 2;
            atributosMacaco[3] = this.leituraInt(listaAtributos[3], 10, 80) / 2;
            atributosMacaco[4] = this.leituraInt(listaAtributos[4], 0, 25);

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

        System.out.println("\n MACACO ADICIONADO COM SUCESSO.");
        this.listaMacacos.add(new Macaco(nome, atributosMacaco[0], atributosMacaco[1], atributosMacaco[2], atributosMacaco[3], atributosMacaco[4]));
    }

    public void removerMacaco() {
        if (this.listaMacacos.size() <= 3) {
            System.out.println(" \n NÃO EXISTEM MACACOS PARA SEREM REMOVIDOS.");
            return;
        }

        System.out.println();

        this.mostrarListaMacacos();

        int rem = this.leituraInt("\n >>> SELECIONE O MACACO A SER REMOVIDO (EXCETO MACACOS PADRÕES): ", 4, this.listaMacacos.size());
        this.listaMacacos.remove(rem - 1);

        System.out.println("\n MACACO REMOVIDO COM SUCESSO.");

    }

    public void editarMacaco() {

        if (this.listaMacacos.size() <= 3) {
            System.out.println(" \n NÃO EXISTEM MACACOS PARA SEREM EDITADOS.");
            return;
        }

        System.out.println();

        this.mostrarListaMacacos();

        int edit = this.leituraInt("\n INSIRA O MACACO A SER EDITADO (EXCETO MACACOS PADRÕES): ", 4, this.listaMacacos.size());

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

            atributosMacaco[0] = this.leituraInt(listaAtributos[0], 0, 30);
            atributosMacaco[1] = this.leituraInt(listaAtributos[1], 5, 20);
            atributosMacaco[2] = this.leituraInt(listaAtributos[2], 0, 50) * 2;
            atributosMacaco[3] = this.leituraInt(listaAtributos[3], 10, 80) / 2;
            atributosMacaco[4] = this.leituraInt(listaAtributos[4], 0, 25);

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

        System.out.println("\n MACACO EDITADO COM SUCESSO.");
        this.listaMacacos.set(edit - 1, new Macaco(nome, atributosMacaco[0], atributosMacaco[1], atributosMacaco[2], atributosMacaco[3], atributosMacaco[4]));
    }
}
