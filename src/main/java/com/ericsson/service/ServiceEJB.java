package com.ericsson.service;



import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.persistence.NoResultException;

import com.ericsson.dao.UserDAO;
import com.ericsson.dao.ItemDAO;
import com.ericsson.entities.User;
import com.ericsson.entities.Item;
import com.ericsson.interfaces.IUserDAO;
import com.ericsson.interfaces.IItemDAO;




@Stateless
@WebService (endpointInterface= "com.ericsson.service.IServiceEJB")
@Local(IServiceEJB.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ServiceEJB implements IServiceEJB {
	
	@Inject
	private IUserDAO userDAO;
	
	@Inject
	private IItemDAO itemDAO;
	
	
	
	@Override
	public String addNewUser(User entity) {
		String userExist = null;
		try {
			userExist = userDAO.addUser(entity);
		} catch (Exception e) {
			userExist = null;
		}
		System.out.println("in serviceEJB with userExist: "+userExist);
		return userExist;
	}

	
	public List<Item> retrieveAllItems()
	{
		List<Item> items = null;
		try {
			
			items = itemDAO.findAllItems();
			
		} catch (Exception e) {
			System.out.println("***************service dao failled***************");
			items = null;
		}
		
		return items;
	}
		
//	@Override
//	public String addNewUser(User entity) {
//		String userExist = null;
//		try {
//			userExist = userDAO.addUser(entity);
//		} catch (Exception e) {
//			userExist = null;
//		}
//		System.out.println("in serviceEJB with userExist: "+userExist);
//		return userExist;
//	}
	
	@Override
	public User searchforAccountByUsername(String username){
		User user = null;
		
		try {
			System.out.println(username +"----");
			user = userDAO.findUserAccount(username);
		} catch (Exception e) {
			System.out.println("servicedao search for user error");
			user = null;
		}
		
		return user;
		
	}
	
	
	@Override
	public String addNewitem(Item item){
		String itemSuccess = null;
		try{
			itemSuccess = itemDAO.addItem(item);
		}catch (Exception e){
			itemSuccess = null;
		}
		return itemSuccess;
	}
	
	@Override
	public User searchforAccountUsernameAndPassword(String username, String password){
		
		User user = null;
		
		try {
			System.out.println(username +"----"+ password);
			user = userDAO.findUserAccountUsernameAndPassword(username, password);
		} catch (Exception e) {
			System.out.println("servicedao search for account error");
			user = null;
		}
		
		return user;
		
	}

	@Override   
	public List<Item> searchforUserItems(String accountName){
		List<Item> items = null;
		
		try{
			items = itemDAO.findAllItemsForUser(accountName);
		}
		catch(Exception e){
			System.out.println("servicedao search for account error");
			items = null;
		}
		return items;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public List<Event> searchImsisAffectedByFailureClass(String failureClass, String username){
//		List<Event> events = null;
//		
//		try {
//			String failString = failureClass.trim();
//			
//			events = eventDAO.findImsisByFailureClass(failureClass);
//			
//		} catch (Exception e) {
//			System.out.println("***************service dao failled***************");
//			events = null;
//		}
//		
//		
//		try {
//			int enquiryCount = 0;
//			try {
//				enquiryCount =  enquiryDAO.countUserEnquiries(username);
//			} catch (NoResultException e) {
//				enquiryCount = 0;
//			}
//			Account logUser = accountDAO.findUserAccount(username);
//			System.out.println("searched for equiry name: " + logUser.getName());
//			Enquiry logEnquiry = new Enquiry((enquiryCount++), "Searched for IMSI affected by Failure Class " + failureClass, logUser, new Date());	
//			enquiryDAO.addEnquiry(logEnquiry);
//		} catch (Exception e) {
//			System.out.println("Equiry was not persisted");
//		}
//		
//		return events;
//	}
//	
//
//	
//	public List<EventErrorTb> retrieveAllErrorEvents()
//	{
//		List<EventErrorTb> events = null;
//		
//	
//		try {
//			
//			events = eventErrorDAO.findAllEventErrors();
//			
//		} catch (Exception e) {
//			System.out.println("***************service dao failled***************");
//			events = null;
//		}
//		
//		return events;
//	}
//	
//
//	@Override
//	public List<Event> searchforEventIdCauseCodeByImsi(String imsi, String username, String accountType) {
//		List<Event> events = null;
//		
//		System.out.println("eventimsi "+ imsi);
//		try {
//			String imsiString = imsi.trim();
//			Long imsiNumber = Long.valueOf(imsiString).longValue();
//			events = eventDAO.findEventIdCauseCodeByImsi(imsiNumber);
//			
//		} catch (Exception e) {
//			System.out.println("***************service dao failled***************");
//			events = null;
//		}
//		
//		try {
//			int enquiryCount = 0;
//			try {
//				enquiryCount =  enquiryDAO.countUserEnquiries(username);
//			} catch (NoResultException e) {
//				enquiryCount = 0;
//			}
//			Account logUser = accountDAO.findUserAccount(username);
//			System.out.println("searched for equiry name: " + logUser.getName());
//			Enquiry logEnquiry = new Enquiry((enquiryCount++), "Searched for Event ID & Cause Codes for " + imsi, logUser, new Date());	
//			enquiryDAO.addEnquiry(logEnquiry);
//		} catch (Exception e) {
//			System.out.println("Equiry was not persisted");
//		}
//		
//		
//		return events;
//	}
//
//
//	@Override
//	public List<Event> searchforDistinctEventIdCauseCodeByImsi(String imsi, String username, String accountType) {
//		List<Event> events = null;
//			
//		try {
//			String imsiString = imsi.trim();
//			Long imsiNumber = Long.valueOf(imsiString).longValue();
//			events =  (List<Event>) eventDAO.findDistinctEventIdCauseCodeByImsi(imsiNumber);
//			
//			
//		} catch (Exception e) {
//			events = null;
//		}
//		
//		try {
//			int enquiryCount = 0;
//			try {
//				enquiryCount =  enquiryDAO.countUserEnquiries(username);
//			} catch (NoResultException e) {
//				enquiryCount = 0;
//			}
//			Account logUser = accountDAO.findUserAccount(username);
//			System.out.println("searched for equiry name: " + logUser.getName());
//			
//			Enquiry logEnquiry = new Enquiry((enquiryCount++), "Searched for Unique EventID and Cause codes for " + imsi, logUser, new Date());	
//			enquiryDAO.addEnquiry(logEnquiry);
//		} catch (Exception e) {
//			System.out.println("Equiry was not persisted");
//		}
//		return events;
//	}
//
//
//	@SuppressWarnings("null")
//	@Override
//	public List<Event> searchforCountByImsiAndDate(String imsi, String startdatetime, String enddatetime,  String username, String accountType) {
//	
//		List<Event> events = null;
//		String startDate = startdatetime;
//		String endDate = enddatetime;
//			
//		startDate = startDate.replace("T", " ");	
//		startDate = startDate.replace("t", " ");
//		endDate = endDate.replace("T", " ");
//		endDate = endDate.replace("t", " ");
//		
//		System.out.println("got in here1");
//		Date date1 = null;
//		Date date2 = null;
//		Long imsiNumber = 0L;
//		try {
//			String imsiString = imsi.trim();
//			imsiNumber = Long.valueOf(imsiString).longValue();
//			date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startDate);
//			date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(endDate);
//
//		} catch (ParseException e) {
//			System.out.println("date parse error");
//		}
//		
//		try {
//			events = eventDAO.findCountByImsiAndDate(imsiNumber, date1, date2);
//		} catch (Exception e) {
//		
//		}
//		
//		try {
//			int enquiryCount = 0;
//			try {
//				enquiryCount =  enquiryDAO.countUserEnquiries(username);
//			} catch (NoResultException e) {
//				enquiryCount = 0;
//			}
//			Account logUser = accountDAO.findUserAccount(username);
//			System.out.println("searched for equiry name: " + logUser.getName());
//			Enquiry logEnquiry = new Enquiry((enquiryCount++), "Searched for Count failures for fixed period for " + imsi, logUser, new Date());	
//			enquiryDAO.addEnquiry(logEnquiry);
//		} catch (Exception e) {
//			System.out.println("Equiry was not persisted");
//		}
//		
//		return events;	
//	}
//	
//
//	@Override
//	public List<Event> searchForFailuresByModelAndDate(String model, String startdatetime, String enddatetime, String username) {
//		
//		String startDate = startdatetime;
//		String endDate = enddatetime;
//		List<Event> events = null; 
//			
//		startDate = startDate.replace("T", " ");	
//		startDate = startDate.replace("t", " ");
//		endDate = endDate.replace("T", " ");
//		endDate = endDate.replace("t", " ");
//		
//		Date date1 = null;
//		Date date2 = null;
//		String modelString = model.trim();
//		Ue ue = ueDAO.findUeByModel(modelString);
//	
//		try {
//			
//			date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startDate);
//			date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(endDate);		
//		} catch (ParseException e) {
//			System.out.println("date parse error");
//		}
//		
//		try {
//			events = eventDAO.findNumberEventsByModelAndDate(ue.getTac(),date1,date2);
//			
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		
//		
//		try {
//			int enquiryCount = 0;
//			try {
//				enquiryCount =  enquiryDAO.countUserEnquiries(username);
//			} catch (NoResultException e) {
//				enquiryCount = 0;
//			}
//			Account logUser = accountDAO.findUserAccount(username);
//			System.out.println("searched for equiry name: " + logUser.getName());
//			Enquiry logEnquiry = new Enquiry((enquiryCount++), "Searched for Failure for Model between " + date1 +" to " + date2, logUser, new Date());	
//			enquiryDAO.addEnquiry(logEnquiry);
//		} catch (Exception e) {
//			System.out.println("Equiry was not persisted");
//		}
//		
//		return events;	
//	}
//	
//	@Override
//	public List<Long> searchforFailuresByImsiAndDate(String startdatetime, String enddatetime , String username) {
//		
//		System.out.println("got to ejb imsis");
//		String startDate = startdatetime;
//		String endDate = enddatetime;
//		List<Long> imsis = null;
//			
//		startDate = startDate.replace("T", " ");	
//		startDate = startDate.replace("t", " ");
//		endDate = endDate.replace("T", " ");
//		endDate = endDate.replace("t", " ");
//		
//		Date date1 = null;
//		Date date2 = null;
//		Long imsiNumber = 0L;
//		try {
//			date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startDate);
//			date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(endDate);		
//		} catch (ParseException e) {
//			System.out.println("date parse error");
//			System.out.println("date 1 is "+date1);
//			System.out.println("date 2 is "+date2);
//		}
//		
//		try {
//			imsis = eventDAO.findFailuresByImsiBetweenDates(date1, date2);
//			//countFailure.setValue(count);
//			
//		} catch (Exception e) {
//		}
//		
//		
//		try {
//			int enquiryCount = 0;
//			try {
//				enquiryCount =  enquiryDAO.countUserEnquiries(username);
//			} catch (NoResultException e) {
//				enquiryCount = 0;
//			}
//			Account logUser = accountDAO.findUserAccount(username);
//			System.out.println("searched for equiry name: " + logUser.getName());
//			Enquiry logEnquiry = new Enquiry((enquiryCount++), "Searched for Failure for IMSIs between " + date1 +" to " + date2, logUser, new Date());	
//			enquiryDAO.addEnquiry(logEnquiry);
//		} catch (Exception e) {
//			System.out.println("Equiry was not persisted");
//		}
//		
//		return imsis;	
//	}	
//	
//
//	public List<Enquiry> searchforEnquiriesTypeForUser(String username, String accountType){
//		List<Enquiry> history = null;
//		
//		try {
//			 history = enquiryDAO.findEnquiriesTypeForUser(username, accountType);
//		} catch (Exception e) {
//			history = null;
//			System.out.println("service dao enquiry threw exception");
//		}
//		
//		return history;
//		
//	}
//	
//public List<Event> searchForCauseCodeEventIDByModel(String model) {
//		
//		List<Event> events = null; 
//		
//		String modelString = model.trim();
//		Ue ue = ueDAO.findUeByModel(modelString);
//		
//		try {
//			events = eventDAO.findNumberEventsCauseCodeByModel(ue.getTac());
//			
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		
//		return events;	
//	}
//
//public List<BigInteger> searchCountCauseCodeOccurancesByModel(String model)
//{
//	List<BigInteger> results = null;
//	String modelString = model.trim();
//	Ue ue = ueDAO.findUeByModel(modelString);
//	try {
//		results = eventDAO.findCountCauseCodeOccurancesByModel(ue.getTac());
//		
//	} catch (Exception e)
//	{
//		e.printStackTrace();
//	}
//	
//	return results;	
//}
//
////////////////////////////////////////////////////////////////////////////////////
//
//public List<Event> searchForMarketOperatorCellIDCombo() {
//	
//	List<Event> events = null; 
//	
//	try {
//		events = eventDAO.findMarketOperatorCellIDCombo();
//		
//	} catch (Exception e)
//	{
//		e.printStackTrace();
//	}
//	
//	return events;	
//}
//
//public List<BigInteger> searchCountMarketOperatorCellIDcombo()
//{
//	List<BigInteger> results = null;
//
//	try {
//		results = eventDAO.findCountMarketOperatorCellIDcombo();
//		
//	} catch (Exception e)
//	{
//		e.printStackTrace();
//	}
//	
//	return results;	
//}
/////////////////////////////////////////////////////////////////////////////////////	
//
//
//@SuppressWarnings("null") ///Mineeee
//@Override
//public List<Long> searchforCountFailuresByImsiAndDate(String startdatetime, String enddatetime) {
//	
//	System.out.println("got to ejb count");
//	String startDate = startdatetime;
//	String endDate = enddatetime;
//	List<Long> occurrences = null;
//		
//	startDate = startDate.replace("T", " ");	
//	startDate = startDate.replace("t", " ");
//	endDate = endDate.replace("T", " ");
//	endDate = endDate.replace("t", " ");
//	
//	System.out.println("Got in here1aa");
//	Date date1 = null;
//	Date date2 = null;
//	Long imsiNumber = 0L;
//	try {
//		date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startDate);
//		date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(endDate);		
//	} catch (ParseException e) {
//		System.out.println("date parse error");
//	}
//	
//	try {
//		occurrences = eventDAO.findCountFailuresByImsiBetweenDates(date1, date2);
//		//countFailure.setValue(count);
//		
//	} catch (Exception e) {
//	}
//	
//	return occurrences;	
//}	
////searchforDurationFailuresByImsiAndDate
//
//
//@SuppressWarnings("null") ///Mineeee
//@Override
//public List<Long> searchforDurationFailuresByImsiAndDate(String startdatetime, String enddatetime) {
//	
//	System.out.println("got to ejb duration");
//	String startDate = startdatetime;
//	String endDate = enddatetime;
//	List<Long> durations = null;
//		
//	startDate = startDate.replace("T", " ");	
//	startDate = startDate.replace("t", " ");
//	endDate = endDate.replace("T", " ");
//	endDate = endDate.replace("t", " ");
//	
//	System.out.println("Got in here1aa");
//	Date date1 = null;
//	Date date2 = null;
//	Long imsiNumber = 0L;
//	try {
//		date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startDate);
//		date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(endDate);		
//	} catch (ParseException e) {
//		System.out.println("date parse error");
//	}
//	
//	try {
//		durations = eventDAO.findDurationFailuresByImsiBetweenDates(date1, date2);
//		//countFailure.setValue(count);
//		
//	} catch (Exception e) {
//	}
//	
//	return durations;	
//}
//
//	@Override
//	public List<Top10Failure> searchforTop10ImsiWithFailure(String startdatetime, String enddatetime) {
//		List<Top10Failure> result = null;
//		
//		String startDate = startdatetime;
//		String endDate = enddatetime;
//			
//		startDate = startDate.replace("T", " ");	
//		startDate = startDate.replace("t", " ");
//		endDate = endDate.replace("T", " ");
//		endDate = endDate.replace("t", " ");
//		
//		System.out.println("Got in here1aa");
//		Date date1 = null;
//		Date date2 = null;
//		
//		try {
//			date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startDate);
//			date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(endDate);		
//		} catch (ParseException e) {
//			System.out.println("date parse error");
//		}
//		
//		try {
//			result = eventDAO.findTop10ImsiWithFailure(date1, date2);	
//		} catch (Exception e){
//			result = null;
//		}
//		return result;
//	}
}

