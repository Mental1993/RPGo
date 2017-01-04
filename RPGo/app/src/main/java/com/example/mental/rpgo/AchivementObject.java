package com.example.mental.rpgo;

/**
 *
 * Contains the achivement object with it's attributes. Also contains setters and getters
 *
 * @author Mental
 * @version 1.0
 */

public class AchivementObject {

    private int id;
    private String name;
    private String desc;

    /**
     * Gets the {@link Integer} instance of achivement's id
     * @return The {@link Integer} instance of achivement's id
     */
    public int getId(){ return id; }

    /**
     * Sets the {@link int} instance of achivement's id
     * @param id int: The new value of id to be passed
     */
    public void setId(int id) { this.id = id; }

    /**
     * Gets the {@link String} instance of achivement's descryption
     * @return The {@link String} instance of achivement's descryption
     */
    public String getDesc() { return desc; }

    /**
     * Gets the {@link String} instance of achivement's descryption
     * @param desc int: The new value of achivement's descryption to be passed.
     */
    public void setDesc(String desc) { this.desc = desc; }

    /**
     * Gets the {@link String} instance of achivement's name
     * @return The {@link String} instance of achivement's name
     */
    public String getName() { return name; }

    /**
     * Sets the {@link String} instance of achivement's name
     * @param name String: The new value of achivement's name
     */
    public void setName(String name) { this.name = name; }
}
