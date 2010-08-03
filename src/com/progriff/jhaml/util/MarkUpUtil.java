package com.progriff.jhaml.util;

/**
 * <p>Utilities for the markup</p>
 * @author Benson Lim
 */
public class MarkUpUtil {
    
    /**
     * Returns the layout file name
     * @param output the html output
     * @return the layout file name
     */
    public static String getLayoutName(String output) {
        String[] temp = output.split("\n");
        
        if(temp != null && temp.length > 0) {
            if(temp[0].contains("@layout")) {
                if(temp[0].contains("\"")) {
                    String[] temp2 = temp[0].split("\"");
                    if(temp2 != null && temp2.length == 3)
                        return temp2[1];
                } else if(temp[0].contains("'")) {
                    String[] temp2 = temp[0].split("'");
                    if(temp2 != null && temp2.length == 3)
                        return temp2[1];
                }
            }
        }
        return null;
    }
}
