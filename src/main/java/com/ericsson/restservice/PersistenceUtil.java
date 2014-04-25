package com.ericsson.restservice;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import com.ericsson.entities.Item;
import com.ericsson.entities.User;





public class PersistenceUtil implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("todoList"); 	
	
	
	public static void persist(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction()
		.commit();
		em.close();
	}

	public static void remove(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Object mergedEntity = em.merge(entity);
		em.remove(mergedEntity);
		em.getTransaction().commit();
		em.close();
	}
	
	public static Object merge(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		entity = em.merge(entity);
		em.getTransaction().commit();		
		em.close();
		return entity;
	}

	public static EntityManager createEM() {
		return emf.createEntityManager();
	}
	
	
//	public static List<Event> findAllEvents()
//	{
//		EntityManager em = emf.createEntityManager();
//		List<Event> events = (List<Event>) em.createNamedQuery("Event.findAll").getResultList();
//		em.close();
//		
//		return events;
//	}
//	
//	public static FailureClassErrorTb findFailureClassErrorbyFailure(Failure failure){
//		EntityManager em = emf.createEntityManager();
//		List<FailureClassErrorTb> failError = (List<FailureClassErrorTb>) em.createNamedQuery("FailureClassErrorTb.findbyFailure").setParameter("failure", failure).getResultList();
//		em.close();
//		
//		if (failError.size() == 0)
//			return null;
//		else 
//			return failError.get(0);
//		
//	}
//	
//	
//	public static Failure findFailureByFailureClass(String failureClass)
//	{
//		EntityManager em = emf.createEntityManager();
//		List<Failure> failures = (List<Failure>) em.createNamedQuery("Failure.findByFailureClass").setParameter("failureClass", failureClass).getResultList();
//		em.close();
//		
//		if (failures.size() == 0)
//			return null;
//		else 
//			return failures.get(0);
//	}
//	
//	public static EventCauseErrorTb findEventCauseErrorTbByPK(EventCauseErrorTbPK pk)
//	{
//		EntityManager em = emf.createEntityManager();
//		List<EventCauseErrorTb> ecet = (List<EventCauseErrorTb>) em.createNamedQuery("EventCauseErrorTb.findByEventCauseErrorTbPK").setParameter("EventCauseErrorTbPK", pk).getResultList();
//		em.close();
//		
//		if (ecet.size() == 0)
//			return null;
//		else 
//			return ecet.get(0);
//	}
//	
//	public static MccMncErrorTable findMccMncErrorTbByPK(MccMncErrorTablePK pk)
//	{
//		EntityManager em = emf.createEntityManager();
//		List<MccMncErrorTable> mmet = (List<MccMncErrorTable>) em.createNamedQuery("MccMncErrorTb.findByMccMncPK").setParameter("MccMncErrorTablePK", pk).getResultList();
//		em.close();
//		
//		if (mmet.size() == 0)
//			return null;
//		else 
//			return mmet.get(0);
//	}
//	
//	
//	public static Ue findUeBTac(int tac)
//	{
//		EntityManager em = emf.createEntityManager();
//		List<Ue> ues = (List<Ue>) em.createNamedQuery("Ue.findByTac").setParameter("tac", tac).getResultList();
//		em.close();
//		
//		if (ues.size() == 0)
//			return null;
//		else 
//			return ues.get(0);
//	}
//	
//	public static UeErrorTb findUeErrorTbTac(int tac)
//	{
//		EntityManager em = emf.createEntityManager();
//		List<UeErrorTb> ueError = (List<UeErrorTb>) em.createNamedQuery("UeErrorTb.findByTac").setParameter("tac", tac).getResultList();
//		em.close();
//		
//		if (ueError.size() == 0)
//			return null;
//		else 
//			return ueError.get(0);
//	}
//	
//	public static CellHier findCellHierByCellID(int cellID)
//	{
//		EntityManager em = emf.createEntityManager();
//		List<CellHier> cellhiers = (List<CellHier>) em.createNamedQuery("CellHier.findCellHierbyCellID").setParameter("cellID", cellID).getResultList();
//		em.close();
//		
//		if (cellhiers.size() == 0)
//			return null;
//		else 
//			return cellhiers.get(0);
//	}
//	
////	public static Eventdetail findEventDetailByPK(EventdetailPK id)
////	{
////		EntityManager em = emf.createEntityManager();
////		List<Eventdetail> eventdetails = (List<Eventdetail>) em.createNamedQuery("Eventdetail.findByEventPK").setParameter("id", id).getResultList();
////		em.close();
////		
////		if (eventdetails.size() == 0)
////			return null;
////		else 
////			return eventdetails.get(0);
////	}
//	
//	public static Operator findOperatorByPK(OperatorPK id)
//	{
//		EntityManager em = emf.createEntityManager();
//		List<Operator> operators = (List<Operator>) em.createNamedQuery("Operator.findByOperatorPK").setParameter("id", id).getResultList();
//		em.close();
//		
//		if (operators.size() == 0)
//			return null;
//		else 
//			return operators.get(0);
//	}
//	
//	public static List<Event> findEventIdCauseCodeByImsi(Long imsi)
//	{
//		EntityManager em = emf.createEntityManager();
//		List<Event> events = (List<Event>) em.createNamedQuery("Event.findEventIDCauseCodeByImsi").setParameter("imsi", imsi).getResultList();
//		em.close();
//		
//		if (events.size() == 0)
//			return null;
//		else 
//			return events;
//	}
//	
//	public static List<Event> findUniqueCauseCodeByImsi(Long imsi)
//	{
//		EntityManager em = emf.createEntityManager();
//		List<Event> events = (List<Event>) em.createNamedQuery("Event.findUniqueCauseCodeByImsi").setParameter("imsi", imsi).getResultList();
//		em.close();
//		
//		if (events.size() == 0)
//			return null;
//		else 
//			return events;
//	}
//	
//	
//	public static Account findUserAccount(String username)
//	{
//		EntityManager em = emf.createEntityManager();
//		Account user = null;
//		
//		try {
//			user = (Account) em.createNamedQuery("Account.findUserAccount").setParameter("username", username).getSingleResult();
//		
//		} catch (NoResultException e) {
//			
//		}	
//		em.close();
//		return user;
//	}
	
//	public static int findEnquiryCount(String username)
//	{
//		EntityManager em = emf.createEntityManager();
//		List<Enquiry> count = null;
//		int countSize = 0;
//		try {
//			count = (List<Enquiry>) em.createNamedQuery("Enquiry.count").setParameter("username", username).getResultList();
//
//		} catch (Exception e) {
//			count = null;
//		}
//		em.close();
//		if(count == null){
//			countSize = 0;
//		}else{
//			countSize = count.size();
//		}
//		return countSize;
//	}
	
//	public static boolean doesUserNameExist(String username){
//		boolean result = false;
//		EntityManager em = emf.createEntityManager();
//		try {
//			Account user = (Account) em.createNamedQuery("Account.findUserAccount").setParameter("username", username).getSingleResult();
//			
//			result = true;
//			
//		} catch (NoResultException e) {
//			result = false;
//		}
//		em.close();
//		
//		return result;
//	}
//	
	
	
	
	//------------------------------Adding new persistence -------------------------
	
	
	public static List<Long> findNumberImsifailuresBetweenDates(Date date1, Date date2)
	{
		EntityManager em = emf.createEntityManager();
		List<Long> events = (List<Long>) em.createNamedQuery("Event.findNumberImsifailuresBetweenDates").setParameter("date1", date1).setParameter("date2", date2).getResultList();
		em.close();
		
		if (events.size() == 0)
		{
			
			return events = null;
		}
		else 
			return events;
	}
	
//	public static int findCountByImsiAndDate(long imsi, Date date1, Date date2)
//	{
//		EntityManager em = emf.createEntityManager();
//		List<Event> events = (List<Event>) em.createNamedQuery("Event.findNumberEventsByImsiAndDate").setParameter("imsiLong", imsi)
//				.setParameter("date1", date1).setParameter("date2", date2).getResultList();
//		em.close();
//		//System.out.println("Got here, count is "+count.size());
//		return events.size();
//	}
//
//	public static List<Event> findCountByModelAndDate(String model,
//				java.util.Date date, java.util.Date date1) {
//		
//		List<Event> countByModel = null;
//		EntityManager em = emf.createEntityManager();
//		List<Ue> modelType = em.createNamedQuery("Ue.findTacFromModel").setParameter("model", model).getResultList();
//		
//		if(modelType.isEmpty()){
//			System.out.println("None found!!!!");
//			return countByModel;
//			//Returning null if none found
//		}
//		else{
//		int modelTac = modelType.get(0).getTac();
//		System.out.println("Found TAC is :"+modelTac);
//		
//		countByModel = em.createNamedQuery("Event.findNumberEventsByModelAndDate").setParameter("TAC", modelTac)
//				.setParameter("date", date).setParameter("date1", date1).getResultList();
//		em.close();
//		System.out.println("Got here, count is "+countByModel.size());
//		}
//		return countByModel;
//	}
//	
//	
//	public static List<Event> findDistinctEventIdCauseCodeByImsi(Long imsi)
//	{
//		EntityManager em = emf.createEntityManager();
//		List<Event> events = (List<Event>) em.createNamedQuery("Event.findDistinctCauseCodesByImsi").setParameter("imsi", imsi).getResultList();
//		em.close();
//		
//		if (events.size() == 0)
//			return null;
//		else 
//			return events;
//	}
//	
//	public static List<Long> findEventByFailureClass(String failureClass)
//	{
//		EntityManager em = emf.createEntityManager();
//		Failure failure = (Failure) em.createNamedQuery("Failure.findByFailureClass").setParameter("failureClass", failureClass).getSingleResult();
//		List<Long> events = null;
//		try {
//			if(failure != null)
//			{
//				events = (List<Long>) em.createNamedQuery("Event.findEventByFailureCauseClass").setParameter("failure", failure).getResultList();
//			}
//		} catch (NoResultException e) {
//			// TODO: handle exception
//			events = null;
//		}
//		
//		em.close();
//		
//		if (events.size() == 0 || events == null)
//			return null;
//		else 
//			return events;
//	}
	
	
//	public static List<Enquiry> findEnquiryForUser(String username)
//	{
//		EntityManager em = emf.createEntityManager();
//		List<Enquiry> history = null;
//		try {
//			history = (List<Enquiry>) em.createNamedQuery("Enquiry.count").setParameter("username", username).getResultList();
//
//		} catch (NoResultException e) {
//			history = null;
//		}
//		em.close();
//		
//		return history;
//	}
	
}

