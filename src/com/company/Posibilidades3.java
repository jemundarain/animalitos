package com.company;

public class Posibilidades3 {
    int animalXdiaXhora[][][] = new int[38][7][8];
    int colorXdiaXhora[][][] = new int[3][7][8];
    int grupoXdiaXhora[][][] = new int[6][7][8];
    int sectorXdiaXhora[][][] = new int[4][7][8];
    int columnaXdiaXhora[][][] = new int[4][7][8];
    int filaXdiaXhora[][][] = new int[13][7][8];

    public Posibilidades3(int[][][] animalXdiaXhora, int[][][] colorXdiaXhora, int[][][] grupoXdiaXhora, int[][][] sectorXdiaXhora, int[][][] columnaXdiaXhora) {
        this.animalXdiaXhora = animalXdiaXhora;
        this.colorXdiaXhora = colorXdiaXhora;
        this.grupoXdiaXhora = grupoXdiaXhora;
        this.sectorXdiaXhora = sectorXdiaXhora;
        this.columnaXdiaXhora = columnaXdiaXhora;
    }

    public Posibilidades3(){
        this.animalXdiaXhora = new int[38][7][8];
        this.colorXdiaXhora = new int[3][7][8];
        this.grupoXdiaXhora = new int[6][7][8];
        this.sectorXdiaXhora = new int[4][7][8];
        this.columnaXdiaXhora = new int[4][7][8];
    }

    public int[][][] getAnimalXdiaXhora() {
        return animalXdiaXhora;
    }

    public void setAnimalXdiaXhora(int[][][] animalXdiaXhora) {
        this.animalXdiaXhora = animalXdiaXhora;
    }

    public int[][][] getColorXdiaXhora() {
        return colorXdiaXhora;
    }

    public void setColorXdiaXhora(int[][][] colorXdiaXhora) {
        this.colorXdiaXhora = colorXdiaXhora;
    }

    public int[][][] getGrupoXdiaXhora() {
        return grupoXdiaXhora;
    }

    public void setGrupoXdiaXhora(int[][][] grupoXdiaXhora) {
        this.grupoXdiaXhora = grupoXdiaXhora;
    }

    public int[][][] getSectorXdiaXhora() {
        return sectorXdiaXhora;
    }

    public void setSectorXdiaXhora(int[][][] sectorXdiaXhora) {
        this.sectorXdiaXhora = sectorXdiaXhora;
    }

    public int[][][] getColumnaXdiaXhora() {
        return columnaXdiaXhora;
    }

    public void setColumnaXdiaXhora(int[][][] columnaXdiaXhora) {
        this.columnaXdiaXhora = columnaXdiaXhora;
    }
}
