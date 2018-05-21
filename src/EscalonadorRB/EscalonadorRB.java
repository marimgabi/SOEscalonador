package EscalonadorRB;

import escalonadornp.Processo;
import java.util.ArrayList;
import java.util.Random;

public class EscalonadorRB {
    int time;
    int nProcessos;
    int mTempoChegada;
    int mExecucao;
    int quantum;
    Processo processExe;
    ArrayList<Processo> processos;
    ArrayList<Processo> prontos;
    ArrayList<Processo> finalizados;
    Random gerador = new Random();

    public EscalonadorRB(int time, int nProcessos, int mTempoChegada, int mExecucao,int quantum) {
        this.quantum=quantum;
        this.time = time;
        this.nProcessos = nProcessos;
        this.mTempoChegada = mTempoChegada;
        this.mExecucao = mExecucao;
        processos=new ArrayList<>();
        prontos=new ArrayList<>();
        finalizados= new ArrayList<>();
        for (int i=0; i<this.nProcessos; i++){
                    Processo p = new Processo();
                    p.setId(i);
                    p.setTempoExec((int) (gerador.nextInt((int) (((mExecucao*1.5) - (mExecucao*0.5)) + 1)) + (mExecucao*0.5)));
                    p.setTempoFalta(p.getTempoExec());
                    processos.add(p);
        }
    }
    public void movePronto(){
        for(Processo p:processos)
        if (time==p.getTempoChegada()){
            prontos.add(p);
        }
    }
    public void execProcess(int quantum){
        processExe.setTempoFalta(processExe.getTempoExec());
         for(int i=0;i<quantum;i++){
             if(processExe.getTempoFalta()>0) {
                 time++;
                 processExe.setTempoFalta(processExe.getTempoFalta() - 1);
             }
             if(processExe.getTempoFalta()==0){
                finalizados.add(processExe);
                prontos.remove(processExe);
                processExe=null;
                break;
             }
        }

    }
    public void espeProcess(){
        for(Processo p:this.prontos){
            p.setTempoEspera(p.getTempoEspera()+1);
            time++;
        }
    }
    public void moveExec(){
        for(Processo p:prontos){
            if(p.getTempoChegada()==time){
                if(processExe==null){
                    processExe=p;
                }
            }
        }
    }
    public void trocaProcess(){
        if(processExe.getId()==prontos.get(prontos.size()-1).getId()){
            processExe=prontos.get(0);
        }else{
            processExe=prontos.get(prontos.indexOf(processExe)+1);
        }
    }
}
