package matière;

import java.io.Serializable;

public class UE implements Serializable {
    private final String code;
    private final String discipline;
    private final int semestre;
    private final int ECTS;
    private final String nomUE;
    private final int nbPlaces;
    private Boolean preChecked = false;
    private Boolean sansSousMatieres = false;

    public UE(String code, String discipline, String nomUE, int semestre, int ECTS, int nbPlaces) {
        this.code = code;
        this.discipline = discipline;
        this.semestre = semestre;
        this.ECTS = ECTS;
        this.nomUE = nomUE;
        this.nbPlaces = nbPlaces;
    }

    public UE(String code, String discipline, String nomUE, int semestre, int ECTS, int nbPlaces, boolean preChecked, boolean sansSousMatieres) {
        this.code = code;
        this.discipline = discipline;
        this.semestre = semestre;
        this.ECTS = ECTS;
        this.nomUE = nomUE;
        this.nbPlaces = nbPlaces;
        this.preChecked = preChecked;
        this.sansSousMatieres = sansSousMatieres;
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

    public Boolean getPreChecked() {
        return preChecked;
    }

    public Boolean getSansSousMatieres() {
        return sansSousMatieres;
    }
}
