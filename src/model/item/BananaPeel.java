/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

/**
 * Implementation of Banana peel model
 *
 * @author Florent Plomb <plombf at gmail.com>
 */
public class BananaPeel extends Transportable {

    /**
     *
     * @param name the object name.
     * @param description the object description.
     * @param weight the object weight.
     * @param immortal true if the object apprear in the room inventory when the
     * player drop it.
     * @param URL the picture url.
     */
    public BananaPeel(String name, String description, int weight, boolean immortal, String URL) {
        super(name, description, weight, immortal, URL);
    }

}
