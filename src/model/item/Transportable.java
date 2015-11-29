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
public abstract class  Transportable extends Item{
    
    private final int WEIGHT;
    private final String URL;
    private final boolean IMMORTAL;
   
    public Transportable(String name, String description,int weight,boolean immortal, String URL) {
        super(name, description);
        this.WEIGHT = weight;
        this.URL = URL;
        this.IMMORTAL = immortal;
    }
    
  

    public String getURL() {
        return URL;
    }

    /**
     * @return the WEIGHT
     */
    public int getWEIGHT() {
        return WEIGHT;
    }

    /**
     * @return the IMMORTAL
     */
    public boolean isIMMORTAL() {
        return IMMORTAL;
    }
   
   

}
