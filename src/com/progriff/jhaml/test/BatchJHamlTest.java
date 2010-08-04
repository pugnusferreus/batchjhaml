package com.progriff.jhaml.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.progriff.jhaml.BatchJHaml;
import com.progriff.jhaml.model.Configuration;

import junit.framework.TestCase;

public class BatchJHamlTest extends TestCase {
    private BatchJHaml batchJHaml;

    private final static String CURRENT_PATH = System.getProperty("user.dir");
    private final static String SEPERATOR = 
                                    System.getProperty("file.separator");
    
    private String hamlPath = CURRENT_PATH + SEPERATOR + "unitTest" + SEPERATOR + "hamlTest"; 
    private String hamlLayoutPath = CURRENT_PATH + SEPERATOR + "unitTest" + SEPERATOR + "hamlTest" + SEPERATOR + "layouts"; 
    private String outputPath = CURRENT_PATH + SEPERATOR + "unitTest" + SEPERATOR + "outputTest";
    private String scriptPath = CURRENT_PATH + SEPERATOR + "unitTest" + SEPERATOR + "scriptTest";
    private String styleSheetPath = CURRENT_PATH + SEPERATOR + "unitTest" + SEPERATOR + "styleSheetTest";
    
    @Before
    public void setUp() {
        Configuration configuration = new Configuration(hamlPath,hamlLayoutPath,outputPath,"jsp",scriptPath,styleSheetPath);
        batchJHaml = new BatchJHaml(configuration);
    }
    
    @Test
    public void testGetDefaultLayout() {
        batchJHaml.populateLayouts();
        assertNotNull(batchJHaml.getLayouts().get("application.haml"));
    }
    
    @Test
    public void testGetInvalidLayout() {
        batchJHaml.populateLayouts();
        assertNull(batchJHaml.getLayouts().get("test.haml"));
    }
    
   
    
    @Test
    public void testGetContentOutput() throws IOException {
        File hamlFile = new File(hamlPath + SEPERATOR + "test.haml");
        assertFalse(StringUtils.isBlank(batchJHaml.getHamlOutput(hamlFile)));
        assertFalse(StringUtils.contains(batchJHaml.getHamlOutput(hamlFile),"<html>"));
    }
}
