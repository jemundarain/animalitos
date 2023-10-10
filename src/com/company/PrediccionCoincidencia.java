package com.company;

public class PrediccionCoincidencia {
    int posibles;
    int dia;
    String hora = new String();

    public PrediccionCoincidencia(int posibles, int dia, String hora){
        this.posibles = posibles;
        this.dia = dia;
        this.hora = hora;
    }

    public int getPosibles() {
        return posibles;
    }

    public void setPosibles(int posibles) {
        this.posibles = posibles;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
