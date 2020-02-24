package com.ln.fileoperations;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;


/**
 * A Mojo that helps to create a file with a given input file name, file content and path location of the file.
 * 
 * @author Sandeep Vanam
 *
 */
@Mojo(name = "createFile", defaultPhase = LifecyclePhase.INITIALIZE)
public class CreateFileMojo extends AbstractMojo {

    /**
     * The fileName is used for name of the file to be created
     */
    @Parameter(property = "fileName", defaultValue = "newFile.txt")
    private String fileName;
    
    /**
     * The fileContent used to get the content of the file
     */
    @Parameter(property = "fileContent", defaultValue = "Welcome to File operations plugin ")
    private String fileContent;
    
    /**
     * The filePath used for location of the file where it should be created 
     */
    @Parameter(property = "filePath", defaultValue = "C:\\Users\\sv8986\\Desktop\\")
    private String filePath;
    
    
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("create a file : " + fileName);
        getLog().info("create a file PAth : " + filePath);
        createFile(fileName, fileContent, filePath);
    }

    /**
     * Method used to create a file at specified location with dynamic content.
     * 
     * @param fileName name of the file 
     * @param fileContent Content of the file
     * @param filePath path of file location to be created
     * @return Nothing
     * @exception IOException can occur while creating a file
     * @see IOException
     */
    public void createFile(String fileName, String fileContent, String filePath) {
        String path = filePath + fileName;
        
        File file = new File(path);
        FileWriter fileWriter = null;
        try {
        	if(!file.exists()) {
        		if(file.createNewFile()) {
        			fileWriter = new FileWriter(file);
        			fileWriter.write(fileContent);
        			getLog().info("File has been created successfully: with content  "+ fileContent +" at location " + path);
        		}else {
        			getLog().warn("Error occured while creating file ");
        		}
        	}else {
        		getLog().warn("File already exists at: " + path); 
        	}
        } catch (IOException e) {
            getLog().error("Error creating a file" + e.getMessage());
        }finally {
        	try {
				fileWriter.close();
			} catch (IOException e) {
				getLog().error("Exception occured while closing fileWriter ", e);
			}
        }
    }
}
