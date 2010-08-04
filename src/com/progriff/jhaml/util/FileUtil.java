package com.progriff.jhaml.util;

import java.io.File;
import java.io.FilenameFilter;


public class FileUtil {
    /**
     * Returns a filter that accepts filenames that: <br/>
     * <ul>
     *   <li/> doesn't start with "."
     *   <li/> ends with ".haml"
     * </ul>
     * @return the FilenameFilter object.
     */
    public static FilenameFilter getHamlFilenameFilter() {
        return new FilenameFilter() {
            public boolean accept( File dir, String name ) {
                return !name.startsWith(".") && name.endsWith(".haml");
            }
        };
    }
    
}
