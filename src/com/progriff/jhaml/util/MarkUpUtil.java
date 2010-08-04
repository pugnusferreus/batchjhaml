package com.progriff.jhaml.util;

import java.util.ArrayList;
import java.util.List;

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
    
    public static List<String> getScriptNames(String outputLine) {
        outputLine = outputLine.replace("<%= javascripts", "");
        outputLine = outputLine.replace("%>","");
        outputLine = outputLine.replace("'","");
        outputLine = outputLine.replace("\"","");
        if(outputLine.trim().length() == 0) {
            return null;
        }
        
        String[] temp = outputLine.split(",");
        ArrayList<String> scriptNames = new ArrayList<String>();
        for(String name:temp) {
            scriptNames.add(name.trim());
        }
        return scriptNames;
    }
    
    public static List<String> getIndividualFileScripts(String hamlOutput) {
        String[] temp = hamlOutput.split("\n");
        for(String eachLine: temp) {
            if(eachLine.contains("<% @javascripts")) {
                eachLine = eachLine.replace("<% @javascripts", "");
                eachLine = eachLine.replace("%>", "");
                eachLine = eachLine.replace("'", "");
                eachLine = eachLine.replace("\"", "");
                
                if(eachLine.trim().length() == 0) {
                    return null;
                }
                
                String[] temp2 = eachLine.split(",");
                ArrayList<String> scriptNames = new ArrayList<String>();
                for(String name:temp2) {
                    scriptNames.add(name.trim());
                }
                return scriptNames;
            }
        }
        return null;
    }

}
