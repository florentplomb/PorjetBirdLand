/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Quizz.QuizzController;
import Quizz.Result;
import java.util.ArrayList;
import model.item.Item;

/**
 *
 * @author Bryan Cornelius
 */
public abstract class Person {

    private String name;
    private ArrayList<Item> items;
    private Room currentRoom;

    public Person(String name, Room currentRoom) {
        this.name = name;
        this.currentRoom = currentRoom;
        this.items = new ArrayList<Item>();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Item> getItem() {
        return this.items;
    }
    
    public void setCurrentRoom(Room room){
        this.currentRoom = room; 
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public void setItem(Item item) {
        items.add(item);
    }
    
    public void getQuizz(){
        
         int cpt = 0;
         int nbQuestion = 3;

        while (cpt < nbQuestion) {
            Result r = QuizzController.getPrintQuestion();
            if (r.isIsCorrect()) {
                System.out.println("Correct! - Answer = " + r.getAnswer());
               
            } else {
                System.out.println("Incorrect!");
                System.out.println("The Correct answer = " + r.getAnswer());
              
            }
            cpt++;
        }
       
          System.out.println("***************YOU WIN******************");
    }

}
