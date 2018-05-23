package EscalonadorRB;

import escalonadornp.Processo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;

public class Main {
    static Scanner entrada = new Scanner(System.in);
    public static void main(String[] args) {

        int n, mediaChegada, mediaExec, maiorExec=0, tempoTotal=0;

        System.out.println("Informe a quantidade de processos: ");
        n=entrada.nextInt();

        System.out.println("Informe o tempo médio de chegada: ");
        mediaChegada=entrada.nextInt();

        System.out.println("Informe o tempo médio de execução: ");
        mediaExec=entrada.nextInt();

        System.out.println("Informe o tempo tamanho de quantun (unidades de tempo genérico): ");
        int quantun =entrada.nextInt();

        ArrayList<Processo> processos = new ArrayList<>();
        Random gerador = new Random();
        for (int i=0; i<n; i++){
            Processo p = new Processo();
            p.setId(i);
            p.setTempoExec((int) (gerador.nextInt((int) (((mediaExec*1.5) - (mediaExec*0.5)) + 1)) + (mediaExec*0.5)));
            p.setTempoFalta(p.getTempoExec());
            processos.add(p);
        }
        //ENCONTRA O MAIOR TEMPO DE EXECUÇÃO E O TEMPO TOTAL DE EXEC
        for(Processo a: processos){
            if (a.getTempoExec()>maiorExec){
                maiorExec=a.getTempoExec();
                tempoTotal+=a.getTempoExec();
            }
        }
        //SETA OS TEMPOS DE CHEGADA ALEATORIAMENTE
        for(Processo a: processos){
            a.setTempoChegada( (gerador.nextInt( ((tempoTotal-maiorExec) + 1))));
        }
//        for (Processo a:processos){
//            System.out.println(a.toString());
//        }

        EscalonadorRB pc = new EscalonadorRB(n,mediaChegada,maiorExec,quantun);
        pc.setProcessos(processos);
        System.out.println("Nº P:"+pc.getnProcessos());

        while ((pc.getProntos().size()!=0)||(pc.getFinalizados().size()!=pc.getnProcessos())){
            pc.movePronto();
            pc.exec();
//            if(pc.getTime()>tempoTotal*10){
//
//                break;
//            }

        }
        System.out.println();
        System.out.println("P:"+pc.prontos.size());
        System.out.println("F:"+pc.finalizados.size());

        System.out.print("\n");
//        for (Processo p:pc.getFinalizados()){
//            System.out.println(p.toString());
//        }
    }
}
