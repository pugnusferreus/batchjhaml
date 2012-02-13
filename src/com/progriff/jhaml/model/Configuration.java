package com.progriff.jhaml.model;

public class Configuration {
    private String hamlPath;
    private String hamlLayoutPath;
    private String outputPath;
    private String outputExtension;
    private String scriptPath;
    private String styleSheetPath;
    private boolean recursive = false;

    public Configuration() {

    }

    /**
     * To parse this from
     * 
     * @param args
     */
    public Configuration(String[] args) throws IllegalArgumentException {
        try {
            boolean recursive = false;
            if (args.length == 7) {
                recursive = Boolean.parseBoolean(args[6]);
            }

            this.hamlPath = args[0];
            this.hamlLayoutPath = args[1];
            this.outputPath = args[2];
            this.outputExtension = args[3];
            this.scriptPath = args[4];
            this.styleSheetPath = args[5];
            this.recursive = recursive;

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(
                    "Unable to parse args[]. Array index out of bound occured",
                    e);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(
                    "Unable to parse args[]. Null pointer exception occured", e);
        }
    }

    public Configuration(String hamlPath, String hamlLayoutPath,
            String outputPath, String outputExtension, String scriptPath,
            String styleSheetPath, boolean recursive) {
        this.hamlPath = hamlPath;
        this.hamlLayoutPath = hamlLayoutPath;
        this.outputPath = outputPath;
        this.outputExtension = outputExtension;
        this.scriptPath = scriptPath;
        this.styleSheetPath = styleSheetPath;
        this.recursive = recursive;
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

    public boolean isRecursive() {
        return recursive;
    }

    public void setRecursive(boolean recursive) {
        this.recursive = recursive;
    }

}
