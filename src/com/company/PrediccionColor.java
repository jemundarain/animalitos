package com.company;

public class PrediccionColor {

    public int posibles;
    public String color = new String();
    public int dia;
    public String hora = new String();

    public PrediccionColor(int posibles, String color, int dia, String hora) {
        this.posibles = posibles;
        this.color = color;
        this.dia = dia;
        this.hora = hora;
    }

    public PrediccionColor(){
        this.posibles=-1;
        this.color = "";
        this.dia = -1;
        this.hora = "";
    }
    public String getColor() {
        return color;
    }

    public void setAnimal(String color) {
        this.color = color;
    }

    public int getDia() {
        return dia;
    }

    public int getPosibles() {
        return posibles;
    }

    public void setPosibles(int posibles) {
        this.posibles = posibles;
    }

    public void setColor(String color) {
        this.color = color;
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
