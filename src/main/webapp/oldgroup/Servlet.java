package com.ericsson.persistence;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import JXL.WriteExcelToDataBase;

import com.ericsson.files.FileOp;

import entity.Account;
import entity.Enquiry;
import entity.Event;
/**
 * Servlet implementation class TestServlet
 */
@WebServlet(urlPatterns="/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FileOp fileOp=new FileOp();
	private long time=0;
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private Date date;
	private java.sql.Timestamp dateTimepk;
	
	 @PersistenceUnit
	 EntityManagerFactory factory;
	
	 private HttpSession session;
	
	private final String UPLOAD_DIRECTORY = fileOp.getPath().toString()+"/Upload";
	private File downloadedfile;
	
	
    public Servlet() {
        super();
     
        
    }

	/**
	 * This Servlet receive a request from the web front end and use the PsersistenceUtil
	 * class that uses JPA, to connect and retrieve the event and cause code for that given IMSI
	 * and servlet sends a response to the web from that displays it in a table.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String imsiTextBox = request.getParameter("imsiSearch");
		String custSelection = request.getParameter("CustSelection");
		System.out.println(action);
		System.out.println(imsiTextBox);
		String username = null;
		String accounttype  = null;
		int enquiryCount  = 0;
		
		System.out.println(custSelection);
		session = LoginRegServlet.getSession();
	
		if(session != null){
			username = (String) session.getAttribute("username");
			accounttype = (String) session.getAttribute("accounttype");
		}
		
		if("Search IMSI".equalsIgnoreCase(action) && imsiTextBox != null && accounttype.equals("CustRep")
			&& "Event ID & Cause Codes".equalsIgnoreCase(custSelection) && username != null){
			
			List<Event> events = null;
			imsiTextBox = imsiTextBox.trim();
			try {
				long imsi = Long.valueOf(imsiTextBox).longValue();
				events = PersistenceUtil.findEventIdCauseCodeByImsi(imsi);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			Account logUser = PersistenceUtil.findUserAccount(username);
			enquiryCount =  PersistenceUtil.findEnquiryCount(username);
			Enquiry logEnquiry = new Enquiry((enquiryCount++), "Searched for Event ID & Cause Codes for " + imsiTextBox, logUser);	
			PersistenceUtil.persist(logEnquiry);
			
			
			if(events != null){
				for (Event event : events) {
					System.out.println(event.getImsi());
				}
				request.setAttribute("IMSIeventcausecode", events);
				request.getRequestDispatcher("CustServiceRep.jsp").forward(request, response);
			}else{
				response.sendRedirect("CustServiceRep.jsp");
			}
			
			
			
		}else if("Search IMSI".equalsIgnoreCase(action) && imsiTextBox != null && accounttype.equals("CustRep")
				&& "All Unique Cause Codes".equalsIgnoreCase(custSelection) && username != null){
			List<Event> events = null;
			
			try {
				imsiTextBox = imsiTextBox.trim();
				
				long imsi = Long.valueOf(imsiTextBox).longValue();
				events =  (List<Event>) PersistenceUtil.findDistinctEventIdCauseCodeByImsi(imsi);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			Account logUser = PersistenceUtil.findUserAccount(username);
			enquiryCount =  PersistenceUtil.findEnquiryCount(username);
			Enquiry logEnquiry = new Enquiry((enquiryCount++), "Searched for All Unique Cause codes for " + imsiTextBox, logUser);	
			PersistenceUtil.persist(logEnquiry);
			
			if(events != null){
				for (Event event : events) {
					System.out.println(event.getImsi());
				}
				request.setAttribute("IMSIeventcausecode", events);
				request.getRequestDispatcher("CustServiceRep.jsp").forward(request, response);
			}else{
				response.sendRedirect("CustServiceRep.jsp");
			}
		}else if("Search IMSI".equalsIgnoreCase(action) && imsiTextBox != null && accounttype.equals("CustRep")
				&& "3".equalsIgnoreCase(custSelection) && username != null){
			
			long imsi = 0L;
			int countFailure = 0;
			
			try {
				imsiTextBox = imsiTextBox.trim();
				imsi = Long.valueOf(imsiTextBox).longValue();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			
			
			startDate = startDate.replace("T", " ");
			endDate = endDate.replace("T", " ");
			
			startDate = startDate.replace("t", " ");
			endDate = endDate.replace("t", " ");
			
			System.out.println("got in here1");
			Date date1 = null;
			Date date2 = null;
			try {
				date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startDate);
				date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(endDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("date parse error");
			}	
			
			
			
			try {
				countFailure = PersistenceUtil.findCountByImsiAndDate(imsi, date1, date2);
				
				Account logUser = PersistenceUtil.findUserAccount(username);
				enquiryCount =  PersistenceUtil.findEnquiryCount(username);
				Enquiry logEnquiry = new Enquiry((enquiryCount++), "Searched for Count failures for fixed period for " + imsiTextBox, logUser);	
				PersistenceUtil.persist(logEnquiry);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			List<Integer> tempArray = new ArrayList<Integer>();
			tempArray.add(countFailure);
			request.setAttribute("IMSIeventcausecode", tempArray);
			request.getRequestDispatcher("CustServiceRep.jsp").forward(request, response);	
			
			System.out.println(countFailure);
		}
		
	}

	/**
	 * This doPost method gets request to upload a file on to the servlet from the web front end.
	 * downloads the file into load directory so it can be imported into the database
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		try {
			
			boolean ismultipart =  ServletFileUpload.isMultipartContent(request);
			
			if(!ismultipart){
				
				request.setAttribute("notification", "Sorry No File To Upload");
				
			}else{
				 FileItemFactory factory = new DiskFileItemFactory();
				 
				  // Create a new file upload handler
			      ServletFileUpload upload = new ServletFileUpload(factory);
			      
			      try {
			    	// Parse the request to get file items.
				      List<FileItem> fileItems = upload.parseRequest(request);
				      
				      for(FileItem item : fileItems){
				         				    	  			    	  
				    	  if(!item.isFormField()){
		                        String name = FilenameUtils.getName(item.getName());
		                        
		                        System.out.println("saving file to: "+UPLOAD_DIRECTORY);
		                        
		                        new File(UPLOAD_DIRECTORY).mkdirs();
		                        this.downloadedfile = new File(UPLOAD_DIRECTORY + File.separator + name);
		                        if(this.downloadedfile.exists()){
		                        	System.out.println("File exist. Overwriting ...");
		                        	//this.downloadedfile.delete();
		                        	item.write( this.downloadedfile);
		                        }else{
		                        	item.write( this.downloadedfile);
		                        }

		                		Workbook workbook = Workbook.getWorkbook(downloadedfile); 
		                        WriteExcelToDataBase.sendExcelToDatabase(workbook);
		                        this.downloadedfile.delete();
		                        //parse();
		                   }
				      }
				      
				     //File uploaded successfully
			         request.setAttribute("notification", "File Uploaded Successfully");  
				
			      } catch (Exception e) {  
			    	  request.setAttribute("notification", "Sorry No File To Upload");
			      }	      
			}
			
		} catch (Exception e) {
			e.getMessage();
			
		}
		
		
		request.getRequestDispatcher("sysAdmin.jsp").forward(request, response);
		
	}	

}
