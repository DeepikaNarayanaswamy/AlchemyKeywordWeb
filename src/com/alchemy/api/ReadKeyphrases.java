package com.alchemy.api;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alchemy.keyword.Keyphrase;

@Path("/keyphrases")
public class ReadKeyphrases {
	
	@GET
	@Path("/MobileKeyPhrases")
	@Produces(MediaType.APPLICATION_JSON)
	public 	Keyphrase getMobileKeyPhrases(){
	
		String fileName = "C:/Users/916786/Desktop/MOBILE_KEYPHRASES.txt";
		
		
		Keyphrase mobileKeyPhrase = new Keyphrase();
		mobileKeyPhrase.setType(AlchemyApiConstants.MOBILE);
		
        // This will reference one line at a time
       
        mobileKeyPhrase.setKeyphrases(getKeyPhrasesFromFile(fileName));
        return mobileKeyPhrase;
    }
	
	@GET
	@Path("/TVKeyPhrases")
	@Produces(MediaType.APPLICATION_JSON)
	public 	Keyphrase getTVKeyPhrases(){
	
		String fileName = "C:/Users/916786/Desktop/TV_KEYPHRASES.txt";
		
		
		Keyphrase mobileKeyPhrase = new Keyphrase();
		mobileKeyPhrase.setType(AlchemyApiConstants.TV);
		
        // This will reference one line at a time
       
        mobileKeyPhrase.setKeyphrases(getKeyPhrasesFromFile(fileName));
        return mobileKeyPhrase;
    }
	
	@GET
	@Path("/AllKeyPhrases")
	@Produces(MediaType.APPLICATION_JSON)
	public 	List<Keyphrase> getAllKeyPhrases(){
	
		List<Keyphrase> allKeyPhrases = new ArrayList<Keyphrase>();
		allKeyPhrases.add(getMobileKeyPhrases());
		allKeyPhrases.add(getTVKeyPhrases());
		 
       
        return allKeyPhrases;
    }
	
	public ArrayList<String>getKeyPhrasesFromFile(String filePath){
		 String line = null;
		 ArrayList<String>keyphrases = new ArrayList<String>();
	        try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = 
	                new FileReader(filePath);

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);

	            while((line = bufferedReader.readLine()) != null) {
	                keyphrases.add(line);
	            }   

	            // Always close files.
	            bufferedReader.close();         
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println(
	                "Unable to open file '" + 
	                		filePath + "'");                
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error reading file '" 
	                + filePath + "'");                  
	            // Or we could just do this: 
	            // ex.printStackTrace();
	        }
	        return keyphrases;
	}
   
        
}
