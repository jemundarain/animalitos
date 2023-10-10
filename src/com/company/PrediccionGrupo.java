package com.company;

public class PrediccionGrupo {

    public int posibles;
    public String grupo = new String();
    public int dia;
    public String hora = new String();

    public PrediccionGrupo(int posibles, String grupo, int dia, String hora) {
        this.posibles = posibles;
        this.grupo = grupo;
        this.dia = dia;
        this.hora = hora;
    }

    public PrediccionGrupo(){
        this.posibles = -1;
        this.grupo = "";
        this.dia = -1;
        this.hora = "";
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getPosibles() {
        return posibles;
    }

    public void setPosibles(int posibles) {
        this.posibles = posibles;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
