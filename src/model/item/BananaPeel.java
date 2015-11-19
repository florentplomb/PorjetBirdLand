/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.item;

import java.net.URL;

/**
 * 
 *@author Florent Plomb <plombf at gmail.com>
 */
public class BananaPeel extends Transportable{

    public BananaPeel(String name, String description,int weight) {
        super(name, description,weight);
    }
    
    public BananaPeel(String name,String description,int weight,String URL){
        super(name, description,weight,URL);
    }

    @Override
    public void action() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
