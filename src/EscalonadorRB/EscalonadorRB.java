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

//                System.out.println(p.toString());
            }
    }
    public void processExec(){
        if (this.prontos.size() > 0) {

            int n = this.prontos.size();
            for(int relative=0;relative<n;relative++){

                Processo p = this.prontos.get(relative);


                System.out.print("p"+p.getId()+"-");
                for (int i = 0; i < quantum; i++) {
                    if (p.getTempoFalta() > quantum) {
                        p.setTempoFalta(p.getTempoFalta() - 1);
                        this.time++;

                        this.movePronto();


                        n = this.prontos.size();

                        System.out.print(this.time);
                    } else {
                        if (p.getTempoFalta() == 1) {


                            p.setTempoFalta(p.getTempoFalta() - 1);
                            System.out.print(this.time);
                            this.time++;
                            this.moveFinal(p);
                            this.movePronto();
                            n=this.prontos.size();
                            break;
                        } else {
                            p.setTempoFalta(p.getTempoFalta() - 1);
                            System.out.print(this.time);
                            this.time++;
                            this.movePronto();
                            n = this.prontos.size();
                        }
                    }
                }
            }
        } else {
            this.time++;
            this.movePronto();
            System.out.print("-"+this.time+"-");

        }


    }
    public void moveFinal(Processo p){
        this.finalizados.add(p);

        this.prontos.remove(p);

    }

}
