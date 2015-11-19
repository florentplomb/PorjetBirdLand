/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.item;

/**
 * 
 *@author Florent Plomb <plombf at gmail.com>
 */
public class BananaPeel extends Transportable{

    public BananaPeel(String name, String description,int weight) {
        super(name, description,weight);
    }

    @Override
    public void action() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
