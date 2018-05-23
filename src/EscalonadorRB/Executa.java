package EscalonadorRB;

public class Executa implements Runnable{
    EscalonadorRB pc;

    public Executa(EscalonadorRB pc) {
        this.pc = pc;
    }

    @Override
    public void run() {
        pc.processExec();
    }
}
