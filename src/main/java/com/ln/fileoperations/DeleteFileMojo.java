package com.ln.fileoperations;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * A Mojo that helps delete a file. It takes file name and file path dynamically
 * 
 * Sandeep Vanam
 * 
 */
@Mojo(name = "deleteFile", defaultPhase = LifecyclePhase.INITIALIZE)
public class DeleteFileMojo extends AbstractMojo {

    /**
     * The fileName is used for name of the file to be deleted
     */
    @Parameter(property = "fileName", defaultValue = "newFile.txt")
    private String fileName;
    
    /**
     * The filePath is used to delete the file at specified path location
     */
    @Parameter(property = "filePath", defaultValue = "C:\\Users\\sv8986\\Desktop\\")
    private String filePath;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Delete file Name is : " + fileName);
        deleteFile(fileName, filePath);
    }

    /**
     * Method to delete the file at specified location
     * 
     * @param fileName name of the file to be deleted
     * @param filePath path location for file
     * @return Nothing
     */
    public void deleteFile(String fileName, String filePath) {
    	String path = filePath + fileName;
    	File file = new File(path);
    	if (file.exists()) {
    		if(file.delete()) {
    			getLog().info("File has been deleted successfully. at: " + path);
    		}else {
    			getLog().info("Not able to delete the file. at: " + path);
    		}
    	} else {
    		getLog().warn("File does not exist at: " + path);
    	}
    }
}
