package com.company;
import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static final String DEFAULT_DRIVER_CLASS = "org.postgresql.Driver";
    public static final String DEFAULT_URL = "jdbc:postgresql://localhost:5432/Animalitos";
    private static final String DEFAULT_USERNAME = "postgres";
    private static final String DEFAULT_PASSWORD = "whovian22";


    public static void main(String[] args) {
        Connection connection = null;
        Integer i, j, k;
        String horas[] = {"'10'", "'11'", "'12'", "'1'", "'4'", "'5'", "'6'", "'7'"};
        String colores[] = {"'N'", "'R'", "'V'"};
        String grupos[] = {"'A'", "'B'","'C'", "'D'", "'E'", "'F'"};
        String sectores[] = {"'BAJO'", "'MEDIO'", "'ALTO'", "'NEUTRO'"};
        Posibilidades3 posibilidades3 = new Posibilidades3();
        Posibilidades2 posibilidades2 = new Posibilidades2();
        Posibilidades1 posibilidades1= new Posibilidades1();
        ArrayList<PrediccionAnimal> listaPrediccionAnimal = new ArrayList<PrediccionAnimal>(10);
        ArrayList<PrediccionColor> listaPrediccionColor = new ArrayList<PrediccionColor>(10);
        ArrayList<PrediccionGrupo> listaPrediccionGrupo = new ArrayList<PrediccionGrupo>(10);
        ArrayList<PrediccionSector> listaPrediccionSector = new ArrayList<PrediccionSector>(10);
        ArrayList<PrediccionColumna> listaPrediccionColumna = new ArrayList<PrediccionColumna>(10);
        ArrayList<PrediccionFila> listaPrediccionFila = new ArrayList<PrediccionFila>(10);
        ArrayList<PrediccionRepeticion> listaPrediccionRepeticion = new ArrayList<PrediccionRepeticion>(10);
        ArrayList<PrediccionCoincidencia> listaPrediccionCoincidencia = new ArrayList<PrediccionCoincidencia>(10);

        Statement pstm = null;
        ResultSet rs = null;
        int pMayor=0, veces=0;


        try {

            connection = Main.getConnection(DEFAULT_DRIVER_CLASS, DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);

            //*************************************ANIMAL X DIA******************************
            //Almacenar ANIMALxdia = 266
            String SQLi  = "SELECT COUNT(num) AS numero FROM Sorteo WHERE num= all-animals AND date_part('dow', fecha)= all-days ;";
            for(i=0 ; i<38 ; i++){
                for(j=0 ; j<7 ; j++){
                        String SQLf = ((SQLi.replace("all-animals", i.toString())).replace("all-days", j.toString()));
                        pstm = connection.createStatement();
                        rs = pstm.executeQuery(SQLf);
                        while(rs.next()){
                            posibilidades2.animalXdia[i][j] = rs.getInt("numero");
                            if(posibilidades2.animalXdia[i][j] > pMayor) {
                                pMayor = posibilidades2.animalXdia[i][j];
                            }
                        }
                }
            }
    /*
            //Buscar mayores en ANIMALxdia
            for(i=0 ; i<38 ; i++){
                for(j=0 ; j<7 ; j++){
                        if(posibilidades2.animalXdia[i][j] == pMayor || posibilidades2.animalXdia[i][j] == (pMayor-1)){
                            PrediccionAnimal prediccionAnimal = new PrediccionAnimal(posibilidades2.animalXdia[i][j],i,j,"");
                            listaPrediccionAnimal.add(prediccionAnimal);
                        }
                }
            }

            //*************************************ANIMAL X HORA******************************
            //Almacenar ANIMALxHORA = 304
            pMayor=0;
            SQLi  = "SELECT COUNT(num) AS numero FROM Sorteo WHERE num= all-animals AND hora= all-hours;";
            for(i=0 ; i<38 ; i++){
                for(k=0 ; k<8 ; k++){
                    String SQLf = ((SQLi.replace("all-animals", i.toString())).replace("all-hours", horas[k]));
                    rs = pstm.executeQuery(SQLf);
                    while(rs.next()){
                        posibilidades2.animalXhora[i][k] = rs.getInt("numero");
                        if(posibilidades2.animalXhora[i][k] > pMayor) {
                            pMayor = posibilidades2.animalXhora[i][k];
                        }
                    }
                }
            }

            //Buscar mayores en ANIMALxhora
            for(i=0 ; i<38 ; i++){
                for(k=0 ; k<8 ; k++){
                    if(posibilidades2.animalXhora[i][k] == pMayor || posibilidades2.animalXhora[i][k] == (pMayor-1)){
                        PrediccionAnimal prediccionAnimal = new PrediccionAnimal(posibilidades2.animalXhora[i][k],i,-1,horas[k]);
                        listaPrediccionAnimal.add(prediccionAnimal);
                    }
                }
            }


            //*************************************ANIMAL X DIA X HORA ******************************
            //Almacenar ANIMALxdiaxhora = 2072
            pMayor=0;
            SQLi  = "SELECT COUNT(num) AS numero FROM Sorteo WHERE num= all-animals AND date_part('dow', fecha)= all-days AND hora= all-hours;";
            for(i=0 ; i<38 ; i++){
                for(j=0 ; j<7 ; j++){
                    for(k=0 ; k<8; k++){
                        String SQLf = (((SQLi.replace("all-animals", i.toString())).replace("all-days", j.toString())).replace("all-hours", horas[k]));
                        rs = pstm.executeQuery(SQLf);
                        while(rs.next()){
                            posibilidades3.animalXdiaXhora[i][j][k] = rs.getInt("numero");
                            if(posibilidades3.animalXdiaXhora[i][j][k] > pMayor) {
                                pMayor = posibilidades3.animalXdiaXhora[i][j][k];
                            }
                        }
                    }
                }
            }

            //Buscar mayores en ANIMALxdiaxhora
            for(i=0 ; i<38 ; i++){
                for(j=0 ; j<7 ; j++){
                    for(k=0 ; k<8 ; k++){
                        if(posibilidades3.animalXdiaXhora[i][j][k] == pMayor || posibilidades3.animalXdiaXhora[i][j][k] == (pMayor-1)){
                            PrediccionAnimal prediccionAnimal = new PrediccionAnimal(posibilidades3.animalXdiaXhora[i][j][k],i,j,horas[k]);
                            listaPrediccionAnimal.add(prediccionAnimal);
                        }
                    }
                }
            }



            //*************************************COLOR X DIA ******************************
            //Almacenar COLORxdia
            SQLi  = "SELECT COUNT(diaS.num) AS numero FROM Animal A, (SELECT num FROM Sorteo WHERE date_part('dow', fecha)= all-days ) diaS WHERE diaS.num=A.num AND A.color= all-colors ;";
            pMayor = 0;
            for(i=0 ; i<3 ; i++){
                for(j=0 ; j<7 ; j++){
                        String SQLf = ((SQLi.replace("all-colors", colores[i])).replace("all-days", j.toString()));
                        rs = pstm.executeQuery(SQLf);
                        while(rs.next()){
                            posibilidades2.colorXdia[i][j] = rs.getInt("numero");
                            if(posibilidades2.colorXdia[i][j] > pMayor) {
                                pMayor = posibilidades2.colorXdia[i][j];
                            }
                        }
                }
            }

            //Buscar mayores en COLORxdia
            for(i=0 ; i<3 ; i++){
                for(j=0 ; j<7 ; j++){
                        if(posibilidades2.colorXdia[i][j] == pMayor || posibilidades2.colorXdia[i][j] == (pMayor-1)){
                            PrediccionColor prediccionColor = new PrediccionColor(posibilidades2.colorXdia[i][j], colores[i], j, "");
                            listaPrediccionColor.add(prediccionColor);
                        }

                }
            }

            //*************************************COLOR X HORA******************************
            //Almacenar COLORxhora
            SQLi  = "SELECT COUNT(diaS.num) AS numero FROM Animal A, (SELECT num FROM Sorteo WHERE hora= all-hours ) diaS WHERE diaS.num=A.num AND A.color= all-colors ;";
            pMayor = 0;
            for(i=0 ; i<3 ; i++){
                    for(k=0 ; k<8; k++){
                        String SQLf = (SQLi.replace("all-colors", colores[i])).replace("all-hours", horas[k]);
                        rs = pstm.executeQuery(SQLf);
                        while(rs.next()){
                            posibilidades2.colorXhora[i][k] = rs.getInt("numero");
                            if(posibilidades2.colorXhora[i][k] > pMayor) {
                                pMayor = posibilidades2.colorXhora[i][k];
                            }
                        }
                    }
            }

            //Buscar mayores en COLORxhora
            for(i=0 ; i<3 ; i++) {
                for (k = 0; k < 8; k++) {
                    if (posibilidades2.colorXhora[i][k] == pMayor || posibilidades2.colorXhora[i][k] == (pMayor - 1)) {
                        PrediccionColor prediccionColor = new PrediccionColor(posibilidades2.colorXhora[i][k], colores[i], -1, horas[k]);
                        listaPrediccionColor.add(prediccionColor);
                    }

                }
            }

            //*************************************COLOR X DIA X HORA******************************
            //Almacenar COLORxdiaxhora
            SQLi  = "SELECT COUNT(diaS.num) AS numero FROM Animal A, (SELECT num FROM Sorteo WHERE date_part('dow', fecha)= all-days AND hora= all-hours ) diaS WHERE diaS.num=A.num AND A.color= all-colors ;";
            pMayor = 0;
            for(i=0 ; i<3 ; i++){
                for(j=0 ; j<7 ; j++){
                    for(k=0 ; k<8; k++){
                        String SQLf = (((SQLi.replace("all-colors", colores[i])).replace("all-days", j.toString())).replace("all-hours", horas[k]));
                        rs = pstm.executeQuery(SQLf);
                        while(rs.next()){
                            posibilidades3.colorXdiaXhora[i][j][k] = rs.getInt("numero");

                            if(posibilidades3.colorXdiaXhora[i][j][k] > pMayor) {
                                pMayor = posibilidades3.colorXdiaXhora[i][j][k];
                            }
                        }
                    }
                }
            }

            //Buscar mayores en COLORxdiaxhora
            for(i=0 ; i<3 ; i++){
                for(j=0 ; j<7 ; j++){
                    for(k=0 ; k<8 ; k++){
                        if(posibilidades3.colorXdiaXhora[i][j][k] == pMayor || posibilidades3.colorXdiaXhora[i][j][k] == (pMayor-1)){
                            PrediccionColor prediccionColor = new PrediccionColor(posibilidades3.colorXdiaXhora[i][j][k], colores[i], j, horas[k]);
                            listaPrediccionColor.add(prediccionColor);
                        }

                    }
                }
            }

            //*************************************GRUPO X DIA******************************
            //Almacenar GRUPOxdia
            SQLi  = "SELECT COUNT(diaS.num) AS numero FROM Animal A, (SELECT num  FROM Sorteo WHERE date_part('dow', fecha)= all-days ) diaS WHERE diaS.num=A.num AND A.grupo= all-groups ;";
            pMayor = 0;
            for(i=0 ; i<6 ; i++){
                for(j=0 ; j<7 ; j++){
                        String SQLf = ((SQLi.replace("all-groups", grupos[i])).replace("all-days", j.toString()));
                        rs = pstm.executeQuery(SQLf);
                        while(rs.next()){
                            posibilidades2.grupoXdia[i][j] = rs.getInt("numero");
                            if(posibilidades2.grupoXdia[i][j] > pMayor) {
                                pMayor = posibilidades2.grupoXdia[i][j];
                            }
                        }
                }
            }

            //Buscar mayores en GRUPOxdia
            for(i=0 ; i<6 ; i++){
                for(j=0 ; j<7 ; j++){
                        if(posibilidades2.grupoXdia[i][j] == pMayor || posibilidades2.grupoXdia[i][j] == (pMayor-1)){
                            PrediccionGrupo prediccionGrupo = new PrediccionGrupo(posibilidades2.grupoXdia[i][j], grupos[i], j, "");
                            listaPrediccionGrupo.add(prediccionGrupo);
                        }
                }
            }

            //*************************************GRUPO X HORA******************************
            //Almacenar GRUPOxhora
            SQLi  = "SELECT COUNT(diaS.num) AS numero FROM Animal A, (SELECT num  FROM Sorteo WHERE hora= all-hours ) diaS WHERE diaS.num=A.num AND A.grupo= all-groups ;";
            pMayor = 0;
            for(i=0 ; i<6 ; i++){
                    for(k=0 ; k<8; k++){
                        String SQLf = (SQLi.replace("all-groups", grupos[i])).replace("all-hours", horas[k]);
                        rs = pstm.executeQuery(SQLf);
                        while(rs.next()){
                            posibilidades2.grupoXhora[i][k] = rs.getInt("numero");
                            if(posibilidades2.grupoXhora[i][k] > pMayor) {
                                pMayor = posibilidades2.grupoXhora[i][k];
                            }
                        }
                    }
            }

            //Buscar mayores en GRUPOxhora
            for(i=0 ; i<6 ; i++){
                    for(k=0 ; k<8 ; k++){
                        if(posibilidades2.grupoXhora[i][k] == pMayor || posibilidades2.grupoXhora[i][k] == (pMayor-1)){
                            PrediccionGrupo prediccionGrupo = new PrediccionGrupo(posibilidades2.grupoXhora[i][k], grupos[i],-1, horas[k]);
                            listaPrediccionGrupo.add(prediccionGrupo);
                        }
                    }
            }
            //*************************************GRUPO X DIA X HORA******************************
            //Almacenar GRUPOxdiaxhora
            SQLi  = "SELECT COUNT(diaS.num) AS numero FROM Animal A, (SELECT num  FROM Sorteo WHERE date_part('dow', fecha)= all-days AND hora= all-hours ) diaS WHERE diaS.num=A.num AND A.grupo= all-groups ;";
            pMayor = 0;
            for(i=0 ; i<6 ; i++){
                for(j=0 ; j<7 ; j++){
                    for(k=0 ; k<8; k++){
                        String SQLf = (((SQLi.replace("all-groups", grupos[i])).replace("all-days", j.toString())).replace("all-hours", horas[k]));
                        rs = pstm.executeQuery(SQLf);
                        while(rs.next()){
                            posibilidades3.grupoXdiaXhora[i][j][k] = rs.getInt("numero");
                            if(posibilidades3.grupoXdiaXhora[i][j][k] > pMayor) {
                                pMayor = posibilidades3.grupoXdiaXhora[i][j][k];
                            }
                        }
                    }
                }
            }

            //Buscar mayores en GRUPOxdiaxhora
            for(i=0 ; i<6 ; i++){
                for(j=0 ; j<7 ; j++){
                    for(k=0 ; k<8 ; k++){
                        if(posibilidades3.grupoXdiaXhora[i][j][k] == pMayor || posibilidades3.grupoXdiaXhora[i][j][k] == (pMayor-1)){
                            PrediccionGrupo prediccionGrupo = new PrediccionGrupo(posibilidades3.grupoXdiaXhora[i][j][k], grupos[i], j, horas[k]);
                            listaPrediccionGrupo.add(prediccionGrupo);
                        }

                    }
                }
            }

            //*************************************SECTOR X DIA******************************
            //Almacenar SECTORxdia
            SQLi  = "SELECT COUNT(diaS.num) AS numero FROM Animal A, (SELECT num  FROM Sorteo WHERE date_part('dow', fecha)= all-days ) diaS WHERE diaS.num=A.num AND A.sector=all-sectors ;";
            pMayor = 0;
            for(i=0 ; i<4 ; i++){
                for(j=0 ; j<7 ; j++){
                        String SQLf = (SQLi.replace("all-sectors", sectores[i])).replace("all-days", j.toString());
                        rs = pstm.executeQuery(SQLf);
                        while(rs.next()){
                            posibilidades2.sectorXdia[i][j] = rs.getInt("numero");
                            if(posibilidades2.sectorXdia[i][j] > pMayor) {
                                pMayor = posibilidades2.sectorXdia[i][j];
                            }
                        }
                }
            }

            //Buscar mayores en SECTORxdia
            for(i=0 ; i<4 ; i++){
                for(j=0 ; j<7 ; j++){
                        if(posibilidades2.sectorXdia[i][j] == pMayor || posibilidades2.sectorXdia[i][j] == (pMayor-1)){
                            PrediccionSector prediccionSector = new PrediccionSector(posibilidades2.sectorXdia[i][j], sectores[i], j, "");
                            listaPrediccionSector.add(prediccionSector);
                        }

                }
            }

            //*************************************SECTOR X HORA******************************
            //Almacenar SECTORxhora
            SQLi  = "SELECT COUNT(diaS.num) AS numero FROM Animal A, (SELECT num  FROM Sorteo WHERE hora= all-hours) diaS WHERE diaS.num=A.num AND A.sector=all-sectors ;";
            pMayor = 0;
            for(i=0 ; i<4 ; i++){
                    for(k=0 ; k<8; k++){
                        String SQLf = (SQLi.replace("all-sectors", sectores[i])).replace("all-hours", horas[k]);
                        rs = pstm.executeQuery(SQLf);
                        while(rs.next()){
                            posibilidades2.sectorXhora[i][k] = rs.getInt("numero");
                            if(posibilidades2.sectorXhora[i][k] > pMayor) {
                                pMayor = posibilidades2.sectorXhora[i][k];
                            }
                        }
                    }
            }

            //Buscar mayores en SECTORxhora
            for(i=0 ; i<4 ; i++){
                    for(k=0 ; k<8 ; k++){
                        if(posibilidades2.sectorXhora[i][k] == pMayor || posibilidades2.sectorXhora[i][k] == (pMayor-1)){
                            PrediccionSector prediccionSector = new PrediccionSector(posibilidades2.sectorXhora[i][k], sectores[i], -1, horas[k]);
                            listaPrediccionSector.add(prediccionSector);
                        }

                    }
            }
            //*************************************SECTOR X DIA X HORA******************************
            //Almacenar SECTORxdiaxhora
            SQLi  = "SELECT COUNT(diaS.num) AS numero FROM Animal A, (SELECT num  FROM Sorteo WHERE date_part('dow', fecha)= all-days AND hora= all-hours) diaS WHERE diaS.num=A.num AND A.sector=all-sectors ;";
            pMayor = 0;
            for(i=0 ; i<4 ; i++){
                for(j=0 ; j<7 ; j++){
                    for(k=0 ; k<8; k++){
                        String SQLf = (((SQLi.replace("all-sectors", sectores[i])).replace("all-days", j.toString())).replace("all-hours", horas[k]));
                        rs = pstm.executeQuery(SQLf);
                        while(rs.next()){
                            posibilidades3.sectorXdiaXhora[i][j][k] = rs.getInt("numero");
                            if(posibilidades3.sectorXdiaXhora[i][j][k] > pMayor) {
                                pMayor = posibilidades3.sectorXdiaXhora[i][j][k];
                            }
                        }
                    }
                }
            }

            //Buscar mayores en SECTORxdiaxhora
            for(i=0 ; i<4 ; i++){
                for(j=0 ; j<7 ; j++){
                    for(k=0 ; k<8 ; k++){
                        if(posibilidades3.sectorXdiaXhora[i][j][k] == pMayor || posibilidades3.sectorXdiaXhora[i][j][k] == (pMayor-1)){
                            PrediccionSector prediccionSector = new PrediccionSector(posibilidades3.sectorXdiaXhora[i][j][k], sectores[i], j, horas[k]);
                            listaPrediccionSector.add(prediccionSector);
                        }

                    }
                }
            }

            //*************************************COLUMNA X DIA******************************
            //Almacenar COLUMNAxdia
            SQLi  = "SELECT COUNT(diaS.num) AS numero FROM Animal A, (SELECT num  FROM Sorteo WHERE date_part('dow', fecha)= all-days ) diaS WHERE diaS.num=A.num AND A.columna= all-columns ; ";
            pMayor = 0;
            for(i=0 ; i<4 ; i++){
                for(j=0 ; j<7 ; j++){
                        String SQLf = (SQLi.replace("all-columns", i.toString())).replace("all-days", j.toString());
                        rs = pstm.executeQuery(SQLf);
                        while(rs.next()){
                            posibilidades2.columnaXdia[i][j] = rs.getInt("numero");
                            if(posibilidades2.columnaXdia[i][j] > pMayor) {
                                pMayor = posibilidades2.columnaXdia[i][j];
                            }
                        }
                }
            }

            //Buscar mayores en COLUMNAxdia
            for(i=0 ; i<4 ; i++){
                for(j=0 ; j<7 ; j++){
                        if(posibilidades2.columnaXdia[i][j] == pMayor || posibilidades2.columnaXdia[i][j] == (pMayor-1)){
                            PrediccionColumna prediccionColumna = new PrediccionColumna(posibilidades2.columnaXdia[i][j], i, j,"");
                            listaPrediccionColumna.add(prediccionColumna);
                        }

                    }
            }

            //*************************************COLUMNA X HORA******************************
            //Almacenar COLUMNAxhora
            SQLi  = "SELECT COUNT(diaS.num) AS numero FROM Animal A, (SELECT num  FROM Sorteo WHERE hora= all-hours ) diaS WHERE diaS.num=A.num AND A.fila= all-columns ; ";
            pMayor = 0;
            for(i=0 ; i<4 ; i++){
                    for(k=0 ; k<8; k++){
                        String SQLf = (SQLi.replace("all-columns", i.toString())).replace("all-hours", horas[k]);
                        rs = pstm.executeQuery(SQLf);
                        while(rs.next()){
                            posibilidades2.columnaxhora[i][k] = rs.getInt("numero");
                            if(posibilidades2.columnaxhora[i][k] > pMayor) {
                                pMayor = posibilidades2.columnaxhora[i][k];
                            }
                        }
                    }
            }

            //Buscar mayores en COLUMNAxhora
            for(i=0 ; i<4 ; i++){
                    for(k=0 ; k<8 ; k++){
                        if(posibilidades2.columnaxhora[i][k] == pMayor || posibilidades2.columnaxhora[i][k] == (pMayor-1)){
                            PrediccionColumna prediccionColumna = new PrediccionColumna(posibilidades2.columnaxhora[i][k], i,-1, horas[k]);
                            listaPrediccionColumna.add(prediccionColumna);
                        }

                    }
            }

            //*************************************COLUMNA X DIA X HORA******************************
            //Almacenar COLUMNAxdiaxhora
            SQLi  = "SELECT COUNT(diaS.num) AS numero FROM Animal A, (SELECT num  FROM Sorteo WHERE date_part('dow', fecha)= all-days AND hora= all-hours ) diaS WHERE diaS.num=A.num AND A.fila= all-columns ; ";
            pMayor = 0;
            for(i=0 ; i<4 ; i++){
                for(j=0 ; j<7 ; j++){
                    for(k=0 ; k<8; k++){
                        String SQLf = (((SQLi.replace("all-columns", i.toString())).replace("all-days", j.toString())).replace("all-hours", horas[k]));
                        rs = pstm.executeQuery(SQLf);
                        while(rs.next()){
                            posibilidades3.columnaXdiaXhora[i][j][k] = rs.getInt("numero");
                            if(posibilidades3.columnaXdiaXhora[i][j][k] > pMayor) {
                                pMayor = posibilidades3.columnaXdiaXhora[i][j][k];
                            }
                        }
                    }
                }
            }

            //Buscar mayores en COLUMNAxdiaxhora
            for(i=0 ; i<4 ; i++){
                for(j=0 ; j<7 ; j++){
                    for(k=0 ; k<8 ; k++){
                        if(posibilidades3.columnaXdiaXhora[i][j][k] == pMayor || posibilidades3.columnaXdiaXhora[i][j][k] == (pMayor-1)){
                            PrediccionColumna prediccionColumna = new PrediccionColumna(posibilidades3.columnaXdiaXhora[i][j][k], i, j, horas[k]);
                            listaPrediccionColumna.add(prediccionColumna);
                        }

                    }
                }
            }

                //*************************************FILA X DIA******************************
            //Almacenar ANIMALxdia = 266
            SQLi  = "SELECT COUNT(diaS.num) AS numero FROM Animal A, (SELECT num  FROM Sorteo WHERE date_part('dow', fecha)= all-days) diaS WHERE diaS.num=A.num AND A.fila= all-filas ;";
            for(i=0 ; i<13 ; i++){
                for(j=0 ; j<7 ; j++){
                    String SQLf = ((SQLi.replace("all-filas", i.toString())).replace("all-days", j.toString()));
                    rs = pstm.executeQuery(SQLf);
                    while(rs.next()){
                        posibilidades2.filaxdia[i][j] = rs.getInt("numero");
                        if(posibilidades2.filaxdia[i][j] > pMayor) {
                            pMayor = posibilidades2.filaxdia[i][j];
                        }
                    }
                }
            }

            //Buscar mayores en FILAxdia
            for(i=0 ; i<13 ; i++){
                for(j=0 ; j<7 ; j++){
                    if(posibilidades2.filaxdia[i][j] == pMayor || posibilidades2.filaxdia[i][j] == (pMayor-1)){
                        PrediccionFila prediccionFila = new PrediccionFila(posibilidades2.filaxdia[i][j],i,j,"");
                        listaPrediccionFila.add(prediccionFila);
                    }
                }
            }

            //*************************************FILA X HORA******************************
            //Almacenar FILAxHORA = 304
            pMayor=0;
            SQLi  = "SELECT COUNT(diaS.num) AS numero FROM Animal A, (SELECT num  FROM Sorteo WHERE hora= all-hours ) diaS WHERE diaS.num=A.num AND A.fila= all-filas;";
            for(i=0 ; i<13 ; i++){
                for(k=0 ; k<8 ; k++){
                    String SQLf = ((SQLi.replace("all-filas", i.toString())).replace("all-hours", horas[k]));
                    rs = pstm.executeQuery(SQLf);
                    while(rs.next()){
                        posibilidades2.filaxhora[i][k] = rs.getInt("numero");
                        if(posibilidades2.filaxhora[i][k] > pMayor) {
                            pMayor = posibilidades2.filaxhora[i][k];
                        }
                    }
                }
            }

            //Buscar mayores en FILAxhora
            for(i=0 ; i<13 ; i++){
                for(k=0 ; k<8 ; k++){
                    if(posibilidades2.filaxhora[i][k] == pMayor || posibilidades2.filaxhora[i][k] == (pMayor-1)){
                        PrediccionFila prediccionFila = new PrediccionFila(posibilidades2.filaxhora[i][k],i,-1,horas[k]);
                        listaPrediccionFila.add(prediccionFila);
                    }
                }
            }


            //*************************************FILA X DIA X HORA ******************************
            //Almacenar FILAxdiaxhora =
            pMayor=0;
            SQLi  = "SELECT COUNT(diaS.num) AS numero FROM Animal A, (SELECT num  FROM Sorteo WHERE date_part('dow', fecha)= all-days AND hora= all-hours ) diaS WHERE diaS.num=A.num AND A.fila= all-filas ;";
            for(i=0 ; i<13 ; i++){
                for(j=0 ; j<7 ; j++){
                    for(k=0 ; k<8; k++){
                        String SQLf = (((SQLi.replace("all-filas", i.toString())).replace("all-days", j.toString())).replace("all-hours", horas[k]));
                        rs = pstm.executeQuery(SQLf);
                        while(rs.next()){
                            posibilidades3.filaXdiaXhora[i][j][k] = rs.getInt("numero");
                            if(posibilidades3.filaXdiaXhora[i][j][k] > pMayor) {
                                pMayor = posibilidades3.filaXdiaXhora[i][j][k];
                            }
                        }
                    }
                }
            }

            //Buscar mayores en FILAxdiaxhora
            for(i=0 ; i<13 ; i++){
                for(j=0 ; j<7 ; j++){
                    for(k=0 ; k<8 ; k++){
                        if(posibilidades3.filaXdiaXhora[i][j][k] == pMayor || posibilidades3.filaXdiaXhora[i][j][k] == (pMayor-1)){
                            PrediccionFila prediccionFila = new PrediccionFila(posibilidades3.filaXdiaXhora[i][j][k],i,j,horas[k]);
                            listaPrediccionFila.add(prediccionFila);
                        }
                    }
                }
            }


            //*******************************************ADICIONALES**************************************
            //*************************************REPETICIONES ANIMAL X DIA******************************
            //Almacenar REPETICIONES animalxdia =
            pMayor=0;
            SQLi  = "SELECT COUNT(DISTINCT S.num) AS numero FROM Sorteo S, (SELECT fecha, hora FROM Sorteo WHERE num= all-animals ) PA WHERE S.num= all-animals AND S.hora<>PA.hora AND S.fecha=PA.fecha AND date_part('dow', S.fecha) = all-days ";
            for(i=0 ; i<38 ; i++){
                for(j=0 ; j<7 ; j++){
                    String SQLf = ((SQLi.replace("all-animals", i.toString())).replace("all-days", j.toString()));
                    rs = pstm.executeQuery(SQLf);
                    while(rs.next()){
                        posibilidades2.repeticiones[i][j] = rs.getInt("numero");
                        if(posibilidades2.repeticiones[i][j] > pMayor) {
                            pMayor = posibilidades2.repeticiones[i][j];
                        }
                    }
                }
            }

            //Buscar mayores en REPETICIONES animalxdia
            for(i=0 ; i<38 ; i++){
                for(j=0 ; j<7 ; j++){
                    if(posibilidades2.repeticiones[i][j] == pMayor){
                        PrediccionRepeticion prediccionRepeticion = new PrediccionRepeticion(posibilidades2.repeticiones[i][j],i,j);
                        listaPrediccionRepeticion.add(prediccionRepeticion);
                    }
                }
            }

            //*************************************REPETICIONES ANIMAL******************************
            //Almacenar REPETICIONES=37
            pMayor=0;
            int temp=0;

            //inicialización
            for(i=0 ; i<38 ; i++){
                posibilidades1.repeticionesAnimal[i] = 0;
            }

            for(i=0 ; i<38 ; i++){
                for(j=0 ; j<7 ; j++){
                    temp += posibilidades2.repeticiones[i][j];
                }
                posibilidades1.repeticionesAnimal[i] = temp;
                temp=0;
            }

            //Buscar mayores en REPETICIONES
            for(i=0 ; i<38 ; i++){
                if(posibilidades1.repeticionesAnimal[i] > pMayor){
                    pMayor = posibilidades1.repeticionesAnimal[i];
                }
            }

            for(i=0 ; i<38 ; i++){
                if(posibilidades1.repeticionesAnimal[i] == pMayor || posibilidades1.repeticionesAnimal[i] == (pMayor-1)){
                    PrediccionRepeticion prediccionRepeticion = new PrediccionRepeticion(posibilidades1.repeticionesAnimal[i],i,-1);
                    listaPrediccionRepeticion.add(prediccionRepeticion);
                }
            }

            //*************************************REPETICIONES DIA******************************
            //Almacenar REPETICIONES dia = 7
            pMayor=0;
            temp=0;

            //inicialización
            for(i=0 ; i<7 ; i++){
                posibilidades1.repeticionesDia[i] = 0;
            }

            for(j=0 ; j<7 ; j++){
                for(i=0 ; i<38 ; i++){
                    temp += posibilidades2.repeticiones[i][j];
                }
                posibilidades1.repeticionesDia[j] = temp;
                temp=0;
            }

            //Buscar mayores en REPETICIONES dia
            for(j=0 ; j<7 ; j++){
                if(posibilidades1.repeticionesDia[j] > pMayor){
                    pMayor = posibilidades1.repeticionesDia[j];
                }
            }

            for(j=0 ; j<7 ; j++){
                if(posibilidades1.repeticionesDia[j] == pMayor || posibilidades1.repeticionesDia[j] == (pMayor-1)){
                    PrediccionRepeticion prediccionRepeticiones = new PrediccionRepeticion(posibilidades1.repeticionesDia[j],-1,j);
                    listaPrediccionRepeticion.add(prediccionRepeticiones);
                }
            }
*/

            //*************************************COINCIDENCIA ANIMAL X DIA******************************
            //Almacenar COINCIDENCIAS dia =
            SQLi  = "SELECT COUNT(num) AS numero FROM Sorteo WHERE num= all-animals AND date_part('day', fecha) = all-animals ;";
            for(i=1 ; i<32 ; i++){
                String SQLf = SQLi.replaceAll("all-animals", i.toString());
                rs = pstm.executeQuery(SQLf);
                while(rs.next()){
                    posibilidades1.coincideDia[i-1] = rs.getInt("numero");
                    if(posibilidades1.coincideDia[i-1] > pMayor) {
                        pMayor = posibilidades1.coincideDia[i-1];
                    }
                }
            }

            for(i=0 ; i<31 ; i++){
                if(posibilidades1.coincideDia[i] == pMayor || posibilidades1.coincideDia[i] == (pMayor-1)){
                    PrediccionCoincidencia prediccionCoincidencia = new PrediccionCoincidencia(posibilidades1.coincideDia[i],i,"");
                }
            }
/*
            //*************************************COINCIDENCIA ANIMAL X HORA******************************
            //Almacenar COINCIDENCIAS hora =
            pMayor=0;
            SQLi  = "SELECT COUNT(num) AS numero FROM Sorteo WHERE num= all-animals1 AND hora = all-animals2 ;";
            for(k=0 ; k<8 ; i++){
                String SQLf = (SQLi.replace("all-animals2", horas[k])).replace("all-animals1", horas[k].replaceAll("'",""));
                rs = pstm.executeQuery(SQLf);
                while(rs.next()){
                    posibilidades1.coincideHora[k] = rs.getInt("numero");
                    if(posibilidades1.coincideHora[k] > pMayor) {
                        pMayor = posibilidades1.coincideHora[k];
                    }
                }
            }

            for(k=0 ; k<8 ; k++){
                if(posibilidades1.coincideHora[k] == pMayor || posibilidades1.coincideHora[k] == (pMayor-1)){
                    PrediccionCoincidencia prediccionCoincidencia = new PrediccionCoincidencia(posibilidades1.coincideDia[i],-1,horas[k]);
                    listaPrediccionCoincidencia.add(prediccionCoincidencia);
                }
            }


*/

            /*
            System.out.println("ANIMALES:");
            for(int p=0 ; p<listaPrediccionAnimal.size() ; p++){
                System.out.println("POSIBILIDADES:"+listaPrediccionAnimal.get(p).getPosibles()+" ANIMAL:"+listaPrediccionAnimal.get(p).getAnimal()+" DIA:"+listaPrediccionAnimal.get(p).getDia()+" HORA:"+listaPrediccionAnimal.get(p).getHora());
            }

            System.out.println("COLORES:");
            for(int p=0 ; p<listaPrediccionColor.size() ; p++){
                System.out.println("POSIBILIDADES:"+listaPrediccionColor.get(p).getPosibles()+" COLOR:"+listaPrediccionColor.get(p).getColor()+" DIA:"+listaPrediccionColor.get(p).getDia()+" HORA:"+listaPrediccionColor.get(p).getHora());
            }

            System.out.println("GRUPOS:");
            for(int p=0 ; p<listaPrediccionGrupo.size() ; p++){
                System.out.println("POSIBILIDADES:"+listaPrediccionGrupo.get(p).getPosibles()+" GRUPO:"+listaPrediccionGrupo.get(p).getGrupo()+" DIA:"+listaPrediccionGrupo.get(p).getDia()+" HORA:"+listaPrediccionGrupo.get(p).getHora());
            }

            System.out.println("SECTORES:");
            for(int p=0 ; p<listaPrediccionSector.size() ; p++){
                System.out.println("POSIBILIDADES:"+listaPrediccionSector.get(p).getPosibles()+" SECTOR:"+listaPrediccionSector.get(p).getSector()+" DIA:"+listaPrediccionSector.get(p).getDia()+" HORA:"+listaPrediccionSector.get(p).getHora());
            }

            System.out.println("COLUMNAS:");
            for(int p=0 ; p<listaPrediccionColumna.size() ; p++){
                System.out.println("POSIBILIDADES:"+listaPrediccionColumna.get(p).getPosibles()+" COLUMNA:"+listaPrediccionColumna.get(p).getColumna()+" DIA:"+listaPrediccionColumna.get(p).getDia()+" HORA:"+listaPrediccionColumna.get(p).getHora());
            }

            System.out.println("FILAS:");
            for(int p=0 ; p<listaPrediccionFila.size() ; p++){
                System.out.println("POSIBILIDADES:"+listaPrediccionFila.get(p).getPosibles()+" FILA:"+listaPrediccionFila.get(p).getFila()+" DIA:"+listaPrediccionFila.get(p).getDia()+" HORA:"+listaPrediccionFila.get(p).getHora());
            }

            System.out.println("REPETICIONES:");
            for(int p=0 ; p<listaPrediccionRepeticiones.size() ; p++){
                System.out.println("POSIBILIDADES:"+listaPrediccionRepeticiones.get(p).getPosibles()+" ANIMAL:"+listaPrediccionRepeticiones.get(p).getAnimal()+" DIA:"+listaPrediccionRepeticiones.get(p).getDia());
            }
            */
            System.out.println("COINCIDENCIA DIA: ");
            for(int p=0 ; p<listaPrediccionCoincidencia.size() ; p++){
                System.out.println("POSIBILIDADES:"+listaPrediccionCoincidencia.get(p).getPosibles()+" DIA:"+listaPrediccionCoincidencia.get(p).getDia()+" HORA:"+listaPrediccionCoincidencia.get(p).getHora());
            }

            SQLi  = "SELECT COUNT(num) AS numero FROM Sorteo WHERE num= all-animals AND date_part('day', fecha) = all-animals ;";
            String SQLf = SQLi.replaceAll("all-animals", i.toString());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Main.close(connection);
        }
    }

    public static Connection getConnection(String driverClass, String url, String username, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClass);
        return DriverManager.getConnection(url, username, password);
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
