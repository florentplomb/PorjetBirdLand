/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.item;

import java.net.URL;

/**
 * Implementation of Transportable model. This class represent the transportable
 * objects which can be transported by the player
 *
 * @author Florent Plomb <plombf at gmail.com>
 */
public abstract class Transportable extends Item {

    private final int weight;
    private final String URL;
    private final boolean immortal;

    /**
     *
     * @param name the object name.
     * @param description the object description.
     * @param weight the object weight.
     * @param immortal true if the object apprear in the room inventory when the
     * player drop it.
     * @param URL the picture url.
     */
    public Transportable(String name, String description, int weight, boolean immortal, String URL) {
        super(name, description);
        this.weight = weight;
        this.URL = URL;
        this.immortal = immortal;
    }

    /**
     * @return the url
     */
    public String getURL() {
        return URL;
    }

    /**
     * @return the WEIGHT
     */
    public int getWEIGHT() {
        return weight;
    }

    /**
     * @return the IMMORTAL
     */
    public boolean isIMMORTAL() {
        return immortal;
    }

}
