/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.item;

import model.item.Item;

/**
 * 
 *@author Florent Plomb <plombf at gmail.com>
 */
public abstract class  Transportable extends Item{
    
    private int weight;
   
    public Transportable(String name, String description) {
        super(name, description);
    }
    
    

}
