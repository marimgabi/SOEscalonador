package EscalonadorRB;

public class Pronto implements Runnable {
    EscalonadorRB pc;
    public Pronto(EscalonadorRB pc) {
        this.pc = pc;
    }
    @Override
    public void run() {
        pc.movePronto();
    }
}
