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
public  class Item {

    private String name;
    private String description;

    public Item (String name,String description) {
        this.name = name;
        this.description = description;
    }
}
