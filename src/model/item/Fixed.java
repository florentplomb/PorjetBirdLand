/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

/**
 * Implementation of Fixed model. This class represent the fixed objects which
 * can be present in the game rooms
 *
 * @author Florent Plomb <plombf at gmail.com>
 */
public abstract class Fixed extends Item {

    /**
     *
     * @param name The item's name
     * @param description The item's description
     */

    public Fixed(String name, String description) {
        super(name, description);
    }

}
