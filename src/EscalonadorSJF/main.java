/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EscalonadorSJF;

//import com.sun.tracing.dtrace.DependencyClass;
import EscalonadorRB.EscalonadorRB;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class main {

    static Scanner entrada = new Scanner(System.in);
    //LISTAS DE PROCESSOS
    static List<Processo> processos = new ArrayList<>();
    static List<Processo> prontos = new ArrayList<>();
    static List<Processo> finalizados = new ArrayList<>();

    static int idExecutando=-1, quantum=0, vazio=0;

    //FUNÇÃO QUE RETORNA ID DO PROCESSO COM MENOR tempoExec
    public static int escalona(int maiorExec){
        int id=-1, menor;
        if(prontos.isEmpty()){
            return -1;
        }else{
            menor=maiorExec+1;
            for(Processo a:prontos){
                if(a.getTempoFalta()<menor&&a.getTempoFalta()>0){
                    menor=a.getTempoFalta();
                    id=a.getId();
                }
            }
            return id;
        }

    }

    public static void main(String[] args) {

        int n, mediaChegada, mediaExec, maiorExec=0, tempoTotal=0;

        System.out.println("================--SJF--==================");

        System.out.println("Informe a quantidade de processos: ");
        n=entrada.nextInt();

        System.out.println("Informe o tempo médio de chegada: ");
        mediaChegada=entrada.nextInt();

        System.out.println("Informe o tempo médio de execução: ");
        mediaExec=entrada.nextInt();

        Random gerador = new Random();

        //CRIA OS PROCESSOS E SETA O TEMPO DE EXEC ALEATÓRIAMENTE
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
            }
            tempoTotal+=a.getTempoExec();
        }
        //SETA OS TEMPOS DE CHEGADA ALEATORIAMENTE
        for(Processo a: processos){
            a.setTempoChegada( (gerador.nextInt((tempoTotal - maiorExec) + 1)));
        }

        for (Processo a:processos){
            System.out.println(a.toString());
        }

        //System.out.println("Tempo total exec= "+tempoTotal+"\n");


        while(finalizados.size()<n){

            //System.out.println("QUANTUM = "+quantum+"\n");

            //ADICIONA PROCESSOS NA FILA DE PRONTOS;
            for(Processo a:processos){
                if(a.getTempoChegada()==quantum){
                    prontos.add(a);
                    //System.out.println("chegou processo "+a.getId()+"\n");
                    //RETORNA ID COM MENOR TEMPO EXEC
                    idExecutando=escalona(maiorExec);
                }
            }

            if(vazio==1){
                idExecutando=escalona(maiorExec);
                vazio--;
            }


            //System.out.println("Processo vencedor "+idExecutando+"\n");

            if(idExecutando>=0){
                for(Processo a:prontos){
                    if(a.getId()==idExecutando){
                        //System.out.println("T1 "+a.getTempoFalta()+"\n");
                        a.setTempoFalta(a.getTempoFalta()-1);
                        //System.out.println("T2 "+a.getTempoFalta()+"\n");
                        //System.out.println("Decrementou em "+a.getId()+"\n");
                        if(a.getTempoFalta()==0){
                            a.setTempoTotal(1+quantum-a.getTempoChegada());
                            a.setTempoEspera(1+quantum-(a.getTempoChegada()+a.getTempoExec()));
                            finalizados.add(a);
                            vazio++;
                        }
                    }
                }
            }


            quantum++;

        }

        double mediaFinalExec=0, mediaFinalEspera=0, mediaFinalTotal=0;

//        System.out.println("FILA DE PRONTOS");
//        for(Processo a:prontos){
//            System.out.println(a.toString());
//        }

        System.out.println("FILA DE FINALIZADOS");
        for(Processo a:finalizados){
            System.out.println(a.toString());
            mediaFinalExec+=a.getTempoExec();
            mediaFinalEspera+=a.getTempoEspera();
            mediaFinalTotal+=a.getTempoTotal();
        }

//        System.out.println("FILA DE PROCESSOS ORIGINAL");
//        for(Processo a: processos){
//            System.out.println(a.toString());
//        }

        System.out.println("===============MÉDIAS===============");
        System.out.println("Tempo médio de execução: " +mediaFinalExec/n+"\n");
        System.out.println("Tempo médio de espera: "+mediaFinalEspera/n+"\n");
        System.out.println("Tempo médio total de execução: "+mediaFinalTotal/n+"\n");


        System.out.println("\n\n\n\n================--SRB--==================");

        System.out.println("Informe o tempo tamanho de quantun (unidades de tempo genérico): ");
        quantum =entrada.nextInt();

        EscalonadorRB Pc = new EscalonadorRB(n,mediaChegada,maiorExec,quantum);
        Pc.setProcessos((ArrayList<Processo>) processos);
        System.out.println("Nº P:"+Pc.getnProcessos());

        while ((Pc.getProntos().size()!=0)||(Pc.getFinalizados().size()!=Pc.getnProcessos())){
            //Pc.movePronto();
            Pc.exec();
            if(Pc.getTime()>tempoTotal*10){

                break;
            }

        }
        System.out.println();
        System.out.println("P:"+Pc.getProntos().size());
        System.out.println("F:"+Pc.getFinalizados().size());

        System.out.print("\n");
        float mediaTo=0,mediaEx=0,mediaEs=0;
        for (Processo p:Pc.getFinalizados()){
            p.setTempoTotal(p.getTempoExec()+p.getTempoEspera());
            System.out.println(p.toString());
            mediaTo+=p.getTempoTotal();
            mediaEx+=p.getTempoExec();
            mediaEs+=p.getTempoEspera();
        }
        maiorExec= mediaExec/n;
        mediaEs=mediaEs/n;
        mediaTo=mediaTo/n;
        System.out.println("==============Médias da Execução=============");
        System.out.println("Media de Execução:"+mediaEx);
        System.out.println("Media de Espera:"+mediaEs);
        System.out.println("Media Total:"+mediaTo);
    }

}