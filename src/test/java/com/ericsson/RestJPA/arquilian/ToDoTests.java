/*package com.ericsson.RestJPA.arquilian;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import org.junit.Test;

import com.ericsson.dao.EnquiryDAO;
import com.ericsson.dao.EventDAO;
import com.ericsson.dao.FailureDAO;
import com.ericsson.dao.OperatorDAO;
import com.ericsson.entities.Enquiry;
import com.ericsson.entities.Event;
import com.ericsson.entities.Failure;
import com.ericsson.entities.Operator;
import com.ericsson.entities.OperatorPK;
import com.ericsson.other.Top10Failure;
import com.sun.tools.javac.util.List;

public class ToDoTests {
	
	@Test
    public void TestDoesUserNameExist() throws Exception {
    	
		//Account account = new Account();
		account.setAccounttype("SysAdmin");
		account.setName("test1");
		account.setPassword("test1");
		account.setUserEmail("test1@test1.ie");
		account.setUsername("test1");
		
		serviceEjb.addNewAccount(account);
		
		String username = "test1";
		boolean result = false;
		
		boolean userExist =  accountDAO.doesUserNameExist(username);
		
		assertTrue(userExist);
		
		boolean userNotExist =  accountDAO.doesUserNameExist("test45");
		assertFalse(userNotExist);
	}
    
    
    @Inject
    Failure failure;
    
    @Inject 
    ServiceEJB serviceEjb;
        
    @Inject
    FailureDAO failureDAO;
    
    
    @Test
    public void TestaddFailureClass() throws Exception {
    	
    	// failure = new Failure(String failureClass, String description, List<Event> events)
    	
    	Date date = new Date("11/01/2013");
		//Failure failure, Ue ue, CellHier cellHier, Eventdetail eventdetail, Operator operatorBean);
    	Event ev = new Event(date, 5, 11111, "11B", null, null, null, null, null);
    	Event ev1 = new Event(date, 6, 11112, "12A", null, null, null, null, null);
    	//Failure failure, Ue ue, CellHier cellHier, Eventdetail eventdetail, Operator operatorBean);
    	
    	String description = "TestClass";
    	String failureClass = "9";
    	List<Event> events = null;
    	events.add(ev);
    	events.add(ev1);
    	
		failure.setDescription(description);   	
		failure.setFailureClass(failureClass);
		failure.setEvents(events);
    	
		failureDAO.addFailureClass(failure);
    	
		Failure failReturned = failureDAO.findFailureByFailureClass(failureClass);
		
		assertEquals(failReturned.getClass(), description);
		assertNotSame("NOtTestClass", description);
	}
    
    
    @Test
    public void TestFindAllFailureClass() throws Exception {
    	
    	// failure = new Failure(String failureClass, String description, List<Event> events)
    	
    	Date date = new Date("11/01/2013");
		//Failure failure, Ue ue, CellHier cellHier, Eventdetail eventdetail, Operator operatorBean);
    	Event ev = new Event(date, 5, 11111, "11B", null, null, null, null, null);
    	Event ev1 = new Event(date, 6, 11112, "12A", null, null, null, null, null);
    	//Failure failure, Ue ue, CellHier cellHier, Eventdetail eventdetail, Operator operatorBean);
    	
    	String description = "TestClass";
    	String failureClass = "9";
    	List<Event> events = null;
    	events.add(ev);
    	events.add(ev1);
    	
        Failure failure1 = null;
        Failure failure2 = null;
    	
		failure.setDescription(description);   	
		failure.setFailureClass(failureClass);
		failure.setEvents(events);
		
		failure1.setDescription("TestClass1");   	
		failure1.setFailureClass("9");
		failure1.setEvents(events);
		
		failure2.setDescription("TestClass2");   	
		failure2.setFailureClass("9");
		failure2.setEvents(events);

		failureDAO.addFailureClass(failure);
		failureDAO.addFailureClass(failure1);
		failureDAO.addFailureClass(failure2);
    	
		List<Failure> returnedList = (List<Failure>) failureDAO.findAllFailureClass("failureClass");
		
		assertEquals(returnedList.get(0).getClass(), failureClass);
		assertEquals(returnedList.get(1).getClass(), failureClass);
		assertEquals(returnedList.get(2).getClass(), failureClass);
		
		assertNotSame(returnedList.get(0).getClass(), "1");
		assertNotSame(returnedList.get(1).getClass(), "8");
		assertNotSame(returnedList.get(2).getClass(), "3");
		
	}

    
    @Test
    public void TestfindFailureByFailureClass() throws Exception {
    	
    	// failure = new Failure(String failureClass, String description, List<Event> events)
    	
    	Date date = new Date("11/01/2013");
		//Failure failure, Ue ue, CellHier cellHier, Eventdetail eventdetail, Operator operatorBean);
    	Event ev = new Event(date, 5, 11111, "11B", null, null, null, null, null);
    	Event ev1 = new Event(date, 6, 11112, "12A", null, null, null, null, null);
    	//Failure failure, Ue ue, CellHier cellHier, Eventdetail eventdetail, Operator operatorBean);
    	
    	String description = "TestClass";
    	String failureClass = "9";
    	List<Event> events = null;
    	events.add(ev);
    	events.add(ev1);
    	
		failure.setDescription(description);   	
		failure.setFailureClass(failureClass);
		failure.setEvents(events);	

		failureDAO.addFailureClass(failure);
		
		
    	Failure f = failureDAO.findFailureByFailureClass(failureClass);
		
		assertEquals(f.getClass(), failureClass);
		
		assertNotSame(f.getClass(), "1");
		
	}
    
    
    @Inject
    Operator operator;
    
    @Inject 
    ServiceEJB serviceEjb;
        
    @Inject
    OperatorDAO operatorDAO;
    
    @Test
    public void TestaddOperator() throws Exception {
    	
    	// public Operator(OperatorPK id, String country, String operatorName, List<Event> events)
    	OperatorPK opPK = new OperatorPK(11, 11);

    	Date date = new Date("11/01/2013");
    	Event ev = new Event(date, 5, 11111, "11B", null, null, null, null, null);
    	Event ev1 = new Event(date, 6, 11112, "12A", null, null, null, null, null);
    	//Failure failure, Ue ue, CellHier cellHier, Eventdetail eventdetail, Operator operatorBean);
    	
    	String description = "TestClass";
    	String country = "Denmark";
    	String failureClass = "9";
    	List<Event> events = null;
    	events.add(ev);
    	events.add(ev1);
    	
		failure.setDescription(description);   	
		failure.setFailureClass(failureClass);
		
		operator.setId(opPK);
		operator.setCountry(country);
		operator.setOperatorName("TDC-DK");
		operator.setEvents(events);
		
		operatorDAO.addOperator(operator);
		
    	Operator op = operatorDAO.findOperatorByPK(opPK);
		
		assertEquals(op.getId(), opPK);
		assertNotSame(op.getId(), 1);
		assertEquals(op.getCountry(), country);
		assertNotSame(op.getCountry(), "Ireland");
		assertEquals(op.getId(), opPK);
		assertNotSame(op.getId(), 1);
	}
    
    
    @Inject
    Enquiry enquiry;
    
    @Inject 
    ServiceEJB serviceEjb;
        
    @Inject
    EnquiryDAO enquiryDAO;
    
    @Test
    public void TestaddEnquiry() throws Exception {
    	
    	//(int enquiryCount, String enquiryType, Account account, Date dateTime)

    	int enquiryCount = 11;
    	int id = 10;
    	String enquiryType = "TestType";
    	Date date = new Date("11/01/2013");
    	
    	account.setAccounttype("SysAdmin");
    	String nameID = "test1";
    	String username = "test1";
		account.setName(nameID);
		account.setPassword("test1");
		account.setUserEmail("test1@test1.ie");
		account.setUsername(username);

		enquiry.setEnquiryCount(enquiryCount);   	
		enquiry.setAccount(account);
		enquiry.setDateTime(date);
		enquiry.setEnquiryID(id);
		
		enquiryDAO.addEnquiry(enquiry);
		
		List<Enquiry> enqs = enquiryDAO.findEnquiriesTypeForUser(nameID, "test1");
		
		assertEquals(enqs.get(0).getAccount().getUsername(), username);
		assertNotSame(enqs.get(0).getAccount().getUsername(), "NotTest");
		assertEquals(enqs.get(0).getAccount().getName(), nameID);
		assertNotSame(enqs.get(0).getAccount().getName(), "NotTest");
		
	}
    
    
    
    @Inject
    Event event;
    
    @Inject 
    ServiceEJB serviceEjb;
        
    @Inject
    EventDAO eventDAO;
    
    @Test
    public void TestfindTop10ImsiWithFailure() throws Exception {

    	Failure failure = new Failure();
    	failure.setFailureClass("4");
    	failure.setDescription("MO DATA");

    	Ue ue = new Ue();
    	ue.setTac(33001735);
    	ue.setMarketingName("Mitsubishi GSM MT 20 Type MT 1171FD2");
    	ue.setManufacturer("Mitsubishi Electric France");
    	ue.setAccessCability("GSM 900");
    	ue.setModel("Mitsubishi GSM MT 20 Type MT 1171FD2");
    	ue.setOs("(null)");
    	ue.setInputMode("(null)");
    	ue.setUEType("(null)");

    	CellHier cellHier = new CellHier();
    	cellHier.setCellID(1);
    	cellHier.setHier3_id(Long.parseLong("5101480358281000000"));
    	cellHier.setHier32_id(Long.parseLong("4462714857032800000"));
    	cellHier.setHier321_id(Long.parseLong("1650127677358040000"));

    	EventdetailPK id = new EventdetailPK();
    	id.setCauseCode(1);
    	id.setEventID(4098);

    	Eventdetail ed = new Eventdetail();
    	ed.setId(id);
    	ed.setDescription("S1 SIG CONN SETUP-S1 INTERFACE DOWN");

    	OperatorPK opPK = new OperatorPK(1, 238);
    	Operator operator = new Operator();
    	operator.setId(opPK);
    	operator.setCountry("Denmark");
    	operator.setOperatorName("TDC-DK");


    	@SuppressWarnings("deprecation")
    	Event evt = new Event(new Date("2/21/2013 21:01"), 1005, Long.parseLong("191911000456426"), "13A", failure, ue, cellHier, ed, operator);
    	
    	List<Top10Failure> compareList = new ArrayList<Top10Failure>();
    	Date startDate = null;
		Date endDate = null;
		List<Top10Failure> result = (List<Top10Failure>) eventDAO.findTop10ImsiWithFailure(startDate, endDate);
    	
		assertEquals(result, compareList);
		assertNotSame(result, compareList);

	}
    
    
    @Test
    public void TestfindDurationFailuresByImsiBetweenDates() throws Exception {
    	
    	ArrayList<Long> compareList = new ArrayList<Long>();
    	Date startDate = null;
		Date endDate = null;
		List<Long> result = (List<Long>) eventDAO.findDurationFailuresByImsiBetweenDates(startDate, endDate);
    	
		assertEquals(result, compareList);
		assertNotSame(result, compareList);
	}
    
    
    @Test
    public void TestfindCountFailuresByImsiBetweenDates() throws Exception {
    	
    	ArrayList<Long> compareList = new ArrayList<Long>();
    	Date startDate = null;
		Date endDate = null;
		List<Long> result = (List<Long>) eventDAO.findCountFailuresByImsiBetweenDates(startDate, endDate);
    	
		assertEquals(result, compareList);
		assertNotSame(result, compareList);
	}
    
    
    @Test
    public void TestfindFailuresByImsiBetweenDates() throws Exception {
    	
    	ArrayList<Long> compareList = new ArrayList<Long>();
    	Date startDate = null;
		Date endDate = null;
		List<Long> result = (List<Long>) eventDAO.findFailuresByImsiBetweenDates(startDate, endDate);
    	
		assertEquals(result, compareList);
		assertNotSame(result, compareList);
	}
    
    
    @Test
    public void TestfindCountMarketOperatorCellIDcombo() throws Exception {
    	
    	ArrayList<BigInteger> compareList = new ArrayList<BigInteger>();
		List<BigInteger> result = (List<BigInteger>) eventDAO.findCountMarketOperatorCellIDcombo();
    	
		assertEquals(result, compareList);
		assertNotSame(result, compareList);
	}
    
    
    @Test
    public void TestfindMarketOperatorCellIDCombo() throws Exception {
    	
    	List<Event> compareList = new ArrayList<Event>();
		List<Event> result = eventDAO.findMarketOperatorCellIDCombo();
    	
		assertEquals(result, compareList);
		assertNotSame(result, compareList);
	}
    
    @Test
    public void TestfindCountCauseCodeOccurancesByModel() throws Exception {
    	int tac = 0;
    	List<BigInteger> compareList = new ArrayList<BigInteger>();
		List<BigInteger> result = eventDAO.findCountCauseCodeOccurancesByModel(tac);
    	
		assertEquals(result, compareList);
		assertNotSame(result, compareList);
	}
    
    
    @Test
    public void TestfindNumberEventsCauseCodeByModel() throws Exception {
    	int tac = 0;
    	List<Event> compareList = new ArrayList<Event>();
		List<Event> result = eventDAO.findNumberEventsCauseCodeByModel(tac);
    	
		assertEquals(result, compareList);
		assertNotSame(result, compareList);
	}
    
    @Test  //CHeck this one. May return??
    public void TestfindNumberEventsByModelAndDate() throws Exception {
    	
    	Date startDate= new Date("11/01/2013");
    	Date endDate = new Date("11/01/2013");
    	int tac = 0;
    	List<Event> compareList = new ArrayList<Event>();
		List<Event> result = eventDAO.findNumberEventsCauseCodeByModel(tac);
    	
		assertEquals(result, compareList);
		assertNotSame(result, compareList);
	}
    
    
    @Test
    public void TestfindEventByFailureClass() throws Exception {
    	
    	String failureClass = "";
    	List<Long> compareList = new ArrayList<Long>();
		List<Long> result = eventDAO.findEventByFailureClass(failureClass);
    	
		assertEquals(result, compareList);
		assertNotSame(result, compareList);
	}
    
    @Test
    public void TestfindImsisByFailureClass() throws Exception {
    	
    	String failureClass = "";
    	List<Event> compareList = new ArrayList<Event>();
		List<Event> result = eventDAO.findImsisByFailureClass(failureClass);
    	
		assertEquals(result, compareList);
		assertNotSame(result, compareList);
	}
    
    
    @Test
    public void TestfindNumberImsifailuresBetweenDates() throws Exception {
    	
    	Date startDate= new Date("11/01/2013");
    	Date endDate = new Date("11/01/2013");
    	List<Long> compareList = new ArrayList<Long>();
		List<Long> result = eventDAO.findNumberImsifailuresBetweenDates(startDate, endDate);
    	
		assertEquals(result, compareList);
		assertNotSame(result, compareList);
	}
        
        
    @Test
    public void TestfindEventIdCauseCodeByImsi() throws Exception {
    	
    	Long imsi = 11112222111221111l;
    	List<Event> compareList = new ArrayList<Event>();
		List<Event> result = eventDAO.findEventIdCauseCodeByImsi(imsi);
    	
		assertEquals(result, compareList);
		assertNotSame(result, compareList);
	}
    
    //List<Event> findUniqueCauseCodeByImsi(Long imsi)
    @Test
    public void TestfindUniqueCauseCodeByImsi() throws Exception {
    	
    	Long imsi = 11112222111221111l;
    	List<Event> compareList = new ArrayList<Event>();
		List<Event> result = eventDAO.findUniqueCauseCodeByImsi(imsi);
    	
		assertEquals(result, compareList);
		assertNotSame(result, compareList);
	}
    
    
    @Test
    public void TestfindDistinctEventIdCauseCodeByImsi() throws Exception {
    	
    	Long imsi = 11112222111221111l;
    	List<Event> compareList = new ArrayList<Event>();
		List<Event> result = eventDAO.findDistinctEventIdCauseCodeByImsi(imsi);
    	
		assertEquals(result, compareList);
		assertNotSame(result, compareList);
	}
    

    @Test
    public void TestfindCountByModelAndDate() throws Exception {
    	
    	String model = "Panasonic";
    	Date startDate= new Date("11/01/2013");
    	Date endDate = new Date("11/01/2013");
    	
    	List<Event> compareList = new ArrayList<Event>();
		List<Event> result = eventDAO.findCountByModelAndDate(model, startDate, endDate);
    	
		assertEquals(result, compareList);
		assertNotSame(result, compareList);
	}
    
    
    //List<Event> findCountByImsiAndDate(long imsi, Date date1, Date date2)
    @Test
    public void TestfindCountByImsiAndDate() throws Exception {
    	
    	Long imsi = 11112222111221111l;
    	Date startDate= new Date("11/01/2013");
    	Date endDate = new Date("11/01/2013");
    	
    	List<Event> compareList = new ArrayList<Event>();
		List<Event> result = eventDAO.findCountByImsiAndDate(imsi, startDate, endDate);
    	
		assertEquals(result, compareList);
		assertNotSame(result, compareList);
	}
	
	public void testFindAllCellHier() throws Exception{
CellHier cellHier1 = new CellHier();
cellHier1.setCellID(1);
cellHier1.setHier3_id(Long.parseLong("5101480358281000000"));
cellHier1.setHier32_id(Long.parseLong("4462714857032800000"));
cellHier1.setHier321_id(Long.parseLong("1650127677358040000"));
cellHierDao.addCellHier(cellHier1);

CellHier cellHier2 = new CellHier();
cellHier2.setCellID(2);
cellHier2.setHier3_id(Long.parseLong("5337549188380290000"));
cellHier2.setHier32_id(Long.parseLong("5546490654156790000"));
cellHier2.setHier321_id(Long.parseLong("4931866955053780000"));
cellHierDao.addCellHier(cellHier2);

List<CellHier> cellHierList = cellHierDao.findAllCellHier();

assertEquals(cellHierList.get(0).getCellID(), 1);
assertEquals(cellHierList.get(0).getHier3_id(), Long.parseLong("5101480358281000000"));
assertEquals(cellHierList.get(0).getHier32_id(), Long.parseLong("4462714857032800000"));
assertEquals(cellHierList.get(0).getHier32_id(), Long.parseLong("4462714857032800000"));

assertEquals(cellHierList.get(1).getCellID(), 2);
assertEquals(cellHierList.get(1).getHier3_id(), Long.parseLong("5337549188380290000"));
assertEquals(cellHierList.get(1).getHier32_id(), Long.parseLong("5546490654156790000"));
assertEquals(cellHierList.get(1).getHier32_id(), Long.parseLong("4931866955053780000"));


assertNotSame(cellHierList.get(0).getCellID(), 5);
assertNotSame(cellHierList.get(0).getHier3_id(), Long.parseLong("5101480358281000001"));
assertNotSame(cellHierList.get(0).getHier32_id(), Long.parseLong("4462714857032800001"));
assertNotSame(cellHierList.get(0).getHier32_id(), Long.parseLong("4462714857032800001"));

assertNotSame(cellHierList.get(1).getCellID(), 5);
assertNotSame(cellHierList.get(1).getHier3_id(), Long.parseLong("5337549188380290001"));
assertNotSame(cellHierList.get(1).getHier32_id(), Long.parseLong("5546490654156790001"));
assertNotSame(cellHierList.get(1).getHier32_id(), Long.parseLong("4931866955053780001"));
}


public void testAddEventdetailAndFindEventdetail() throws Exception{

EventdetailPK id = new EventdetailPK();
id.setCauseCode(0);
id.setEventID(4097);

Eventdetail ed = new Eventdetail();
ed.setId(id);
ed.setDescription("RRC CONN SETUP-SUCCESS");

eventDetailDao.addEventdetail(ed);

Eventdetail edDB = eventDetailDao.findEventDetailByPK(new EventdetailPK(0, 4097));

assertEquals(edDB.getId().getCauseCode(), 0);
assertEquals(edDB.getId().getEventID(), 4097);
assertEquals(edDB.getDescription(), "RRC CONN SETUP-SUCCESS");

assertNotSame(edDB.getId().getCauseCode(), 100);
assertNotSame(edDB.getId().getEventID(), 2033);
assertNotSame(edDB.getDescription(), "Hello world");

Eventdetail edDB1 = eventDetailDao.findEventDetailByPK(new EventdetailPK(100, 4097));
assertNull(edDB1); 
}


public void testaddUeAndFindUe() throws Exception{

Ue ue = new Ue();
ue.setTac(100100);
ue.setMarketingName("G410");
ue.setManufacturer("Mitsubishi");
ue.setAccessCability("GSM 1800, GSM 900");
ue.setModel("G410");
ue.setOs("(null)");
ue.setInputMode("(null)");
ue.setUEType("(null)");

ueDao.addUe(ue);

Ue ueDB = ueDao.findUeBTac(100100);
assertEquals(ueDB.getTac(), 100100);
assertEquals(ueDB.getMarketingName(), "G410");
assertEquals(ueDB.getManufacturer(), "Mitsubishi");
assertEquals(ueDB.getAccessCability() , "GSM 1800, GSM 900");
assertEquals(ueDB.getModel() , "G410");
assertEquals(ueDB.getOs() , "(null)");
assertEquals(ueDB.getInputMode(), "(null)");
assertEquals(ueDB.getInputMode(), "(null)");

assertNotSame(ueDB.getTac(), 100101);
assertNotSame(ueDB.getMarketingName(), "A410");
assertNotSame(ueDB.getManufacturer(), "MitsubishY");
assertNotSame(ueDB.getAccessCability() , "GSM 2000, GSM 800");
assertNotSame(ueDB.getModel() , "A410");
assertNotSame(ueDB.getOs() , "(noll)");
assertNotSame(ueDB.getInputMode(), "(noll)");
assertNotSame(ueDB.getInputMode(), "(noll)");


Ue ueDB1 = ueDao.findUeBTac(1);
assertNull(ueDB1); 
}
    
}


Failure failure = new Failure();
failure.setFailureClass("4");
failure.setDescription("MO DATA");

Ue ue = new Ue();
ue.setTac(33001735);
ue.setMarketingName("Mitsubishi GSM MT 20 Type MT 1171FD2");
ue.setManufacturer("Mitsubishi Electric France");
ue.setAccessCability("GSM 900");
ue.setModel("Mitsubishi GSM MT 20 Type MT 1171FD2");
ue.setOs("(null)");
ue.setInputMode("(null)");
ue.setUEType("(null)");

CellHier cellHier = new CellHier();
cellHier.setCellID(1);
cellHier.setHier3_id(Long.parseLong("5101480358281000000"));
cellHier.setHier32_id(Long.parseLong("4462714857032800000"));
cellHier.setHier321_id(Long.parseLong("1650127677358040000"));

EventdetailPK id = new EventdetailPK();
id.setCauseCode(1);
id.setEventID(4098);

Eventdetail ed = new Eventdetail();
ed.setId(id);
ed.setDescription("S1 SIG CONN SETUP-S1 INTERFACE DOWN");

OperatorPK opPK = new OperatorPK(1, 238); 
Operator operator = new Operator();
operator.setId(opPK);
operator.setCountry("Denmark");
operator.setOperatorName("TDC-DK");


@SuppressWarnings("deprecation")
Event evt = new Event(new Date("2/21/2013 21:01"), 1005, Long.parseLong("191911000456426"), "13A", failure, ue, cellHier, ed, operator);



Failure failure1 = new Failure();
failure1.setFailureClass("0");
failure1.setDescription("EMERGENCY");

Ue ue1 = new Ue();
ue1.setTac(33001735);
ue1.setMarketingName("Mitsubishi GSM MT 20 Type MT 1171FD2");
ue1.setManufacturer("Mitsubishi Electric France");
ue1.setAccessCability("GSM 900");
ue1.setModel("Mitsubishi GSM MT 20 Type MT 1171FD2");
ue1.setOs("(null)");
ue1.setInputMode("(null)");
ue1.setUEType("(null)");

CellHier cellHier1 = new CellHier();
cellHier1.setCellID(4);
cellHier1.setHier3_id(Long.parseLong("4054409192418620000"));
cellHier1.setHier32_id(Long.parseLong("5899098095989780000"));
cellHier1.setHier321_id(Long.parseLong("4094364993141140000"));

EventdetailPK id1 = new EventdetailPK();
id1.setCauseCode(1);
id1.setEventID(4098);

Eventdetail ed1 = new Eventdetail();
ed1.setId(id1);
ed1.setDescription("S1 SIG CONN SETUP-S1 INTERFACE DOWN");

OperatorPK opPK1 = new OperatorPK(1, 238); 
Operator operator1 = new Operator();
operator1.setId(opPK1);
operator1.setCountry("Denmark");
operator1.setOperatorName("TDC-DK");


@SuppressWarnings("deprecation")
Event evt1 = new Event(new Date("2/22/2013 19:37"), 1005, Long.parseLong("191911000420566"), "13A", failure, ue, cellHier, ed, operator);
eventDao.addEvent(evt1);

@Test
public void testFindUeByModel() throws Exception{

Ue ue = new Ue();
ue.setTac(100100);
ue.setMarketingName("G410");
ue.setManufacturer("Mitsubishi");
ue.setAccessCability("GSM 1800, GSM 900");
ue.setModel("G410");
ue.setOs("(null)");
ue.setInputMode("(null)");
ue.setUEType("(null)");

ueDao.addUe(ue);

Ue ueDB = ueDao.findUeByModel("G410");
assertEquals(ueDB.getTac(), 100100);
assertEquals(ueDB.getMarketingName(), "G410");
assertEquals(ueDB.getManufacturer(), "Mitsubishi");
assertEquals(ueDB.getAccessCability() , "GSM 1800, GSM 900");
assertEquals(ueDB.getModel() , "G410");
assertEquals(ueDB.getOs() , "(null)");
assertEquals(ueDB.getInputMode(), "(null)");
assertEquals(ueDB.getUEType(), "(null)");

assertNotSame(ueDB.getTac(), 100101);
assertNotSame(ueDB.getMarketingName(), "A410");
assertNotSame(ueDB.getManufacturer(), "MitsubishY");
assertNotSame(ueDB.getAccessCability() , "GSM 2000, GSM 800");
assertNotSame(ueDB.getModel() , "A410");
assertNotSame(ueDB.getOs() , "(noll)");
assertNotSame(ueDB.getInputMode(), "(noll)");
assertNotSame(ueDB.getUEType(), "(noll)");
}

@Test
public void addEvents() throws Exception{

Failure failure = new Failure();
failure.setFailureClass("4");
failure.setDescription("MO DATA");

Ue ue = new Ue();
ue.setTac(33001735);
ue.setMarketingName("Mitsubishi GSM MT 20 Type MT 1171FD2");
ue.setManufacturer("Mitsubishi Electric France");
ue.setAccessCability("GSM 900");
ue.setModel("Mitsubishi GSM MT 20 Type MT 1171FD2");
ue.setOs("(null)");
ue.setInputMode("(null)");
ue.setUEType("(null)");

CellHier cellHier = new CellHier();
cellHier.setCellID(1);
cellHier.setHier3_id(Long.parseLong("5101480358281000000"));
cellHier.setHier32_id(Long.parseLong("4462714857032800000"));
cellHier.setHier321_id(Long.parseLong("1650127677358040000"));

EventdetailPK id = new EventdetailPK();
id.setCauseCode(1);
id.setEventID(4098);

Eventdetail ed = new Eventdetail();
ed.setId(id);
ed.setDescription("S1 SIG CONN SETUP-S1 INTERFACE DOWN");

OperatorPK opPK = new OperatorPK(1, 238); 
Operator operator = new Operator();
operator.setId(opPK);
operator.setCountry("Denmark");
operator.setOperatorName("TDC-DK");


@SuppressWarnings("deprecation")
Event evt = new Event(new Date("2/21/2013 21:01"), 1005, Long.parseLong("191911000456426"), "13A", failure, ue, cellHier, ed, operator);
eventDao.addEvent(evt);


Failure failure1 = new Failure();
failure1.setFailureClass("0");
failure1.setDescription("EMERGENCY");

Ue ue1 = new Ue();
ue1.setTac(33001735);
ue1.setMarketingName("Mitsubishi GSM MT 20 Type MT 1171FD2");
ue1.setManufacturer("Mitsubishi Electric France");
ue1.setAccessCability("GSM 900");
ue1.setModel("Mitsubishi GSM MT 20 Type MT 1171FD2");
ue1.setOs("(null)");
ue1.setInputMode("(null)");
ue1.setUEType("(null)");

CellHier cellHier1 = new CellHier();
cellHier1.setCellID(4);
cellHier1.setHier3_id(Long.parseLong("4054409192418620000"));
cellHier1.setHier32_id(Long.parseLong("5899098095989780000"));
cellHier1.setHier321_id(Long.parseLong("4094364993141140000"));

EventdetailPK id1 = new EventdetailPK();
id1.setCauseCode(1);
id1.setEventID(4098);

Eventdetail ed1 = new Eventdetail();
ed1.setId(id1);
ed1.setDescription("S1 SIG CONN SETUP-S1 INTERFACE DOWN");

OperatorPK opPK1 = new OperatorPK(1, 238); 
Operator operator1 = new Operator();
operator1.setId(opPK1);
operator1.setCountry("Denmark");
operator1.setOperatorName("TDC-DK");


@SuppressWarnings("deprecation")
Event evt1 = new Event(new Date("2/22/2013 19:37"), 1005, Long.parseLong("191911000420566"), "13A", failure, ue, cellHier, ed, operator);
eventDao.addEvent(evt1);

List<Event> events = eventDao.findAllEvents();
assertEquals(events.get(0), evt);
assertEquals(events.get(1), evt1);

List<Event> events1 = eventDao.findCountByImsiAndDate(Long.parseLong("191911000420566"), 
new Date("2/22/2002 19:37"), new Date("2/22/2014 19:37"));
assertEquals(events1.get(0), evt1);
}



@Inject
UeErrorDAO ueErrorDAO;

@Inject
EventErrorDAO eventErrorDAO;

@Inject
EventCauseErrorDAO eventCauseErrorDAO;


@Inject
FailureErrorDAO failureErrorDAO;

@Test
public void testaddUeErroreAndFindUeError() throws Exception{

UeErrorTb ueError = new UeErrorTb();
ueError.setTac(1088088900);
ueError.setMarketingName("G410");
ueError.setManufacturer("Mitsubishi Mobile");
ueError.setVendorName("Mitsubishi Mobile");
ueError.setModel("G410");
ueError.setOs("(null)");
ueError.setInputMode("(null)");
ueError.setAccessCapability("hello world");
ueErrorDAO.addUeError(ueError);

UeErrorTb ueErrorDB = ueErrorDAO.findUeErrorTbTac(1088088900);
assertEquals(ueErrorDB, ueError);

UeErrorTb ueErrorDB1 = ueErrorDAO.findUeErrorTbTac(10800);
assertNull(ueErrorDB1); 
}


@Test
public void testaddEventErrorDao(){

List<EventErrorTb> evntErrors = eventErrorDAO.findAllEventErrors();
assertNull(evntErrors);

EventErrorTb evntError = new EventErrorTb();
evntError.setCauseCode("(null)");
evntError.setCellId("4");
evntError.setDate_Time("2013-01-11 17:15");
evntError.setDuration("1000");
evntError.setEventId("4099");
evntError.setFailure("(null)");
evntError.setHier321_Id("1150444940909479940");
evntError.setHier32_Id("8226896360947470300");
evntError.setHier3_Id("4809532081614990300");
evntError.setImsi("344930000000011");
evntError.setMarket("344");
evntError.setNeVersion("11B");
evntError.setOperator("930");
evntError.setUeType("21060800");

eventErrorDAO.addEventError(evntError);

List<EventErrorTb> evntError1 = eventErrorDAO.findAllEventErrors();
assertEquals(evntError1.get(0), evntError); 
}


@Test
public void testAddEventCauseError(){

EventCauseErrorTbPK pk = new EventCauseErrorTbPK();
pk.setCauseCode(1);
pk.setEventId(200);
EventCauseErrorTb eventcauseErrors = eventCauseErrorDAO.findEventCauseErrorTbByPK(pk);
assertNull(eventcauseErrors);

EventCauseErrorTb evntCauseError = new EventCauseErrorTb();
evntCauseError.setId(pk);
evntCauseError.setDescription("hello world");

eventCauseErrorDAO.addEventCauseError(evntCauseError);

EventCauseErrorTb eventcauseErrors2 = eventCauseErrorDAO.findEventCauseErrorTbByPK(pk);

assertEquals(evntCauseError, eventcauseErrors2);
}



@Test
public void testAddFailureError(){
List<Event> events = null;
Failure failure = new Failure("1", "HIGH PRIORITY ACCESS", events);
FailureClassErrorTb failError = failureErrorDAO.findFailureClassErrorbyFailure(failure);
assertNull(failError);


FailureClassErrorTb fce = new FailureClassErrorTb();
fce.setFailure(1);
fce.setDescription("HIGH PRIORITY ACCESS");

failureErrorDAO.addFailureError(fce);

FailureClassErrorTb failError1 = failureErrorDAO.findFailureClassErrorbyFailure(failure);
assertEquals(failError1, fce);
}






*/