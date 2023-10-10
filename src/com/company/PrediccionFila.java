package com.company;

public class PrediccionFila {
    int posibles;
    int fila;
    int dia;
    String hora = new String();

    public PrediccionFila (){
        this.posibles = -1;
        this.fila = -1;
        this.dia = -1;
        this.hora = "";
    }

    public PrediccionFila(int posibles, int fila, int dia, String hora){
        this.posibles = posibles;
        this.fila = fila;
        this.dia = dia;
        this.hora = hora;
    }

    public int getPosibles() {
        return posibles;
    }

    public void setPosibles(int posibles) {
        this.posibles = posibles;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
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

