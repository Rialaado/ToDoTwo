/**
 * 
 */
package com.ericsson.uploadpath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;



/**
 * @author D12128017 Ovidiu Ivan Csaprda
 *
 */
public class FileOp {

	//get formated path as string
	private String url=generatePath();
	private File path = new File(url);
	private File myFileIn,myFileOut;
	private final String UE_AC_FILENAME="UE_AC Raw.csv";
	private StringBuilder tokenizeThis = new StringBuilder(30); 
	
	
	//method to "instantiate" text files
	public void setFiles(){
		myFileIn = new File(path,"UE_AC Raw.csv");
	}//end setFiles()
	
	//method to "instantiate" text files
	public void setFiles(File downloadedfile) throws IOException{
		myFileIn = downloadedfile;
		if (downloadedfile.getName().equals(UE_AC_FILENAME)){
			String path=(myFileIn.toString()).substring(
					0,myFileIn.toString().lastIndexOf("/"));
			path=path+"/tmp"+UE_AC_FILENAME;
				myFileOut=new File(path);
				myFileOut.createNewFile();
		}
		else myFileOut=null;
	}//end setFiles()
	
	//method to read from text file
	public void readFile(CVSparser cvsParser){
		
		long startTime = System.nanoTime();
		
		//check if the file exists
		if(!myFileIn.exists()){
			System.out.println("file doesn't exists");
			
		}else{
			//load data from file
			System.out.println("file exists");
			//process text file
			BufferedReader myFileBuffer=null;
			try {
				myFileBuffer=new BufferedReader(new FileReader(myFileIn));
				int comaIndex=0;
				//read file line by line
				while( (tokenizeThis.append(myFileBuffer.readLine())).length()!=0){
					//extract token
					comaIndex=tokenizeThis.indexOf(",", 0);
					
					cvsParser.parseTacAccessCapabilityPairs(tokenizeThis, comaIndex, myFileOut);
				}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
				//close fileBuffer
				if(myFileBuffer!=null)
					try {
						myFileBuffer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}//end inner try catch block
			}//end try catch finally block
		}
		//code
				long endTime = System.nanoTime();
				System.out.println("Took "+(endTime - startTime) + " ns");
	}//end readFile()
		
	/**
	 * method to generate absolute path of this class
	 * @return path String value in form <b>path = path.substring(0,path.lastIndexOf("/"));</b>
	 */
	public String generatePath(){
		String path="";
		
		java.io.File file =new File("");
		try {
			file =new File(FileOp.class.getProtectionDomain().
					getCodeSource().getLocation().toURI().getPath());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	//	path="/"+file+"/fileOp/";
		path="/"+file;
		path = path.substring(0,path.lastIndexOf("/"));
		return path;
	}//end public String getPath()

	
	public File getPath() {
		return path;
	}

	public void setPath(File path) {
		this.path = path;
	}
		
	
}//end class FileOp
