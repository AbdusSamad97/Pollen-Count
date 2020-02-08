package com.example.pollenmonitoring;

public class ListItem {
    private String Question;
    private String Anwser;

    public ListItem(String Anwser, String Question) {
        this.Anwser = Anwser;
        this.Question = Question;

    }

    public void setQuestion(String question) {
        Question = question;
    }

    public void setAnwser(String anwser) {
        Anwser = anwser;
    }

    public String getQuestion() {
        return Question;
    }

    public String getAnwser() {
        return Anwser;
    }
}
