package com.progriff.jhaml.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.progriff.jhaml.BatchJHaml;

import junit.framework.TestCase;

public class BatchJHamlTest extends TestCase {
    private BatchJHaml batchJHaml;

    private final static String CURRENT_PATH = System.getProperty("user.dir");
    private final static String SEPERATOR = 
                                    System.getProperty("file.separator");
    
    private String hamlPath = CURRENT_PATH + SEPERATOR + "unitTest" + SEPERATOR + "hamlTest"; 
    private String hamlLayoutPath = CURRENT_PATH + SEPERATOR + "unitTest" + SEPERATOR + "hamlTest" + SEPERATOR + "layouts"; 
    private String outputPath = CURRENT_PATH + SEPERATOR + "unitTest" + SEPERATOR + "outputTest";
    
    @Before
    public void setUp() {
        batchJHaml = new BatchJHaml(hamlPath,hamlLayoutPath,outputPath,"jsp");
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
    public void testGetFolderWithNoLayout() {
        batchJHaml.setHamlLayoutPath(outputPath);
        batchJHaml.populateLayouts();
        assertTrue(batchJHaml.getLayouts().size() < 1);
        batchJHaml.setHamlPath(hamlLayoutPath);
    }
    
    @Test
    public void testGetInvalidLayoutFolder() {
        try {
            batchJHaml.setHamlLayoutPath("sdfsdf");
            batchJHaml.populateLayouts();
            assertTrue(false);
        }
        catch(Exception e) {
            assertTrue(true);
        } finally {
            batchJHaml.setHamlLayoutPath(hamlLayoutPath);
        }
        
    }
    
    @Test
    public void testGetContentOutput() throws IOException {
        File hamlFile = new File(hamlPath + SEPERATOR + "test.haml");
        assertFalse(StringUtils.isBlank(batchJHaml.getHamlOutput(hamlFile)));
        assertFalse(StringUtils.contains(batchJHaml.getHamlOutput(hamlFile),"<html>"));
    }
}
