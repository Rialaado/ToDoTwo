package com.ericsson.restservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.ericsson.entities.Message;
import com.ericsson.uploadpath.FileOp;
import com.google.gson.Gson;

@Stateless
@Path("/message")
public class RestService {

    @PersistenceContext
    private EntityManager em;
    
    private Gson json = new Gson();

    @SuppressWarnings("unchecked")
	@GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getAll() {
    	
    	
        Query q = em.createNativeQuery("SELECT * FROM Message", Message.class);
        List<Message> result = q.getResultList();
        
        for(Message re: result){
        	System.out.println(re.getTitle() + re.getBody());
        }
        
        json.toJson(result);
        
        return result;
    }

    @SuppressWarnings("unchecked")
	@GET
    @Path("/gt/{id}")
    public List<Message> getFromId(@PathParam("id") String id) {
        Query q = em.createNativeQuery("select * from Message where id > :id", Message.class);
        q.setParameter("id", id);
        List<Message> result = q.getResultList();
        json.toJson(result);
        return result;
    }



    @GET
    @Path("mock-insert")
    public void moskInsert() {
        Message message = new Message();
        message.setTitle("Titlehello" + System.currentTimeMillis());
        message.setBody("The we yeees okkayay team.");
        em.persist(message);
        
    }
    
    
	@POST
	@Path("/uploadfile")
	@Consumes("multipart/form-data")
	public Response uploadFile(MultipartFormDataInput input) {
 
		String UPLOADED_FILE_PATH = "/home/ivo/Documents/";
		
		/*
		 FileOp fileOp = new FileOp();
		 String UPLOAD_DIRECTORY = fileOp.getPath().toString()+ File.separator +"Upload" + File.separator;*/
		
		String fileName = "";
 
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		List<InputPart> inputParts = uploadForm.get("file");
 
		for (InputPart inputPart : inputParts) {
 
		 try {
 
			MultivaluedMap<String, String> header = inputPart.getHeaders();
			
			fileName = getFileName(header);
 
			//convert the uploaded file to inputstream
			InputStream inputStream = inputPart.getBody(InputStream.class,null);
 
			byte [] bytes = IOUtils.toByteArray(inputStream);
 
			//constructs upload file path
			fileName = UPLOADED_FILE_PATH + fileName;
			
			File file = new File(fileName);
			file.setReadable(true, false);
			file.setWritable(true, false);
			file.setExecutable(true, false);
			
	 
			if (!file.exists()) {
				file.createNewFile();
			}
	 
			FileOutputStream fileOutputStream = new FileOutputStream(file);
	 
			fileOutputStream.write(bytes);
			fileOutputStream.flush();
			fileOutputStream.close();
			
			
 
			System.out.println("Done");
 
		  } catch (IOException e) {
			e.printStackTrace();
		  }
 
		}
 
		return Response.status(200).entity(fileName.subSequence(fileName.lastIndexOf("/")-1 , fileName.length()-1) + " was Sucessfull Uploaded").build();
	}
 
	
	
	/**
	 * header sample
	 * {
	 * 	Content-Type=[excelfile.xls], 
	 * 	Content-Disposition=[form-data; name="file"; filename="filename.extension"]
	 * }
	 **/
	//get uploaded filename, is there a easy way in RESTEasy?
	private String getFileName(MultivaluedMap<String, String> header) {
 
		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");
 
		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {
				String[] name = filename.split("=");
 
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}
 

    

}
