package com.progriff.jhaml;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.cadrlife.jhaml.JHaml;

public class BatchJHaml {

    private String hamlPath;
    private String outputPath;
    private String outputExtension;
    
    private final static String CURRENT_PATH = System.getProperty("user.dir");
    private final static String SEPERATOR = 
                                    System.getProperty("file.separator");
    
    private HashMap<String, String> layouts = new HashMap<String, String>();
    
    public BatchJHaml() {
        this(CURRENT_PATH + SEPERATOR + "haml", 
                CURRENT_PATH + SEPERATOR + "output", 
                "jsp");
    }
    
    public BatchJHaml(String hamlPath,
                        String outputPath,
                        String outputExtension) {
        this.hamlPath = hamlPath;
        this.outputPath = outputPath;
        this.outputExtension = outputExtension;
    }
    
    public void generateOutput() {
        File file = new File(this.hamlPath);
        populateLayouts();
        
        for(File hamlFile: file.listFiles(getFilenameFilter())) {
            try {
                String hamlOutput = getHamlOutput(hamlFile);
                File outputFile = new File(this.outputPath + SEPERATOR + StringUtils.replace(hamlFile.getName(), ".haml", "." + this.outputExtension));
                writeToFile(outputFile, hamlOutput);
            }
            catch(IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
    
    public FilenameFilter getFilenameFilter() {
        return new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return !name.startsWith(".") && name.endsWith(".haml");
            }
        };
    }
    
    
    private void writeToFile(File outputFile, String hamlOutput) throws IOException {
        String layout = this.layouts.get("application.haml");
        FileUtils.writeStringToFile(outputFile, StringUtils.replace(layout, "<%= yield %>", hamlOutput) );
    }
    
    private void populateLayouts() {
        JHaml jhaml = new JHaml();
        File file = new File(this.hamlPath + SEPERATOR + "layouts");
        for(File layoutFile: file.listFiles(getFilenameFilter())) {
            try {
                this.layouts.put(layoutFile.getName(), jhaml.parse(FileUtils.readFileToString(layoutFile)));
            }
            catch(IOException ioe) {
                System.err.println("Unable to convert layout : " + layoutFile.getName());
                ioe.printStackTrace();
            }
        }
        
    }
    
    private String getHamlOutput(File hamlFile) throws IOException{
        JHaml jhaml = new JHaml();
        return jhaml.parse(FileUtils.readFileToString(hamlFile));
    }
    
    public static void main(String[] args) {
        BatchJHaml batch = new BatchJHaml();
        batch.generateOutput();
        System.out.println("File Generation Completed!");
        
    }
}
