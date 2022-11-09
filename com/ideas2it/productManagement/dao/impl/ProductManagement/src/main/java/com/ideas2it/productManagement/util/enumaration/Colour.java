package com.ideas2it.productManagement.util.enumaration;

/**
 * This class used to get product colour
 *
 * @author Thangapandiyan
 * @version 1.0 
 */
public enum Colour{
    BLACK(1),
    RED(2),
    BLUE(3),
    GREEN(4);
  
    public final int value;

    Colour(final int value) {
        this.value = value;
    }
    
    /**
     * Get the choice from the user and return the enum constant based on user choice.
     *
     * @param choice the user choice to get the enum constant 
     * @return result - if value is matched                       
     */  
    public static Colour getColour(int value) {
        Colour result = null;
        for(Colour colour : Colour.values()) {
            if(colour.value == value) {
                result = colour;
                break;
            }
        }
        return result;
    }
}