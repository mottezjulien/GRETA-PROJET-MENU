package fr.on.mange.quoi.organizer.facade;


public class AddCategoryDTORequest {

    private String dayId;

    private String categoryId;


    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
