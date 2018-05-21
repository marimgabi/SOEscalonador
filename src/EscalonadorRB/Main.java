package EscalonadorRB;

import java.util.Scanner;

public class Main {
    static Scanner entrada = new Scanner(System.in);
    public static void main(String[] args) {

        int n, mediaChegada, mediaExec, maiorExec=0, tempoTotal=0;

        System.out.println("Informe a quantidade de processos: ");
        n=entrada.nextInt();

        System.out.println("Informe o tempo médio de chegada: ");
        mediaChegada=entrada.nextInt();

        System.out.println("Informe o tempo médio de execução: ");
        mediaExec=entrada.nextInt();


    }
}
