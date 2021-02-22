package fr.on.mange.quoi.organizer.domain.model;

public enum MealOrganizer {

    ANYONE, BREAK_FAST, LUNCH, SNACK, APERITIF, SUPPER;

    public String labelFr() {
        switch (this) {
            case BREAK_FAST:
                return "Petit déjeuner";
            case LUNCH:
                return "Déjeuner";
            case SNACK:
                return "Gouter";
            case APERITIF:
                return "Apéro";
            case SUPPER:
                return "Diner";
            default:
                return "";
        }
    }
}
