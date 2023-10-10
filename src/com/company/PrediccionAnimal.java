package com.company;

public class PrediccionAnimal {
    public int posibles;
    public Integer animal;
    public int dia;
    public String hora = new String();

    public PrediccionAnimal(int posibles, int animal, int dia, String hora) {
        this.posibles = posibles;
        this.animal = animal;
        this.dia = dia;
        this.hora = hora;
    }

    public PrediccionAnimal(){
        this.posibles = -1;
        this.animal = -1;
        this.dia = -1;
        this.hora = "";

    }

    public int getAnimal() {
        return animal;
    }

    public void setAnimal(int animal) {
        this.animal = animal;
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

    public int getPosibles() {
        return posibles;
    }

    public void setPosibles(int posibles) {
        this.posibles = posibles;
    }
}
