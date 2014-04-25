///*package com.ericsson.RestJPA.test;
//import java.io.File;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.UserTransaction;
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.WebArchive;
//import org.jboss.shrinkwrap.resolver.api.ResolutionException;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//@RunWith(Arquillian.class)
//public class MemberRegistrationTest {
//    @Deployment
//    public static WebArchive war() {
//        File[] hibernate;
//        try { // try offline first since it is generally faster
//            hibernate = Maven.resolver()
//                    .offline(true)
//                    .loadPomFromFile("src/test/resources/hibernate-pom.xml")
//                    .importRuntimeAndTestDependencies(new AcceptScopesStrategy(ScopeType.COMPILE, ScopeType.RUNTIME))
//                    .asFile();
//        } catch (ResolutionException re) { // try on central
//            hibernate = Maven.resolver()
//                    .loadPomFromFile("src/test/resources/hibernate-pom.xml")
//                    .importRuntimeAndTestDependencies(new AcceptScopesStrategy(ScopeType.COMPILE, ScopeType.RUNTIME))
//                    .asFile();
//        }
//
//        WebArchive webArchive =  ShrinkWrap.create(WebArchive.class, "hibernate-app.war")
//                  .addAsWebInfResource("test-persistence.xml", "classes/META-INF/persistence.xml")   
//                  .addAsWebInfResource(EmptyAsset.INSTANCE, "classes/META-INF/beans.xml")
//                .addAsLibraries(hibernate)
//                .addAsLibraries(JarLocation.jarLocation(ResolutionException.class))
//                .addClasses(Game.class)
//                .addAsLibraries(JarLocation.jarLocation(org.jboss.shrinkwrap.resolver.api.maven.filter.MavenResolutionFilter.class));
//
//
//        System.out.println(webArchive.toString(true));
//        return webArchive;
//    }
//
//    private static final String[] GAME_TITLES = {
//        "Super Mario Brothers",
//        "Mario Kart",
//        "F-Zero"
//    };
//
//    @PersistenceContext(unitName = "yasharUnit")
//    EntityManager em;           
//
//    @Inject
//    UserTransaction utx;
//
//    @Before
//    public void preparePersistenceTest() throws Exception {
//        clearData();
//        insertData();
//        startTransaction();
//    }
//
//    private void clearData() throws Exception {
//        utx.begin();
//        em.joinTransaction();
//        System.out.println("Dumping old records...");
//        em.createQuery("delete from Game").executeUpdate();
//        utx.commit();
//    }
//
//    private void insertData() throws Exception {
//        utx.begin();
//        em.joinTransaction();
//        System.out.println("Inserting records...");
//        for (String title : GAME_TITLES) {
//            Game game = new Game(title);
//            em.persist(game);
//        }
//        utx.commit();
//        em.clear();
//    }
//
//    private void startTransaction() throws Exception {
//        utx.begin();
//        em.joinTransaction();
//    }
//
//    @After
//    public void commitTransaction() throws Exception {
//        utx.commit();
//    }
//
//    @Test
//    public void shouldFindAllGamesUsingJpqlQuery() throws Exception {
//        // given
//        String fetchingAllGamesInJpql = "select g from Game g order by g.id";
//
//        // when
//        System.out.println("Selecting (using JPQL)...");
//        List<Game> games = em.createQuery(fetchingAllGamesInJpql, Game.class).getResultList();
//
//        // then
//        System.out.println("Found " + games.size() + " games (using JPQL):");
//        assertContainsAllGames(games);
//    }
//
//
//    private static void assertContainsAllGames(Collection<Game> retrievedGames) {
//        Assert.assertEquals(GAME_TITLES.length, retrievedGames.size());
//        final Set<String> retrievedGameTitles = new HashSet<String>();
//        for (Game game : retrievedGames) {
//            System.out.println("* " + game);
//            retrievedGameTitles.add(game.getTitle());
//        }
//        Assert.assertTrue(retrievedGameTitles.containsAll(Arrays.asList(GAME_TITLES)));
//    }
//}*/
//
//
///**
// * JBoss, Home of Professional Open Source
// * Copyright 2012, Red Hat, Inc. and/or its affiliates, and individual
// * contributors by the @authors tag. See the copyright.txt in the 
// * distribution for a full listing of individual contributors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// * http://www.apache.org/licenses/LICENSE-2.0
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,  
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.ericsson.RestJPA.arquilian;
//
//import static org.junit.Assert.*;
//
//import org.junit.Assert.*;
//
//import java.io.File;
//
//import javax.ejb.EJB;
//import javax.inject.Inject;
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.logging.Logger;
//import org.jboss.shrinkwrap.api.Archive;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.importer.ZipImporter;
//import org.jboss.shrinkwrap.api.spec.WebArchive;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.rules.TestName;
//import org.junit.runner.RunWith;
//
//import com.ericsson.dao.UserDAO;
//import com.ericsson.dao.ItemDAO;
//import com.ericsson.entities.Account;
//import com.ericsson.entities.CellHier;
//import com.ericsson.interfaces.IUserDAO;
//import com.ericsson.service.ServiceEJB;
//
//@RunWith(Arquillian.class)
//public class MemberRegistrationTest {
//	
//	private final static String accountTypeAdmin="SysAdmin";
//	private final static String userName="testuserName";
//	private final static String name="testname";
//	private final static String password="testpassword";
//	private final static String email="email@email.ie";
//	private final static String accountTypeAdmin_x="SysAdmin_x";
//	private final static String userName_x="testuserName_x";
//	private final static String name_x="testname_x";
//	private final static String password_x="testpassword_x";
//	private final static String email_x="email@email.ie_X";
//		
//    @Deployment
//    public static Archive<?> createTestArchive() {
//    	
//    	/*MavenDependencyResolver resolver = DependencyResolvers.use(
//    			MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
//    	MavenDependencyResolver resolver1 = DependencyResolvers.use(
//    			MavenDependencyResolver.class).loadMetadataFromPom("pom1.xml");
//		//return null;
//    	
//    	File[] lib1 = Maven.resolver()  
//    		    .loadPomFromFile("pom.xml").resolve("net.sourceforge.jexcelapi:jxl:2.6")  
//    		    .withTransitivity().asFile();
//    	File[] lib2 = Maven.resolver().loadPomFromFile("pom.xml").resolve("com.googlecode.htmleasy:htmleasy")
//    	   .withTransitivity().as(File.class);
//    	File[] lib3 = Maven.resolver().loadPomFromFile("pom.xml").resolve("com.google.gson:gson:2.2.4")
//    	    	   .withTransitivity().asFile();*/
//    	
//    	
//    	
//    	
//    	
//    	return ShrinkWrap.create(ZipImporter.class, "restJPA-test.war")
//    	                     .importFrom(new File("target/restJPA-test.war"))
//    	                     .as(WebArchive.class)
//     .addAsResource("META-INF/test-register.xml", "META-INF/persistence.xml")
//     .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
//     .addAsWebInfResource("test-register.xml")
//     .addPackage("com.ericsson.RestJPA.arquilian");
//    	
//        /*return ShrinkWrap.create(WebArchive.class, "test.war")
//                .addClasses(Account.class, AccountDAO.class, IAccountDAO.class, 
//                		PersistenceUtil.class, ServiceEJB.class, 
//                		IServiceEJB.class, IEventDAO.class, IEnquiryDAO.class, 
//                		IUeDAO.class, Event.class).
//                		addPackages(true, "com.ericsson.dao", "com.ericsson.entities", "com.ericsson.interfaces",
//                				"com.ericsson.jxl", "com.ericsson.other",
//                				"com.ericsson.service", "com.ericsson.uploadpath", "com.google.gson")
//                .addAsResource("META-INF/test-register.xml", "META-INF/persistence.xml")
//                //.addAsLibraries(resolver.artifact("com.googlecode.htmleasy:htmleasy:0.7").resolveAsFiles())
//                //.addAsLibraries(resolver.artifact("com.google.gson:gson:2.0").resolveAsFiles())
//                .addAsLibraries(resolver.artifact("net.sourceforge.jexcelapi:jxl:2.6").resolveAsFiles())
//                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
//                // Deploy our test datasource
//                .addAsWebInfResource("test-register.xml");*/
//    }
//
//    @Inject
//    Account account, accountFromDB;
//    @Inject
//    CellHier cellHier;
//  
//    /*@Inject 
//    ServiceEJB serviceEjb;*/
//
//    @EJB
//	ServiceEJB serviceEjb;
//    @EJB
//    ServiceEJB_TestingUnit serviceEJB_TestingUnit;
//        
//    @Inject
//    UserDAO accountDAO;
//    @Inject
//    ItemDAO cellHierDAO;
//    
//    /*@Inject
//    Logger log;*/
//
//    @Before
//    public void setupAccount(){
//    	//Account account = new Account();
//		account.setAccounttype(accountTypeAdmin);
//		account.setName(name);
//		account.setPassword(password);
//		account.setUserEmail(email);
//		account.setUsername(userName);
//    }
//    
//    @Test
//    public void register_newUser() throws Exception {
//    	
//		//accountDAO.removeAccount(account);
//		
//		String newAccount = "Account Is Created";
//		String existingAccount = "Account Already Exist";
//		
//		String newAccount_x = "Account Is Created_x";
//		String existingAccount_x = "Account Already Exist_x";
//		
//		accountFromDB = serviceEjb.searchforAccountUsernameAndPassword(userName, password);
//		
//		String accountExist = serviceEjb.addNewAccount(account);
//				System.out.println(accountFromDB.toString());
//		if(accountFromDB.getUsername().equals(userName)&&accountFromDB.getPassword().equals(password)){
//			assertEquals(existingAccount, accountExist);
//			assertNotSame(existingAccount_x, accountExist);
//			//log.info("Registering existing user: \n"+account.toString()+"\n with status: "+accountExist+"\n");
//			
//		}else{
//			assertEquals(newAccount, accountExist);
//			assertNotSame(newAccount_x, accountExist);
//			//log.info("Registering new user: \n"+account.toString()+"\n with status: "+accountExist+"\n");
//			
//		}
//	
//	}
//    
//    @Test
//    public void retrieve_existingUser () throws Exception{
//
//	accountFromDB = serviceEjb.searchforAccountUsernameAndPassword(userName, password);
//    assertEquals(accountFromDB.getUsername(), userName);
//    assertEquals(accountFromDB.getPassword(), password);
//    assertEquals(accountFromDB.getName(), name);
//    assertEquals(accountFromDB.getUserEmail(), email);
//    assertEquals(accountFromDB.getAccounttype(), accountTypeAdmin);
//
//
//    assertNotSame(accountFromDB.getUsername(), userName_x);
//    assertNotSame(accountFromDB.getPassword(), password_x);
//    assertNotSame(accountFromDB.getName(), name_x);
//    assertNotSame(accountFromDB.getUserEmail(), email_x);
//    assertNotSame(accountFromDB.getAccounttype(), accountTypeAdmin_x);
//    }
//    
//    
//    @Test
//    public void testAddCellHier() throws Exception{
//
//	cellHier.setCellID(10);
//	cellHier.setHier3_id(Long.parseLong("5101480358281000000"));
//	cellHier.setHier32_id(Long.parseLong("4462714857032800000"));
//	cellHier.setHier321_id(Long.parseLong("1650127677358040000"));
//
//	CellHier ch = serviceEJB_TestingUnit.testAddCellHier(cellHier);
//
//	assertEquals(ch.getCellID(), 10);
//	assertEquals(ch.getHier3_id(), Long.parseLong("5101480358281000000"));
//	assertEquals(ch.getHier32_id(), Long.parseLong("4462714857032800000"));
//	assertEquals(ch.getHier32_id(), Long.parseLong("4462714857032800000"));
//
//	assertNotSame(ch.getCellID(), 2);
//	assertNotSame(ch.getHier3_id(), Long.parseLong("5101480358281000001"));
//	assertNotSame(ch.getHier32_id(), Long.parseLong("4462714857032800001"));
//	assertNotSame(ch.getHier32_id(), Long.parseLong("4462714857032800001"));
//	}
//    
//    
//    /*
//    public void testFindAllCellHier() throws Exception{
//    	CellHier cellHier1 = new CellHier();
//    	cellHier1.setCellID(1);
//    	cellHier1.setHier3_id(Long.parseLong("5101480358281000000"));
//    	cellHier1.setHier32_id(Long.parseLong("4462714857032800000"));
//    	cellHier1.setHier321_id(Long.parseLong("1650127677358040000"));
//    	cellHierDao.addCellHier(cellHier1);
//
//    	CellHier cellHier2 = new CellHier();
//    	cellHier2.setCellID(2);
//    	cellHier2.setHier3_id(Long.parseLong("5337549188380290000"));
//    	cellHier2.setHier32_id(Long.parseLong("5546490654156790000"));
//    	cellHier2.setHier321_id(Long.parseLong("4931866955053780000"));
//    	cellHierDao.addCellHier(cellHier2);
//
//    	List<CellHier> cellHierList = cellHierDao.findAllCellHier();
//
//    	assertEquals(cellHierList.get(0).getCellID(), 1);
//    	assertEquals(cellHierList.get(0).getHier3_id(), Long.parseLong("5101480358281000000"));
//    	assertEquals(cellHierList.get(0).getHier32_id(), Long.parseLong("4462714857032800000"));
//    	assertEquals(cellHierList.get(0).getHier32_id(), Long.parseLong("4462714857032800000"));
//
//    	assertEquals(cellHierList.get(1).getCellID(), 2);
//    	assertEquals(cellHierList.get(1).getHier3_id(), Long.parseLong("5337549188380290000"));
//    	assertEquals(cellHierList.get(1).getHier32_id(), Long.parseLong("5546490654156790000"));
//    	assertEquals(cellHierList.get(1).getHier32_id(), Long.parseLong("4931866955053780000"));
//
//
//    	assertNotSame(cellHierList.get(0).getCellID(), 5);
//    	assertNotSame(cellHierList.get(0).getHier3_id(), Long.parseLong("5101480358281000001"));
//    	assertNotSame(cellHierList.get(0).getHier32_id(), Long.parseLong("4462714857032800001"));
//    	assertNotSame(cellHierList.get(0).getHier32_id(), Long.parseLong("4462714857032800001"));
//
//    	assertNotSame(cellHierList.get(1).getCellID(), 5);
//    	assertNotSame(cellHierList.get(1).getHier3_id(), Long.parseLong("5337549188380290001"));
//    	assertNotSame(cellHierList.get(1).getHier32_id(), Long.parseLong("5546490654156790001"));
//    	assertNotSame(cellHierList.get(1).getHier32_id(), Long.parseLong("4931866955053780001"));
//    	}
//
//
//    	public void testAddEventdetailAndFindEventdetail() throws Exception{
//
//    	EventdetailPK id = new EventdetailPK();
//    	id.setCauseCode(0);
//    	id.setEventID(4097);
//
//    	Eventdetail ed = new Eventdetail();
//    	ed.setId(id);
//    	ed.setDescription("RRC CONN SETUP-SUCCESS");
//
//    	eventDetailDao.addEventdetail(ed);
//
//    	Eventdetail edDB = eventDetailDao.findEventDetailByPK(new EventdetailPK(0, 4097));
//
//    	assertEquals(edDB.getId().getCauseCode(), 0);
//    	assertEquals(edDB.getId().getEventID(), 4097);
//    	assertEquals(edDB.getDescription(), "RRC CONN SETUP-SUCCESS");
//
//    	assertNotSame(edDB.getId().getCauseCode(), 100);
//    	assertNotSame(edDB.getId().getEventID(), 2033);
//    	assertNotSame(edDB.getDescription(), "Hello world");
//
//    	Eventdetail edDB1 = eventDetailDao.findEventDetailByPK(new EventdetailPK(100, 4097));
//    	assertNull(edDB1); 
//    	}
//
//
//    	public void testaddUeAndFindUe() throws Exception{
//
//    	Ue ue = new Ue();
//    	ue.setTac(100100);
//    	ue.setMarketingName("G410");
//    	ue.setManufacturer("Mitsubishi");
//    	ue.setAccessCability("GSM 1800, GSM 900");
//    	ue.setModel("G410");
//    	ue.setOs("(null)");
//    	ue.setInputMode("(null)");
//    	ue.setUEType("(null)");
//
//    	ueDao.addUe(ue);
//
//    	Ue ueDB = ueDao.findUeBTac(100100);
//    	assertEquals(ueDB.getTac(), 100100);
//    	assertEquals(ueDB.getMarketingName(), "G410");
//    	assertEquals(ueDB.getManufacturer(), "Mitsubishi");
//    	assertEquals(ueDB.getAccessCability() , "GSM 1800, GSM 900");
//    	assertEquals(ueDB.getModel() , "G410");
//    	assertEquals(ueDB.getOs() , "(null)");
//    	assertEquals(ueDB.getInputMode(), "(null)");
//    	assertEquals(ueDB.getInputMode(), "(null)");
//
//    	assertNotSame(ueDB.getTac(), 100101);
//    	assertNotSame(ueDB.getMarketingName(), "A410");
//    	assertNotSame(ueDB.getManufacturer(), "MitsubishY");
//    	assertNotSame(ueDB.getAccessCability() , "GSM 2000, GSM 800");
//    	assertNotSame(ueDB.getModel() , "A410");
//    	assertNotSame(ueDB.getOs() , "(noll)");
//    	assertNotSame(ueDB.getInputMode(), "(noll)");
//    	assertNotSame(ueDB.getInputMode(), "(noll)");
//
//
//    	Ue ueDB1 = ueDao.findUeBTac(1);
//    	assertNull(ueDB1); 
//    	}*/
//    
//    
//    
//    
//}
