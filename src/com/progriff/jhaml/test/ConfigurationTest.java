package com.progriff.jhaml.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.progriff.jhaml.model.Configuration;

public class ConfigurationTest {

    @Test
    public void testConfiguration_withArray() {
        String[] args = { "test1", "test2", "test3", "test4", "test5", "test6",
                "true" };
        Configuration conf = new Configuration(args);

        assertEquals("test1", conf.getHamlPath());
        assertEquals("test2", conf.getHamlLayoutPath());
        assertEquals("test3", conf.getOutputPath());
        assertEquals("test4", conf.getOutputExtension());
        assertEquals("test5", conf.getScriptPath());
        assertEquals("test6", conf.getStyleSheetPath());
        assertEquals(true, conf.isRecursive());

        String[] args1 = { "test1", "test2", "test3", "test4", "test5",
                "test6", "false" };
        conf = new Configuration(args1);

        assertEquals("test1", conf.getHamlPath());
        assertEquals("test2", conf.getHamlLayoutPath());
        assertEquals("test3", conf.getOutputPath());
        assertEquals("test4", conf.getOutputExtension());
        assertEquals("test5", conf.getScriptPath());
        assertEquals("test6", conf.getStyleSheetPath());
        assertEquals(false, conf.isRecursive());
    }

    @Test
    public void testConfiguration_withArrayWithoutRecursiveArg() {
        String[] args = { "test1", "test2", "test3", "test4", "test5", "test6" };
        Configuration conf = new Configuration(args);

        assertEquals("test1", conf.getHamlPath());
        assertEquals("test2", conf.getHamlLayoutPath());
        assertEquals("test3", conf.getOutputPath());
        assertEquals("test4", conf.getOutputExtension());
        assertEquals("test5", conf.getScriptPath());
        assertEquals("test6", conf.getStyleSheetPath());
        assertEquals(false, conf.isRecursive());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConfiguration_withNotEnoughArg() {
        String[] args = { "test1", "test2", "test3" };
        Configuration conf = new Configuration(args);

        assertEquals("test1", conf.getHamlPath());
        assertEquals("test2", conf.getHamlLayoutPath());
        assertEquals("test3", conf.getOutputPath());
        assertEquals("test4", conf.getOutputExtension());
        assertEquals("test5", conf.getScriptPath());
        assertEquals("test6", conf.getStyleSheetPath());
        assertEquals(false, conf.isRecursive());
    }

    @Test
    public void testConfiguration() {
        Configuration conf = new Configuration("test1", "test2", "test3",
                "test4", "test5", "test6", true);

        assertEquals("test1", conf.getHamlPath());
        assertEquals("test2", conf.getHamlLayoutPath());
        assertEquals("test3", conf.getOutputPath());
        assertEquals("test4", conf.getOutputExtension());
        assertEquals("test5", conf.getScriptPath());
        assertEquals("test6", conf.getStyleSheetPath());
        assertEquals(true, conf.isRecursive());
    }
}
