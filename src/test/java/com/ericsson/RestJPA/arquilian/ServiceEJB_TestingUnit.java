//package com.ericsson.RestJPA.arquilian;
//
//
//
//import java.math.BigInteger;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import javax.ejb.Local;
//import javax.ejb.Stateless;
//import javax.ejb.TransactionAttribute;
//import javax.ejb.TransactionAttributeType;
//import javax.inject.Inject;
//import javax.jws.WebService;
//import javax.persistence.NoResultException;
//
//import com.ericsson.dao.ItemDAO;
//import com.ericsson.entities.Account;
//import com.ericsson.entities.CellHier;
//import com.ericsson.entities.Enquiry;
//import com.ericsson.entities.Event;
//import com.ericsson.entities.EventErrorTb;
//import com.ericsson.entities.Ue;
//import com.ericsson.interfaces.IUserDAO;
//import com.ericsson.interfaces.IEnquiryDAO;
//import com.ericsson.interfaces.IEventDAO;
//import com.ericsson.interfaces.IEventErrorDAO;
//import com.ericsson.interfaces.IUeDAO;
//import com.ericsson.other.Count;
//import com.ericsson.other.Top10Failure;
//
//
//
//@Stateless
//@WebService (endpointInterface= "com.ericsson.RestJPA.arquilian.IServiceEJB_TestingUnit")
//@Local(IServiceEJB_TestingUnit.class)
//@TransactionAttribute(TransactionAttributeType.REQUIRED)
//public class ServiceEJB_TestingUnit implements IServiceEJB_TestingUnit {
//	
//	@Inject
//	private IUserDAO accountDAO;
//	
//	@Inject
//	private IEventDAO eventDAO;
//	
//	@Inject
//	private IEnquiryDAO enquiryDAO;
//	
//	@Inject
//	private IUeDAO ueDAO;
//	
//	@Inject
//	private IEventErrorDAO eventErrorDAO;
//	
//	
//
//	/*@Inject
//    CellHier cellHier;*/
//	@Inject
//    ItemDAO cellHierDAO;
//	
//	public CellHier testAddCellHier(CellHier cellHier) throws Exception{
//		cellHierDAO.addCellHier(cellHier);
//		return cellHierDAO.findCellHierByCellID(1);
//	}
//	
//
//}
//
