/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escalonadornp;

/**
 *
 * @author gabriela
 */
public class Processo {
    int id;
    int tempoTotal;
    int tempoExec;
    int tempoEspera;
    int tempoChegada;
    int tempoFalta;

    public Processo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTempoTotal() {
        return tempoTotal;
    }

    public void setTempoTotal(int tempoTotal) {
        this.tempoTotal = tempoTotal;
    }

    public int getTempoExec() {
        return tempoExec;
    }

    public void setTempoExec(int tempoExec) {
        this.tempoExec = tempoExec;
    }

    public int getTempoEspera() {
        return tempoEspera;
    }

    public void setTempoEspera(int tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public int getTempoChegada() {
        return tempoChegada;
    }

    public void setTempoChegada(int tempoChegada) {
        this.tempoChegada = tempoChegada;
    }

    public int getTempoFalta() {
        return tempoFalta;
    }

    public void setTempoFalta(int tempoFalta) {
        this.tempoFalta = tempoFalta;
    }

    @Override
    public String toString() {
        return "Processo:\n" +
                "\nid=" + id +
                "\ntempoTotal=" + tempoTotal + 
                "\n tempoExec=" + tempoExec + 
                "\n tempoEspera=" + tempoEspera + 
                "\n tempoChegada=" + tempoChegada +
                "\n tempoFalta=" + tempoFalta + 
                "\n ----------------------------------------\n";
    }
    
    
    
}
