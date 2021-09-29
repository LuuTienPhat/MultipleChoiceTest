/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Phat
 */
public class MultipleChoiceQuestion implements Serializable{
    private String question;
    private HashMap<String, String> choices = new HashMap<>();
    private String answer;

    public MultipleChoiceQuestion() {
    }

    public MultipleChoiceQuestion(String question, HashMap<String, String> choices, String answer) {
        this.question = question;
        this.choices = choices;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public HashMap<String, String> getChoices() {
        return choices;
    }

    public void setChoices(HashMap<String, String> choices) {
        this.choices = choices;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
