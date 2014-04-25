/**
 * 
 */
package com.ericsson.uploadpath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * D12128017 Ovidiu Ivan Csaprda
 *
 */
public class CVSparser {

	private String tac="";
	private String accessCapability="";
	private BufferedWriter fileBufferedWriter;
	
	/**
	 * will parse the String token into key/value pairs.<br>
	 * for each pair the <b>public insert(String key, String value);</b><br>
	 * method will be called.
	 * @param token Assumes token in form <b>'103900,"GSM 1800, ..."'<b>
	 * @throws IOException 
	 */
	public void parseTacAccessCapabilityPairs(StringBuilder tokenizeThis, 
			int comaIndex, File fileOut) throws IOException{
		int[] comaIndexes =  new int[2];
		comaIndexes[1]=comaIndex;
		
		if(fileOut!=null){
			//extract token
			comaIndexes[1]=tokenizeThis.indexOf(",", comaIndexes[1]);
			tac=tokenizeThis.substring(comaIndexes[0], comaIndexes[1]);
			tac=tac.trim();
			tokenizeThis.delete(comaIndexes[0], comaIndexes[1]+1);
			if(tokenizeThis.charAt(0)=='"')
				tokenizeThis.deleteCharAt(0);
			if(tokenizeThis.charAt(tokenizeThis.length()-1)=='"')
				tokenizeThis.deleteCharAt(tokenizeThis.length()-1);
			//comaIndexes[0]=comaIndexes[1];
			fileBufferedWriter=new BufferedWriter(new FileWriter(fileOut.getAbsoluteFile()));
            //loop trough accessCapability values and insert pairs into file/db	
			while(tokenizeThis.length()!=0){
				//extract token
				comaIndexes[1]=tokenizeThis.indexOf(",", comaIndexes[1]);
				if(comaIndexes[1]>-1){
					accessCapability=tokenizeThis.substring(comaIndexes[0], comaIndexes[1]);
				tokenizeThis.delete(comaIndexes[0], comaIndexes[1]+1);
				}
				else{
					accessCapability=tokenizeThis.substring(comaIndexes[0], tokenizeThis.length());
					tokenizeThis.delete(comaIndexes[0], tokenizeThis.length());
				}
				accessCapability=accessCapability.trim();
				//comaIndexes[0]=comaIndexes[1];
				//write to file line by line
				fileBufferedWriter.write(tac+","+accessCapability);
				System.out.println("writing [ "+tac+","+accessCapability+" ] to file.");
				fileBufferedWriter.newLine();
	
			}//end while
		}//end if
		
		fileBufferedWriter.close();
	}
}
