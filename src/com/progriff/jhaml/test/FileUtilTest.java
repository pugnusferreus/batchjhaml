package com.progriff.jhaml.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.progriff.jhaml.util.FileUtil;

public class FileUtilTest {
    @Test
    public void testListFiles_withoutRecursion() {
        List<File> files = FileUtil.listFiles("unitTest/hamlTest",
                FileUtil.getHamlFilenameFilter(), false);
        assertEquals(1, files.size());
    }

    @Test
    public void testListFiles_withRecursion() {
        List<File> files = FileUtil.listFiles("unitTest/hamlTest",
                FileUtil.getHamlFilenameFilter(), true);
        assertEquals(2, files.size());
    }

    @Test
    public void testGetSubDirectory_withoutSubDir() {
        File file = new File("hamlTest/test.haml");
        String subDir = FileUtil.getSubDirectory(file, "hamlTest");
        assertEquals("/", subDir);

    }

    @Test
    public void testGetSubDirectory_withSubDir() {
        File file = new File("hamlTest/blah/test.haml");
        String subDir = FileUtil.getSubDirectory(file, "hamlTest");
        assertEquals("/blah/", subDir);

    }
}
