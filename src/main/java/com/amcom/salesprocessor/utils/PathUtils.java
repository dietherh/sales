package com.amcom.salesprocessor.utils;

import java.io.File;

public class PathUtils {

    public static String getHomePath() {
    	return System.getenv("HOMEPATH");
    }
    
    public static String getInputDir() {
    	mkdir(getHomePath()+"/data/in/");
    	return getHomePath()+"/data/in/";
    }
    
    public static String getOutputDir() {
    	mkdir(getHomePath()+"/data/out/");
    	return getHomePath()+"/data/out/";
    }
    
    public static void mkdir(String path) {
		File fp = new File(path);
		if (!fp.exists()) {
			fp.mkdirs();
		}
	}
    
}
