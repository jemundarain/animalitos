package com.company;

public class PrediccionRepeticion {
    public int posibles;
    public int animal;
    public int dia;

    public PrediccionRepeticion(int posibles, int animal, int dia){
        this.posibles = posibles;
        this.animal = animal;
        this.dia = dia;
    }
    public PrediccionRepeticion(){
        this.posibles = -1;
        this.animal = -1;
        this.dia = -1;
    }

    public int getPosibles() {
        return posibles;
    }

    public void setPosibles(int posibles) {
        this.posibles = posibles;
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
}
