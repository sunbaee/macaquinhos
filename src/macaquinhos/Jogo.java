package macaquinhos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import macaquinhos.Macacos.*;
import macaquinhos.Macacos.macacos_floresta.MacacoPrego;
import macaquinhos.Macacos.macacos_floresta.MicoLeaoDourado;
import macaquinhos.Macacos.macacos_floresta.Orangotango;

public class Jogo {

    ArrayList<Macaco> listaMacacos;
    ArrayList<Macaco> macacosAtivos;

    public Jogo(ArrayList<Macaco> listaMacacos) {
        this.listaMacacos = listaMacacos;
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

        macacosAtivos = new ArrayList<>();

        System.out.println("\n 1 - FLORESTA (FÁCIL)" +
                           "\n 2 - SAVANA   (MÉDIO)" + 
                           "\n 3 - PÂNTANO  (DIFÍCIL)");
        
        int habitat = this.leituraInt("\n >>> SELECIONE UM HABITAT (0 PARA SAIR): ", 0, 3);
        if (habitat == 0) return;

        for(int i = 0; i < this.listaMacacos.size(); i++) {
            int dificuldade = this.listaMacacos.get(i).getAmbiente().getDificuldade();

            if (dificuldade == (habitat) || dificuldade == 0) {
                this.macacosAtivos.add(listaMacacos.get(i));
            }
        }

        String confirmar;
        int escolha;
        
        do {
            System.out.println("\n :::::: !!! QUE COMEÇEM OS JOGOS !!! :::::: ");
            System.out.println("\n > QUEM SERÁ O REI DOS MACACOS ?");
            System.out.println("\n :::::::::: SELECIONE SEU MACACO ::::::::::\n");

            for (int i = 0; i < this.macacosAtivos.size(); i++) {
                System.out.println(" " + (i + 1) + " - " + this.macacosAtivos.get(i).getNome());
            }

            escolha = this.leituraInt("\n >>> QUAL SUA ESCOLHA ? (0 PARA SAIR) ", 0, this.macacosAtivos.size());
            if (escolha == 0) return;

            System.out.print("\n CONFIRMAR? (S/N) : ");
            confirmar = sc.next();
            sc.nextLine();

            if(!confirmar.equalsIgnoreCase("S") && !confirmar.equalsIgnoreCase("N")) System.out.println("\nINSIRA UM VALOR VÁLIDO ... ");

            if(!confirmar.equalsIgnoreCase("S")) continue;
            
            int numMacacos = this.leituraInt("\n >>> DESEJA JOGAR CONTRA QUANTOS MACACOS (MIN: 4) ? ", 4);

            if(numMacacos > macacosAtivos.size() - 1) {
                System.out.println("\n !!! QUANTIDADE DE MACACOS INSUFICIENTES.");
                System.out.print("\n ESCREVA ALGO E APERTE ENTER PARA CONTINUAR ... ");
                sc.next();
                sc.nextLine();
                break;
            }

            // DEFININDO MACACO USER

            Macaco macacosJogando[] = new Macaco[numMacacos + 1];
            macacosJogando[0] = macacosAtivos.get(escolha - 1);

            // DEFININDO MACACOS INIMIGOS (nenhum objeto macaco se repetirá)

            int numMacacoIRandom; // numero aleatorio do macaco inimigo

            for(int i = 1; i < macacosJogando.length; i++) {
                numMacacoIRandom = Macaco.random(0, macacosAtivos.size());

                while (Arrays.asList(macacosJogando).contains(macacosAtivos.get(numMacacoIRandom))) {
                    numMacacoIRandom = Macaco.random(0, macacosAtivos.size());
                }

                macacosJogando[i] = macacosAtivos.get(numMacacoIRandom);
            }

            // MENU DE ATAQUE

            int rodadas = this.leituraInt("\n >>> DESEJA QUE O JOGO TENHA QUANTAS RODADAS (MIN: 4) ? ", 4);

            System.out.println("\n :::::::::::: !!! BOM JOGO !!! :::::::::::: ");

            for(int i = 0; i < rodadas; i++) {

                System.out.println("\n RODADA " + (i + 1));
                System.out.println("\n SUAS PEDRAS:  " + macacosJogando[0].getPedras());
                System.out.println(" DEFESA ATUAL: " + macacosJogando[0].getTaxaDefesa());

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
                            
                            while (numRandom == j) {
                                numRandom = Macaco.random(0, macacosJogando.length); 
                            }

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
                System.out.println("\n ::::::::::::::: ::::::::::::::: ::::::::::::::: ");

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
                resetarMacacos(macacosJogando);

                break;
            }

            System.out.println("\n VOCÊ É O REI DOS MACACOS !!! ");
            resetarMacacos(macacosJogando);

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

        int opc = this.leituraInt("\n >>> SELECIONE ALGUMA OPÇÃO (0 PARA SAIR): ", 0, this.listaMacacos.size());
        if (opc == 0) return;

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

        String nome, listaAtributos[] = {" > PEDRAS INICIAIS (0~30): ", " > TAXA DE COLETA (5~20): ", " > CHANCE DE ROUBO (0~60)(2x): ", " > PEDRAS DO ROUBO (10~120)(\u00F72): ", " > DEFESA INICIAL (0~25): "};
        int atributosMacaco[] = new int[5];

        System.out.println("\n :::::::::::::: ADICIONAR MACACO ::::::::::::::");
            
        System.out.println("\n 1 - ADICIONAR MACACO CUSTOMIZADO " + 
                           "\n 2 - ADICIONAR MACACO PADRÃO " +
                           "\n 3 - SAIR ");

        int opc = leituraInt("\n >>> QUAL SUA ESCOLHA ? ", 1, 3);
        Macaco macacoAdicionado;

        switch (opc) {
            case 1:
                do {
                System.out.println("\n ::::::::::::::::::: CUSTOM :::::::::::::::::::");
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
                    System.out.println("\n (A SOMA DOS ATRIBUTOS NÃO DEVE ULTRAPASSAR 160) \n");

                    atributosMacaco[0] = this.leituraInt(listaAtributos[0], 0, 30);
                    sum += atributosMacaco[0];
                    System.out.println("\n TOTAL : " + sum);
                    System.out.println();
                    atributosMacaco[1] = this.leituraInt(listaAtributos[1], 5, 20);
                    sum += atributosMacaco[1];
                    System.out.println("\n TOTAL : " + sum);
                    System.out.println();
                    atributosMacaco[2] = this.leituraInt(listaAtributos[2], 0, 60) * 2;
                    sum += atributosMacaco[2];
                    System.out.println("\n TOTAL : " + sum);
                    System.out.println();
                    atributosMacaco[3] = this.leituraInt(listaAtributos[3], 10, 120) / 2;
                    sum += atributosMacaco[3];
                    System.out.println("\n TOTAL : " + sum);
                    System.out.println();
                    atributosMacaco[4] = this.leituraInt(listaAtributos[4], 0, 25);
                    sum += atributosMacaco[4];
                    System.out.println("\n TOTAL : " + sum);

                    if (sum > 160) {
                        continue;
                    }

                    atributosMacaco[2] /= 2;
                    atributosMacaco[3] *= 2;

                    break;

                } while (true);

                System.out.println("\n ! MACACO ADICIONADO COM SUCESSO !");
                macacoAdicionado = new Custom(nome, atributosMacaco[0], atributosMacaco[1], atributosMacaco[2], atributosMacaco[3], atributosMacaco[4]);
                this.listaMacacos.add(macacoAdicionado);
                macacoAdicionado.cadastrar();
                break;
            case 2:
                System.out.println("\n :::::::::::::::::: DEFAULT ::::::::::::::::::");
                System.out.println("\n 1 - ORANGOTANGO " + 
                                "\n 2 - MACACO-PREGO " + 
                                "\n 3 - MICO-LEÃO-DOURADO ");

                int eMacaco = leituraInt("\n >>> QUAL SUA ESCOLHA ? ", 1, 3);

                do {
                    System.out.print("\n NOME (SEM ESPAÇOS): ");
                    nome = sc.next();
                    sc.nextLine();

                    if (nome.length() >= 30) {
                        System.out.println("\n NOME DEVE TER MENOS QUE 30 CARACTERES.");
                        continue;
                    }

                    break;

                } while (true);
                
                switch (eMacaco) {
                    case 1:
                        macacoAdicionado = new Orangotango(nome);
                        this.listaMacacos.add(macacoAdicionado);
                        macacoAdicionado.cadastrar();
                        break;
                    case 2:
                        macacoAdicionado = new MacacoPrego(nome);
                        this.listaMacacos.add(macacoAdicionado);
                        macacoAdicionado.cadastrar();
                        break;
                    case 3:
                        macacoAdicionado = new MicoLeaoDourado(nome);
                        this.listaMacacos.add(macacoAdicionado);
                        macacoAdicionado.cadastrar();
                        break;
                }
                
                System.out.println("\n ! MACACO ADICIONADO COM SUCESSO !");
                break;
            case 3:
        } 
    }

    public void removerMacaco() {
        if (this.listaMacacos.size() <= 3) {
            System.out.println(" \n ! NÃO EXISTEM MACACOS PARA SEREM REMOVIDOS !");
            return;
        }

        System.out.println();

        this.mostrarListaMacacos();
        int rem;

        do {
            rem = this.leituraInt("\n > INSIRA O MACACO A SER REMOVIDO (EXCETO MACACOS PADRÕES | 0 PARA SAIR): ", 0, this.listaMacacos.size());
            if (rem == 0) return;

            if (rem >= 1 && rem <= 3) {
                System.out.println("\n NÃO É POSSÍVEL REMOVER MACACOS PADRÕES DO SISTEMA.");
                continue;
            }

            break;
        } while (true);

        System.out.println("\n ! MACACO REMOVIDO COM SUCESSO !");
        this.listaMacacos.remove(rem - 1);
        Macaco.excluir(rem);
    }

    public void editarMacaco() {

        if (this.listaMacacos.size() <= 3) {
            System.out.println(" \n ! NÃO EXISTEM MACACOS PARA SEREM EDITADOS !");
            return;
        }

        System.out.println();

        this.mostrarListaMacacos();
        int edit;

        do {
            edit = this.leituraInt("\n > INSIRA O MACACO A SER EDITADO (EXCETO MACACOS PADRÕES | 0 PARA SAIR): ", 0, this.listaMacacos.size());
            if (edit == 0) return;

            if (edit >= 1 && edit <= 3) {
                System.out.println("\n NÃO É POSSÍVEL EDITAR MACACOS PADRÕES DO SISTEMA.");
                continue;
            }

            break;
        } while (true);
            
        String nome, listaAtributos[] = {" > PEDRAS INICIAIS (0~30): ", " > TAXA DE COLETA (5~20): ", " > CHANCE DE ROUBO (0~60)(2x): ", " > PEDRAS DO ROUBO (10~120)(\u00F72): ", " > DEFESA INICIAL (0~25): "};
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
            System.out.println("\n (A SOMA DOS ATRIBUTOS NÃO DEVE ULTRAPASSAR 160) \n");

            atributosMacaco[0] = this.leituraInt(listaAtributos[0], 0, 30);
            sum += atributosMacaco[0];
            System.out.println("\n TOTAL : " + sum);
            System.out.println();
            atributosMacaco[1] = this.leituraInt(listaAtributos[1], 5, 20);
            sum += atributosMacaco[1];
            System.out.println("\n TOTAL : " + sum);
            System.out.println();
            atributosMacaco[2] = this.leituraInt(listaAtributos[2], 0, 60) * 2;
            sum += atributosMacaco[2];
            System.out.println("\n TOTAL : " + sum);
            System.out.println();
            atributosMacaco[3] = this.leituraInt(listaAtributos[3], 10, 120) / 2;
            sum += atributosMacaco[3];
            System.out.println("\n TOTAL : " + sum);
            System.out.println();
            atributosMacaco[4] = this.leituraInt(listaAtributos[4], 0, 25);
            sum += atributosMacaco[4];
            System.out.println("\n TOTAL : " + sum);
            System.out.println();

            if (sum > 160) {
                continue;
            }

            atributosMacaco[2] /= 2;
            atributosMacaco[3] *= 2;

            break;

        } while (true);

        System.out.println("\n ! MACACO EDITADO COM SUCESSO !");
        Macaco macacoEditado = new Custom(nome, atributosMacaco[0], atributosMacaco[1], atributosMacaco[2], atributosMacaco[3], atributosMacaco[4]);
        this.listaMacacos.set(edit - 1, macacoEditado);
        macacoEditado.editar(edit);
    }

    public void resetarMacacos(Macaco macacosJogando[]) {
        for(Macaco m : macacosJogando) {
                m.resetar();
            }
    }
}
