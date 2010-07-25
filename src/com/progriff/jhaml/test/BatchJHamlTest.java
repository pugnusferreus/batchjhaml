package com.progriff.jhaml.test;

import org.junit.Before;
import org.junit.Test;

import com.progriff.jhaml.BatchJHaml;

import junit.framework.TestCase;

public class BatchJHamlTest extends TestCase {
    private BatchJHaml batchJHaml;

    private final static String CURRENT_PATH = System.getProperty("user.dir");
    private final static String SEPERATOR = 
                                    System.getProperty("file.separator");
    
    @Before
    public void setUp() {
        String hamlPath = CURRENT_PATH + SEPERATOR + "unitTest" + SEPERATOR + "hamlTest";
        String outputPath = CURRENT_PATH + SEPERATOR + "unitTest" + SEPERATOR + "outputTest";
        batchJHaml = new BatchJHaml(hamlPath,outputPath,"jsp");
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
}
