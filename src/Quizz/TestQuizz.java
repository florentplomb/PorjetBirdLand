package Quizz;

import PrisonBreak.*;
import model.Game;
import model.*;
import model.item.BananaPeel;

public class TestQuizz {

    //The game started from the method
    public static void main(String[] args) {
          int cpt = 0;

        while (cpt < 5) {
            Result r = QuizzController.getPrintQuestion();
            if (r.isIsCorrect()) {
                System.out.println("Correct! - Answer = " + r.getAnswer());
                cpt++;
                   System.out.println("- Score :" + cpt + "/5 -");
            } else {
                System.out.println("Incorrect!");
                System.out.println("The Correct answer = " + r.getAnswer());
                if (cpt > 0) {
                    cpt--;
                }
                System.out.println("- Score :" + cpt + "/5 -" );
            }
        }
       
          System.out.println("***************YOU WIN******************");
    }
        
    
}
