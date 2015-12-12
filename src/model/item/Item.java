/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

/**
 * Implementaion of the Item model An instance of intance can be carry by the
 * player or present in the game room.
 *
 * @author Florent Plomb <plombf at gmail.com>
 */
public abstract class Item {

    private String name;
    private String description;

    /**
     *
     * @param name the object's name
     * @param description the object's description
     */
    public Item(String name, String description) {
        this.name = name.toLowerCase();
        this.description = description;
    }

    @Override
    public String toString() {
        return getNAME(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the NAME
     */
    public String getNAME() {
        return name;
    }

    /**
     * @param NAME the NAME to set
     */
    public void setNAME(String NAME) {
        this.name = NAME;
    }

    /**
     * @return the DESCRIPTION
     */
    public String getDESCRIPTION() {
        return description;
    }

    /**
     * @param DESCRIPTION the DESCRIPTION to set
     */
    public void setDESCRIPTION(String DESCRIPTION) {
        this.description = DESCRIPTION;
    }

}
