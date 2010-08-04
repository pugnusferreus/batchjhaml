package com.progriff.jhaml.model;

public class Configuration {
    private String hamlPath;
    private String hamlLayoutPath;
    private String outputPath;
    private String outputExtension;
    private String scriptPath;
    private String styleSheetPath;
    
    public Configuration() {
        
    }

    public Configuration(String hamlPath, 
                            String hamlLayoutPath,
                            String outputPath,
                            String outputExtension,
                            String scriptPath,
                            String styleSheetPath) {
        this.hamlPath = hamlPath;
        this.hamlLayoutPath = hamlLayoutPath;
        this.outputPath = outputPath;
        this.outputExtension = outputExtension;
        this.scriptPath = scriptPath;
        this.styleSheetPath = styleSheetPath;
    }

    public String getHamlPath() {
        return hamlPath;
    }

    public void setHamlPath(String hamlPath) {
        this.hamlPath = hamlPath;
    }

    public String getHamlLayoutPath() {
        return hamlLayoutPath;
    }

    public void setHamlLayoutPath(String hamlLayoutPath) {
        this.hamlLayoutPath = hamlLayoutPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getOutputExtension() {
        return outputExtension;
    }

    public void setOutputExtension(String outputExtension) {
        this.outputExtension = outputExtension;
    }

    public String getScriptPath() {
        return scriptPath;
    }

    public void setScriptPath(String scriptPath) {
        this.scriptPath = scriptPath;
    }

    public String getStyleSheetPath() {
        return styleSheetPath;
    }

    public void setStyleSheetPath(String styleSheetPath) {
        this.styleSheetPath = styleSheetPath;
    }
}
