package com.company;

public class PrediccionSector {
    int posibles;
    String sector = new String();
    int dia;
    String hora = new String();

    public PrediccionSector(int posibles, String sector, int dia, String hora) {
        this.posibles = posibles;
        this.sector = sector;
        this.dia = dia;
        this.hora = hora;
    }

    public PrediccionSector(){
        this.sector = "";
        this.dia = -1;
        this.hora = "";
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
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
