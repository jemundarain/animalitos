package com.company;

public class PrediccionColumna {
    int posibles;
    int columna;
    int dia;
    String hora= new String();

    public PrediccionColumna(int posibles, int columna, int dia, String hora) {
        this.posibles = posibles;
        this.columna = columna;
        this.dia = dia;
        this.hora = hora;
    }

    public PrediccionColumna(){
        this.columna = -1;
        this.dia = -1;
        this.hora = "";
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
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

    public int getPosibles(){
        return posibles;
    }

    public void setPosibles(int posibles){
        this.posibles = posibles;
    }
}
