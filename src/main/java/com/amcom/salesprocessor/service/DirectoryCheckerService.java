package com.amcom.salesprocessor.service;
import java.io.File;
import java.io.FileFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amcom.salesprocessor.utils.PathUtils;  

@Service
public class DirectoryCheckerService {
  
    @Autowired
    private ProcessFileService processFileService;
  
    public void verifyDirectory() {  
        File dir = new File(PathUtils.getInputDir());

        File files[] = dir.listFiles(new FileFilter() {  
            public boolean accept(File pathname) {  
                return pathname.getName().toLowerCase().endsWith(".dat");  
            }  
        });  
  
        for (int i = 0; i < files.length; i++) {  
            File file = files[i];  
            newFile(file);  
        }  
    }  
  
    public void newFile(File file) {  
    	processFileService.processFile(file);
    }  
  
} 
