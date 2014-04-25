/*package com.ericsson.RestJPA.jUnit;

import static org.junit.Assert.*;

import java.util.List;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.dao.CellHierDAO;
import com.ericsson.entities.CellHier;
import com.ericsson.entities.Event;
import com.ericsson.entities.Eventdetail;
import com.ericsson.entities.EventdetailPK;
import com.ericsson.entities.Failure;
import com.ericsson.entities.Operator;
import com.ericsson.entities.OperatorPK;
import com.ericsson.entities.Ue;
import com.ericsson.restservice.PersistenceUtil;



public class DbPersistenceTesting {

	private long time=0;
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private Date date;
	private java.sql.Timestamp dateTimepk;
	private String string45 = "012345678901234567890123456789012345678901234";
	private String string100 ="01234567890123456789012345678901234567890123456789"
			+ "01234567890123456789012345678901234567890123456789";
	private String string1000;
	private int intvalue=999999999;
	private String overIntValue = "999999999";
	private long longvalue=999999999;
	private List<Event> events = new ArrayList<Event>();

	@Before
	public void setUp(){
		for(int i=0;i<10;i++)
			string1000=string1000+string100;
		try {
			date = dateFormat.parse("01/01/2000");
			time = date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dateTimepk = new Timestamp(time);
	}


	@Test
	public void testFailure_Initialization() {

		Failure failure = new Failure(overIntValue, string45, events);
		failure.setFailureClass(overIntValue);
		failure.setDescription(string45);


		assertFalse("Failure failure.setFailureClass false", (failure.getDescription().equals(string45+5)));
		assertFalse("Failure failure.setFailureClass false", (failure.getFailureClass()==overIntValue + "w"));

		assertTrue("Failure failure.setDescription true", failure.getDescription().equals(string45));
		assertTrue("Failure failure.setFailureClass true", (failure.getFailureClass()==overIntValue));

	}

	@Test
	public void testEventdetail_Initialization() {

		EventdetailPK y = new EventdetailPK();
		EventdetailPK z = new EventdetailPK();
		y.setCauseCode(intvalue);
		y.setEventID(intvalue);
		Eventdetail x = new Eventdetail(y, string1000, events);


		x.setId(y);
		x.setDescription(string1000);

		assertFalse("EventdetailPK.setCauseCode false", (y.getCauseCode()==intvalue+1));
		assertFalse("EventdetailPK.setEventID false", (y.getEventID()==intvalue+1));
		z.setCauseCode(1);z.setEventID(2);
		assertFalse("Eventdetail.setId false", (x.getId().equals(z)));
		assertFalse(" EventdetailPK.setDescription false", (x.getDescription().equalsIgnoreCase(string1000+1)));

		assertTrue(" EventdetailPK.setCauseCode True", (y.getCauseCode()==intvalue));
		assertTrue(" EventdetailPK.setEventID True", (y.getEventID()==intvalue));
		assertTrue(" Eventdetail.setId True", ((x.getId().getCauseCode()==intvalue)&&(x.getId().getEventID()==intvalue)));
		assertTrue(" EventdetailPK.setDescription True", (x.getDescription().equalsIgnoreCase(string1000)));

	}

	@Test
	public void testCell_Hier_Initialization() {

		CellHier x = new CellHier(intvalue, longvalue, longvalue, longvalue, events);
		x.setCellID(intvalue);
		x.setHier321_id(longvalue);
		x.setHier32_id(longvalue);
		x.setHier3_id(longvalue);

		assertFalse(" x.setCellID false", (x.getCellID()==intvalue+1));
		assertFalse(" x.setHier321_id false", (x.getHier321_id()==longvalue+1));
		assertFalse(" x.setHier32_id false", (x.getHier32_id()==longvalue+1));
		assertFalse(" x.setHier3_id false", (x.getHier3_id()==longvalue+1));

		assertTrue(" x.setCellID True", (x.getCellID()==intvalue));
		assertTrue(" x.setHier321_id True", (x.getHier321_id()==longvalue));
		assertTrue(" x.setHier32_id True", (x.getHier32_id()==longvalue));
		assertTrue(" x.setHier3_id True", (x.getHier3_id()==longvalue));

	}

	@Inject
	CellHierDAO cellHierDAO;
	@Inject
	CellHier cellHier;

	@Before
	public void setupAccount(){
		cellHier.setCellID(10);
		cellHier.setHier3_id(Long.parseLong("5101480358281000000"));
		cellHier.setHier32_id(Long.parseLong("4462714857032800000"));
		cellHier.setHier321_id(Long.parseLong("1650127677358040000"));;
	}
	@Test
	public void testAddCellHier() throws Exception{

		cellHier.setCellID(10);
		cellHier.setHier3_id(Long.parseLong("5101480358281000000"));
		cellHier.setHier32_id(Long.parseLong("4462714857032800000"));
		cellHier.setHier321_id(Long.parseLong("1650127677358040000"));

		cellHierDAO.addCellHier(cellHier);
		CellHier ch = cellHierDAO.findCellHierByCellID(1);

		assertEquals(ch.getCellID(), 10);
		assertEquals(ch.getHier3_id(), Long.parseLong("5101480358281000000"));
		assertEquals(ch.getHier32_id(), Long.parseLong("4462714857032800000"));
		assertEquals(ch.getHier32_id(), Long.parseLong("4462714857032800000"));

		assertNotSame(ch.getCellID(), 2);
		assertNotSame(ch.getHier3_id(), Long.parseLong("5101480358281000001"));
		assertNotSame(ch.getHier32_id(), Long.parseLong("4462714857032800001"));
		assertNotSame(ch.getHier32_id(), Long.parseLong("4462714857032800001"));
	}

	@Test
	public void testOperator_Initialization() {

		OperatorPK y = new OperatorPK();
		OperatorPK z = new OperatorPK();

		y.setMcc(intvalue);
		y.setMnc(intvalue);
		Operator x = new Operator(y, string100, string100, events);

		x.setCountry(string100);
		x.setOperatorName(string100);

		x.setId(y);
		z.setMcc(1);z.setMnc(1);

		assertFalse(" Operator.setCountry false", (x.getCountry().equals(string100+1)));
		assertFalse(" Operator.setOperatorName false", (x.getOperatorName().equals(string100+1)));

		assertFalse(" OperatorPK.setMcc false", (y.getMcc()==intvalue+1));
		assertFalse(" OperatorPK.setMnc false", (y.getMcc()==intvalue+1));
		assertFalse(" Operator.setId false", ((x.getId().getMcc()==intvalue+1)
				&&(x.getId().getMnc()==intvalue+1)));


		assertTrue(" Operator.setCountry True", (x.getCountry().equals(string100)));
		assertTrue(" Operator.setOperatorName True", (x.getOperatorName().equals(string100)));

		assertTrue(" OperatorPK.setMcc True", (y.getMcc()==intvalue));
		assertTrue(" OperatorPK.setMnc True", (y.getMcc()==intvalue));
		assertTrue(" Operator.setId True", ((x.getId().getMcc()==intvalue)
				&&(x.getId().getMnc()==intvalue)));

	}

	@Test
	public void testUE_Initialization(){

		Ue x = new Ue(intvalue, string1000, string100, string100, string100, string100, string100, string100, events);
		x.setAccessCability(string1000);
		x.setInputMode(string100);
		x.setManufacturer(string100);
		x.setMarketingName(string100);
		x.setModel(string100);
		x.setOs(string100);
		x.setTac(intvalue);
		x.setUEType(string100);


		assertFalse(" Ue.setAccessCability False",(x.getAccessCability().equals(string1000+1)));
		assertFalse(" Ue.setInputMode False",(x.getInputMode().equals(string100+1)));
		assertFalse(" Ue.setManufacturer False",(x.getManufacturer().equals(string100+1)));
		assertFalse(" Ue.setMarketingName False",(x.getMarketingName().equals(string100+1)));
		assertFalse(" Ue.setModel False",(x.getModel().equals(string100+1)));
		assertFalse(" Ue.setOs False",(x.getOs().equals(string100+1)));
		assertFalse(" Ue.setTac False",(x.getTac()==intvalue+1));
		assertFalse(" Ue.setUEType False",(x.getUEType().equals(string100+1)));


		assertTrue(" Ue.setAccessCability True",(x.getAccessCability().equals(string1000)));
		assertTrue(" Ue.setInputMode True",(x.getInputMode().equals(string100)));
		assertTrue(" Ue.setManufacturer True",(x.getManufacturer().equals(string100)));
		assertTrue(" Ue.setMarketingName True",(x.getMarketingName().equals(string100)));
		assertTrue(" Ue.setModel True",(x.getModel().equals(string100)));
		assertTrue(" Ue.setOs True",(x.getOs().equals(string100)));
		assertTrue(" Ue.setTac True",(x.getTac()==intvalue));
		assertTrue(" Ue.setUEType True",(x.getUEType().equals(string100)));

	}


	@Test
	public void testEvent_Initialization(){

		Failure failure = new Failure();
		failure.setFailureClass(overIntValue);
		failure.setDescription(string45);

		Eventdetail eventdetail = new Eventdetail();
		EventdetailPK eventdetailPK = new EventdetailPK();
		eventdetailPK.setCauseCode(intvalue);
		eventdetailPK.setEventID(intvalue);
		eventdetail.setId(eventdetailPK);
		eventdetail.setDescription(string1000);

		CellHier cellHier = new CellHier();
		cellHier.setCellID(intvalue);
		cellHier.setHier321_id(longvalue);
		cellHier.setHier32_id(longvalue);
		cellHier.setHier3_id(longvalue);

		Operator operator = new Operator();
		OperatorPK operatorPK = new OperatorPK();
		operator.setCountry(string100);
		operator.setOperatorName(string100);
		operatorPK.setMcc(intvalue);
		operatorPK.setMnc(intvalue);
		operator.setId(operatorPK);

		Ue ue= new Ue();
		ue.setAccessCability(string1000);
		ue.setInputMode(string100);
		ue.setManufacturer(string100);
		ue.setMarketingName(string100);
		ue.setModel(string100);
		ue.setOs(string100);
		ue.setTac(intvalue);
		ue.setUEType(string100);

		Event event = new Event(dateTimepk, intvalue, longvalue, string45, failure, ue, cellHier, eventdetail, operator);
		event.setCellHier(cellHier);
		event.setDateTime(dateTimepk);
		event.setDuration(intvalue);
		event.setEventdetail(eventdetail);
		event.setFailure(failure);
		event.setImsi(longvalue);
		event.setNE_Version(string45);


		assertTrue(" Event.setCellHier True",(event.getCellHier().equals(cellHier)));
		assertTrue(" Event.setDateTime True",(event.getDateTime().equals(dateTimepk)));
		assertTrue(" Event.setDuration True",(event.getDuration()==intvalue));
		assertTrue(" Event.setEventdetail True",(event.getEventdetail().equals(eventdetail)));
		assertTrue(" Event.setFailure True",(event.getFailure().equals(failure)));
		assertTrue(" Event.setImsi True",(event.getImsi()==longvalue));
		assertTrue(" Event.setNE_Version True",(event.getNE_Version().equals(string45)));


		//cellHier.setCellID(intvalue+3);
		//assertFalse(" Event.setCellHier False",(event.getCellHier().equals(cellHier)));
		//dateTimepk=Timestamp.valueOf("01/01/2001");
		//assertFalse(" Event.setDateTime False",(event.getDateTime().equals(dateTimepk)));
		assertFalse(" Event.setDuration False",(event.getDuration()==intvalue+1));
		//eventdetail.setDescription("sadfgsd");
		//assertFalse(" Event.setEventdetail False",(event.getEventdetail().equals(eventdetail)));
		//failure.setDescription("fsdgsdsadsd");
		//assertFalse(" Event.setFailure False",(event.getFailure().equals(failure)));
		assertFalse(" Event.setImsi False",(event.getImsi()==longvalue+1));
		assertFalse(" Event.setNE_Version False",(event.getNE_Version().equals(string45+1)));


	}


	@Test
	public void testPersistenceUtil_findAllEvents(){
		List<Event> events = PersistenceUtil.findAllEvents();
		assertNotNull(events);

	}

	@Test
	public void testPersistenceUtil_findFailureByFailureClass(){
		Failure failure = PersistenceUtil.findFailureByFailureClass("2");
		Failure failureNull = PersistenceUtil.findFailureByFailureClass("10");
		assertNull(failureNull);
		assertNotNull(failure);
	}

	@Test
	public void testPersistenceUtil_findUeBTac()
	{
		Ue ue = PersistenceUtil.findUeBTac(100100);
		Ue ueNull = PersistenceUtil.findUeBTac(852369741);
		assertNull(ueNull);
		assertNotNull(ue);
	}

	@Test
	public void testPersistenceUtil_findCellHierByCellID(){
		CellHier cellHier = PersistenceUtil.findCellHierByCellID(4);
		CellHier NullCellHier = PersistenceUtil.findCellHierByCellID(10);
		assertNull(NullCellHier);
		assertNotNull(cellHier);
	}

	@Test
	public void testPersistenceUtil_findOperatorByPK()
	{
		OperatorPK pk = new OperatorPK(238,1);
		Operator operator = PersistenceUtil.findOperatorByPK(pk);
		OperatorPK pk2 = new OperatorPK(1234,78);
		Operator nullOperator = PersistenceUtil.findOperatorByPK(pk2);
		assertNull(nullOperator);
		assertNotNull(operator);
	}

	@Test
	public void testPersistenceUtil_findEventIdCauseCodeByImsi()
	{
		List<Event> testEvents = PersistenceUtil.findEventIdCauseCodeByImsi(310560000000012L);
		List<Event> NullTestEvents = PersistenceUtil.findEventIdCauseCodeByImsi(310560014520012L);
		assertNull(NullTestEvents);
		assertNotNull(testEvents);

	}

}*/