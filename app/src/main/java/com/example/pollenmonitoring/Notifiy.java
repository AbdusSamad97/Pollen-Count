package com.example.pollenmonitoring;

public class Notifiy {
    private String Allergy;
    private String Category;

    public Notifiy(String allergy, String category) {
        Allergy = allergy;
        Category = category;
    }

    public String getAllergy() {
        return Allergy;
    }

    public String getCategory() {
        return Category;
    }

    public void setAllergy(String allergy) {
        Allergy = allergy;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
