package EscalonadorRB;

public class Cpu implements Runnable{
    EscalonadorRB pc;
    public Cpu(EscalonadorRB _pc){
        this.pc=_pc;
    }
    public void run(){
        while(1+pc.finalizados.size()==pc.processos.size()){
            pc.execProcess();
        }
    }
}
