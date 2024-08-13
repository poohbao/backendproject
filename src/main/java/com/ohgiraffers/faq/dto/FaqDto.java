package com.ohgiraffers.faq.dto;

public class FaqDto {
    private int number;
    private String question;
    private String answer;

    // 생성자
    public FaqDto(int number, String question, String answer) {
        this.number = number;
        this.question = question;
        this.answer = answer;
    }

    // getter 및 setter
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
