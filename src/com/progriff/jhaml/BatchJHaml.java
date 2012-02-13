package com.progriff.jhaml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang.StringUtils;

import com.cadrlife.jhaml.JHaml;
import com.progriff.jhaml.model.Configuration;
import com.progriff.jhaml.util.FileUtil;
import com.progriff.jhaml.util.MarkUpUtil;

/**
 * <p>
 * Creates JSP or HTML in batch ala Staticmatic style.
 * </p>
 * 
 * @author Benson Lim
 */
public class BatchJHaml {

    private Configuration configuration;

    /**
     * The current path where the application will run
     */
    private final static String CURRENT_PATH = System.getProperty("user.dir");
    /**
     * File seperator.
     */
    private final static String SEPERATOR = System
            .getProperty("file.separator");
    /**
     * Haml can have multiple layouts. This map is to store them. BatchJHaml
     * only supports application.haml at the moment
     */
    private HashMap<String, String> layouts = new HashMap<String, String>();

    /**
     * Default constructor.
     */
    public BatchJHaml() {
        this.configuration = new Configuration(CURRENT_PATH + SEPERATOR
                + "haml", CURRENT_PATH + SEPERATOR + "haml" + SEPERATOR
                + "layouts", CURRENT_PATH + SEPERATOR + "output", "jsp",
                CURRENT_PATH + SEPERATOR + "scripts", CURRENT_PATH + SEPERATOR
                        + "stylesheets", false);
    }

    /**
     * Hate default values? Call this one
     * 
     * @param hamlPath
     *            Path to your Haml folder
     * @param outputPath
     *            Path to output
     * @param outputExtension
     *            The name of the extension to append
     */
    public BatchJHaml(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * If you are not generating via ant run , you can call this function from
     * your app.
     */
    public void generateOutput() {
        File file = new File(configuration.getHamlPath());
        populateLayouts();
        Collection<File> found = FileUtils.listFiles(
                new File(configuration.getHamlPath()), TrueFileFilter.INSTANCE,
                TrueFileFilter.INSTANCE);

        for (File x : found) {
            System.out.println(x);
        }

        for (File hamlFile : file.listFiles(FileUtil.getHamlFilenameFilter())) {
            try {
                String hamlOutput = getHamlOutput(hamlFile);
                File outputFile = new File(configuration.getOutputPath()
                        + SEPERATOR
                        + StringUtils.replace(hamlFile.getName(), ".haml", "."
                                + configuration.getOutputExtension()));

                writeToFile(outputFile, hamlOutput);
            } catch (Exception e) {
                System.err.println("Unable to convert haml file : "
                        + hamlFile.getName());
                e.printStackTrace();
            }
        }
    }

    /**
     * Writes the output to the output folder. Replaces <%= yield %> to use the
     * content markup
     * 
     * @param outputFile
     *            File object to output
     * @param hamlOutput
     *            The hamlOutput from JHaml
     * @throws IOException
     *             if unable to write to the folder for some reason
     */
    public void writeToFile(File outputFile, String hamlOutput)
            throws Exception {

        String layout = MarkUpUtil.getLayoutName(hamlOutput);

        if (layout == null)
            layout = this.layouts.get("application.haml");
        else
            layout = this.layouts.get(layout + ".haml");

        FileWriter fstream = new FileWriter(outputFile);
        BufferedWriter out = new BufferedWriter(fstream);

        for (String layoutLine : layout.split("\n")) {
            if (!layoutLine.contains("<%= yield %>")) {
                if (layoutLine.contains("<%= javascripts")) {
                    // add javascript tags
                    List<String> scriptNames = MarkUpUtil
                            .getScriptNames(layoutLine);

                    if (scriptNames != null) {
                        for (String scriptName : scriptNames) {
                            out.write("    <script language='javascript' src='"
                                    + configuration.getScriptPath() + "/"
                                    + scriptName
                                    + ".js' type='text/javascript'></script>\n");
                        }
                    }

                    scriptNames = MarkUpUtil
                            .getIndividualFileScripts(hamlOutput);

                    if (scriptNames != null) {
                        for (String scriptName : scriptNames) {
                            out.write("    <script language='javascript' src='"
                                    + configuration.getScriptPath()
                                    + "/"
                                    + scriptName
                                    + ".js' type='text/javascript' ></script>\n");
                        }
                    }
                } else if (layoutLine.contains("<%= stylesheets")) {
                    // add style sheet tags
                    List<String> styleSheetNames = MarkUpUtil
                            .getStyleSheetNames(layoutLine);
                    if (styleSheetNames != null) {
                        for (String styleSheetName : styleSheetNames) {
                            out.write("    <link rel='stylesheet' media='all' type='text/css' href='"
                                    + configuration.getStyleSheetPath()
                                    + "/"
                                    + styleSheetName + ".css' />\n");
                        }
                    }

                    styleSheetNames = MarkUpUtil
                            .getIndividualFileStyleSheets(hamlOutput);

                    if (styleSheetNames != null) {
                        for (String styleSheetName : styleSheetNames) {
                            out.write("    <link rel='stylesheet' media='all' type='text/css' href='"
                                    + configuration.getStyleSheetPath()
                                    + "/"
                                    + styleSheetName + ".css' />\n");
                        }
                    }

                } else {
                    out.write(layoutLine + "\n");
                }
            } else {
                String frontSpace = layoutLine.split("<%=")[0];

                for (String contentLine : hamlOutput.split("\n")) {
                    if (contentLine.contains("@layout")) {

                    } else if (contentLine.contains("@javascripts")) {

                    } else if (contentLine.contains("@stylesheets")) {

                    } else {
                        out.write(frontSpace + "" + contentLine + "\n");
                    }
                }
            }
        }
        out.close();
    }

    /**
     * Iterates the layouts in the haml/layouts folder and stores the processed
     * MarkUp into a HashMap. The key is the filename.
     */
    public void populateLayouts() {
        JHaml jhaml = new JHaml();
        this.layouts.clear();
        File file = new File(configuration.getHamlLayoutPath());
        for (File layoutFile : file.listFiles(FileUtil.getHamlFilenameFilter())) {
            try {
                this.layouts.put(layoutFile.getName(),
                        jhaml.parse(FileUtils.readFileToString(layoutFile)));
            } catch (Exception e) {
                System.err.println("Unable to convert layout : "
                        + layoutFile.getName());
                e.printStackTrace();
            }
        }

    }

    /**
     * Gets the XHTML Output based on the hamlFile given
     * 
     * @param hamlFile
     *            the haml file object
     * @return JSP String
     * @throws IOException
     *             if unable to read the file.
     */
    public String getHamlOutput(File hamlFile) throws IOException {
        JHaml jhaml = new JHaml();
        return jhaml.parse(FileUtils.readFileToString(hamlFile));
    }

    public HashMap<String, String> getLayouts() {
        return layouts;
    }

    public void setLayouts(HashMap<String, String> layouts) {
        this.layouts = layouts;
    }

    public static void main(String[] args) {

        BatchJHaml batch;

        if (args.length < 1) {
            batch = new BatchJHaml();
        } else {
            try {
                Configuration configuration = new Configuration(args);
                batch = new BatchJHaml(configuration);
            } catch (Exception e) {
                e.printStackTrace();
                System.err
                        .println("Usage: BatchJHaml <hamlPath> <hamlLayoutPath> <outputPath> <outputExtension> <scriptPath> <styleSheetPath>");

                return;
            }
        }

        batch.generateOutput();
        System.out.println("File Generation Completed!");

    }
}
