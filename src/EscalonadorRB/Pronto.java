package EscalonadorRB;

public class Pronto implements Runnable{
    EscalonadorRB pc;
    public Pronto(EscalonadorRB _pc){
        this.pc=_pc;
    }
    public void run(){
        while (pc.prontos.get(pc.prontos.size()-1).getId()<pc.nProcessos){
            pc.movePronto();
        }
    }
}
