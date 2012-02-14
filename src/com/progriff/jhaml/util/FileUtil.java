package com.progriff.jhaml.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class FileUtil {

    /**
     * File seperator.
     */
    public final static String SEPERATOR = System.getProperty("file.separator");

    /**
     * Returns a filter that accepts filenames that: <br/>
     * <ul>
     * <li/>doesn't start with "."
     * <li/>ends with ".haml"
     * </ul>
     * 
     * @return the FilenameFilter object.
     */
    public static FilenameFilter getHamlFilenameFilter() {
        return new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return !name.startsWith(".") && name.endsWith(".haml");
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static List<File> listFiles(String path,
            FilenameFilter fileNameFilter, boolean recursive) {
        if (recursive) {
            Collection<File> files = FileUtils.listFiles(new File(path),
                    TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);

            return new ArrayList<File>(files);
        } else {
            File file = new File(path);
            if (file == null || file.listFiles(fileNameFilter) == null) {
                return new ArrayList<File>();
            }
            return Arrays.asList(file.listFiles(fileNameFilter));
        }

    }

    public static String getSubDirectory(File file, String hamlPath) {
        String[] temp = file.getPath().split(hamlPath + SEPERATOR);
        return SEPERATOR + temp[1].replace(file.getName(), "");
    }
}
