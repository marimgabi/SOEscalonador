//
//* To change this license header, choose License Headers in Project Properties.
//* To change this template file, choose Tools | Templates
//* and open the template in the editor.
//
package escalonadornp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*

BUGS:
    

 */

public class main {

    static Scanner entrada = new Scanner(System.in);
    //LISTAS DE PROCESSOS
    static List<Processo> processos = new ArrayList<>();
    static List<Processo> prontos = new ArrayList<>();
    static List<Processo> finalizados = new ArrayList<>();
    
    boolean cpu;
    static int idExecutando, quantum=0;
    
    //FUNÇÃO QUE RETORNA ID DO PROCESSO COM MENOR tempoExec
    public static int escalona(){
        int id=0, menor;
        menor=prontos.get(0).getTempoExec();
        for(Processo a:prontos){
            if(a.getTempoExec()<menor){
                id=a.getId();
            }
        }
        return id;
    }


    public static int retiraProcessos(){
        for(Processo b: prontos){
            if(b.getTempoFalta()==0) {
                return prontos.indexOf(b);
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        
        int n, mediaChegada, mediaExec, maiorExec=0, tempoTotal=0;

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
                tempoTotal+=a.getTempoExec();
            }
        }
        //SETA OS TEMPOS DE CHEGADA ALEATORIAMENTE
        for(Processo a: processos){
            a.setTempoChegada((int) (gerador.nextInt((int) ((tempoTotal-maiorExec) + 1))));
        }

        for (Processo a:processos){
            System.out.println(a.toString());
        }
        
        while(quantum<=tempoTotal){

            System.out.println("QUANTUM = ");
            //ADICIONA PROCESSOS NA FILA DE PRONTOS;
            for(Processo a:processos){
                if(a.getTempoChegada()==quantum){
                    prontos.add(a);
                    //RETORNA ID COM MENOR TEMPO EXEC
                    idExecutando=escalona();
                }
            }

            for(Processo a:processos){
                if(a.getId()==idExecutando){
                    a.setTempoFalta(a.getTempoFalta()-1);
                }
            }
//
//            //TIRA OS PROCESSOS FINALIZADOS DA LISTA DE PRONTOS E PÕE NA DE FINALIZADOS
//            while(retiraProcessos()!=-1){
//                finalizados.add(prontos.get(retiraProcessos()));
//                prontos.remove(retiraProcessos());
//            }

            quantum++;
            
        }

//        System.out.println("FILA DE PRONTOS DO SATANÁS");
//        for(Processo a:prontos){
//            System.out.println(a.toString());
//        }

//        System.out.println("TERMINADO ESSA BUDEGA");
//        for(Processo a: processos){
//            System.out.println(a.toString());
//        }
    }
    
}
