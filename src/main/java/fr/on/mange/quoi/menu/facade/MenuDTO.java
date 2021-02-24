package fr.on.mange.quoi.menu.facade;


import java.util.ArrayList;
import java.util.List;

public class MenuDTO {
    private String label;
    private String id;
    private String dateDebut;
    private String dateFin;
    private List <MenuJourDTO> lundi= new ArrayList();
    private List <MenuJourDTO> mardi=new ArrayList();
    private List <MenuJourDTO> mercredi=new ArrayList();
    private List <MenuJourDTO> jeudi=new ArrayList();
    private List <MenuJourDTO> vendredi=new ArrayList();
    private List <MenuJourDTO> samedi=new ArrayList();
    private List <MenuJourDTO> dimanche=new ArrayList();



    public void setLundi(List<MenuJourDTO> lundi) {
        this.lundi = lundi;
    }

    public List<MenuJourDTO> getLundi() {
        return lundi;
    }

    public List<MenuJourDTO> getMardi() {
        return mardi;
    }

    public void setMardi(List<MenuJourDTO> mardi) {
        this.mardi = mardi;
    }

    public List<MenuJourDTO> getMercredi() {
        return mercredi;
    }

    public void setMercredi(List<MenuJourDTO> mercredi) {
        this.mercredi = mercredi;
    }

    public List<MenuJourDTO> getJeudi() {
        return jeudi;
    }

    public void setJeudi(List<MenuJourDTO> jeudi) {
        this.jeudi = jeudi;
    }

    public List<MenuJourDTO> getVendredi() {
        return vendredi;
    }

    public void setVendredi(List<MenuJourDTO> vendredi) {
        this.vendredi = vendredi;
    }

    public List<MenuJourDTO> getSamedi() {
        return samedi;
    }

    public void setSamedi(List<MenuJourDTO> samedi) {
        this.samedi = samedi;
    }

    public List<MenuJourDTO> getDimanche() {
        return dimanche;
    }

    public void setDimanche(List<MenuJourDTO> dimanche) {
        this.dimanche = dimanche;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }


}


