/**
 * 
 */
package com.ericsson.uploadpath;

/**
 * @author D12128017
 *
 */
public class TestCVSparser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileOp fo = new FileOp();
		CVSparser cvsParser = new CVSparser();
		fo.setFiles();
		fo.readFile(cvsParser);

	}

}
