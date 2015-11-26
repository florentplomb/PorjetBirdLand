/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

/**
 *
 * @author Florent Plomb <plombf at gmail.com>
 */
public abstract class Item {

    private String NAME;
    private String DESCRIPTION;

    public Item (String name,String description) {
        this.NAME = name;
        this.DESCRIPTION = description;
    }
    
    public abstract void action();

    @Override
    public String toString() {
        return getNAME(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the NAME
     */
    public String getNAME() {
        return NAME;
    }

    /**
     * @param NAME the NAME to set
     */
    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    /**
     * @return the DESCRIPTION
     */
    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    /**
     * @param DESCRIPTION the DESCRIPTION to set
     */
    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }
    
    
}
