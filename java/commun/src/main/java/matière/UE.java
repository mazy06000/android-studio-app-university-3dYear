package matière;

import java.io.Serializable;

public class UE implements Serializable {
    private final String code;
    private final String discipline;
    private final int semestre;
    private final int ECTS;
    private final String nomUE;
    private final int nbPlaces;

    public UE(String nomUE, String code, String discipline, int semestre, int ECTS, int nbPlaces) {
        this.code = code;
        this.discipline = discipline;
        this.semestre = semestre;
        this.ECTS = ECTS;
        this.nomUE = nomUE;
        this.nbPlaces = nbPlaces;
    }

    public String getCode() {
        return code;
    }

    public String getDiscipline() {
        return discipline;
    }

    public int getSemestre() {
        return semestre;
    }

    public int getECTS() {
        return ECTS;
    }

    public String getNomUE() {
        return nomUE;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }
}
