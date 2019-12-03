package com.amcom.salesprocessor.service;
 
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amcom.salesprocessor.utils.PathUtils;  

@Service
public class MonitoringService {
	
	@Autowired
    private DirectoryCheckerService directoryCheckerService;
	 
    public void monitor() {
    	try {
	        WatchService watchService = FileSystems.getDefault().newWatchService();
	 
	        Path path = Paths.get(PathUtils.getInputDir());
	 
	        path.register(
	          watchService, 
	            StandardWatchEventKinds.ENTRY_CREATE, 
	              StandardWatchEventKinds.ENTRY_DELETE, 
	                StandardWatchEventKinds.ENTRY_MODIFY);
	 
	        WatchKey key;
	        while ((key = watchService.take()) != null) {
	            for (@SuppressWarnings("unused") WatchEvent<?> event : key.pollEvents()) {
	            	directoryCheckerService.verifyDirectory();
	            }
	            key.reset();
	        }
    	} catch (Exception e) {
    		e.printStackTrace();
		}
    }
}