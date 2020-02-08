package com.example.pollenmonitoring;

public class ListView {
    private String Date;
    private String Day;
    private String Category;

    public ListView(String date, String day, String category) {
        Date = date;
        Day = day;
        Category = category;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setDay(String day) {
        Day = day;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDate() {
        return Date;
    }

    public String getDay() {
        return Day;
    }

    public String getCategory() {
        return Category;
    }
}
