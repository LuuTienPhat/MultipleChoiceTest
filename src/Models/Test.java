/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Phat
 */
public class Test implements Serializable{

    private String StudentId;
    private int score;
    private ArrayList<MultipleChoiceQuestion> multipleChoiceQuestion = new ArrayList<>();
    private String level;
    private LocalDateTime datetime;

    public Test() {
    }

    public Test(String StudentId, int score, ArrayList<MultipleChoiceQuestion> multipleChoiceQuestion, String level, LocalDateTime datetime) {
        this.StudentId = StudentId;
        this.score = score;
        this.multipleChoiceQuestion = multipleChoiceQuestion;
        this.level = level;
        this.datetime = datetime;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String StudentId) {
        this.StudentId = StudentId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<MultipleChoiceQuestion> getMultipleChoiceQuestion() {
        return multipleChoiceQuestion;
    }

    public void setMultipleChoiceQuestion(ArrayList<MultipleChoiceQuestion> multipleChoiceQuestion) {
        this.multipleChoiceQuestion = multipleChoiceQuestion;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

}
