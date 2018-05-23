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

    public ArrayList<Processo> getProcessos() {
        return processos;
    }

    public void setProcessos(ArrayList<Processo> processos) {
        this.processos = processos;
    }

    public EscalonadorRB(int nProcessos, int mTempoChegada, int mExecucao, int quantum) {
        this.quantum=quantum;
        this.time = 0;
        this.nProcessos = nProcessos;
        this.mTempoChegada = mTempoChegada;
        this.mExecucao = mExecucao;
        int maiorExec=0;
        int tempoTotal=0;
        processos=new ArrayList<>();
        prontos=new ArrayList<>();
        finalizados= new ArrayList<>();
    }
    public void movePronto(){
        for(Processo p:processos)
            if (time==p.getTempoChegada()){
                prontos.add(p);
            }
    }
    public void execProcess(){
        processExe.setTempoFalta(processExe.getTempoExec());
        System.out.print(processExe.getId());
         for(int i=0;i<this.quantum;i++){
             if(processExe.getTempoFalta()>0) {
                 time++;
                 processExe.setTempoFalta(processExe.getTempoFalta() - 1);
                 espeProcess();
                 System.out.print("|");
             }
             if(processExe.getTempoFalta()==0){
                finalizados.add(processExe);
                prontos.remove(processExe);
                trocaProcess();
                break;
             }

        }
    }
    public void espeProcess(){
        for(Processo p:this.prontos){
            if(processExe!=null) {
                if (p.getId() != processExe.getId()) {
                    p.setTempoEspera(p.getTempoEspera() + 1);
                }
            }
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
    public void lazy(){
        while (processExe==null) {
            if(prontos.size()>0){

            }
            moveExec();
            espeProcess();
            time++;
        }
    }
    public void trocaProcess(){
        if((prontos.size()>0)) {
            if (processExe.getId() == prontos.get(prontos.size() - 1).getId()){
                processExe = prontos.get(0);
            } else {
                processExe = prontos.get(prontos.indexOf(processExe) + 1);
            }
        }else {
            processExe=null;
        }
    }
}
