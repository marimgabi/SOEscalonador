package EscalonadorRB;

import EscalonadorSJF.Processo;
import java.util.ArrayList;



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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getnProcessos() {
        return nProcessos;
    }

    public void setnProcessos(int nProcessos) {
        this.nProcessos = nProcessos;
    }

    public int getmTempoChegada() {
        return mTempoChegada;
    }

    public void setmTempoChegada(int mTempoChegada) {
        this.mTempoChegada = mTempoChegada;
    }

    public int getmExecucao() {
        return mExecucao;
    }

    public void setmExecucao(int mExecucao) {
        this.mExecucao = mExecucao;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public Processo getProcessExe() {
        return processExe;
    }

    public void setProcessExe(Processo processExe) {
        this.processExe = processExe;
    }

    public ArrayList<Processo> getProntos() {
        return prontos;
    }

    public void setProntos(ArrayList<Processo> prontos) {
        this.prontos = prontos;
    }

    public ArrayList<Processo> getFinalizados() {
        return finalizados;
    }

    public void setFinalizados(ArrayList<Processo> finalizados) {
        this.finalizados = finalizados;
    }

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
        this.processos=new ArrayList<>();
        this.prontos=new ArrayList<>();
        this.finalizados= new ArrayList<>();

    }
    public void movePronto(){
        for(Processo p:this.processos)
            if (this.time==p.getTempoChegada()){

                p.setTempoFalta(p.getTempoExec());
                this.prontos.add(p);
                System.out.println("-----------------entrou----------------:"+p.getId() + "T:" +this.time );
                //System.out.println(p.toString());
            }
    }
    public void exec(){
        if (this.prontos.size() > 0) {
            int n = this.prontos.size();
            for(int i=0;i<n;i++){
                Processo p = this.prontos.get(i);
                System.out.println("***********p"+p.getId()+"-F "+p.getTempoFalta());
                execProces(p);
                System.out.println();
                if((n<prontos.size())){
                    if(p!=this.prontos.get(i)){
                        i--;
                    }

                }else if (n>prontos.size()){
                    n=prontos.size();
                }

            }
        } else{
            this.update();
        }
    }

    public void execProces(Processo p){
        for (int i = 0; i < quantum; i++) {
            this.addWait(p);
            if (p.getTempoFalta() > quantum) {
                p.setTempoFalta(p.getTempoFalta() - 1);

                this.update();

            } else {
                if (p.getTempoFalta() == 0) {

                    this.moveFinal(p);
                    break;

                }
                if (p.getTempoFalta() == 1) {
                    p.setTempoFalta(p.getTempoFalta() - 1);

                    this.update();

                    this.moveFinal(p);

                    break;
                } else {
                    p.setTempoFalta(p.getTempoFalta() - 1);

                    this.update();

                }
            }
        }
    }

    public void moveFinal(Processo p){
        this.finalizados.add(p);
        this.prontos.remove(p);
        System.out.println("-----------------saiu----------------:"+p.getId() + "T:" +this.time );
    }

    public void addWait(Processo notThisOne){
        for(Processo p:prontos){
            if(notThisOne.getId()!=p.getId()){
                p.setTempoEspera(p.getTempoEspera()+1);
            }
        }
    }
    public void update(){

        this.movePronto();
        if((this.prontos.size()==0)&&(this.finalizados.size()<nProcessos)){
            System.out.print("&"+this.time+"&");
        }else if(this.prontos.size()>0){
            System.out.println(this.time);
        }
        this.time++;
    }

}
