/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBaseManager;

import java.util.HashMap;

/**
 * 
 *@author Florent Plomb <plombf at gmail.com>
 */
public class Question {
    
    private String title;
    private HashMap<String, Integer> answers;

    public Question() {
    }
    
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the answers
     */
    public HashMap<String, Integer> getAnswers() {
        return answers;
    }

    /**
     * @param answers the answers to set
     */
    public void setAnswers(HashMap<String, Integer> answers) {
        this.answers = answers;
    }

}
