package com.cust;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cust.init.Initializer;

/**
 * Integration test to be executed. This will be executed after war file is deployed in tomcat.
 * 
 * Any UI related tests can be performed here
 * 
 *
 * @author murali
 *
 */
//TODO: UI related tests using selenium or any other UI tool using URL http://localhost:8080/cust-master/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { Initializer.class }, loader = AnnotationConfigWebContextLoader.class)
public class ITCustNotes {

	@Autowired
	private ApplicationContext applicationContext;

	
	//TODO: Test after deployment of the web application
	@Test
	public void testAfterDeployment() {
		assertNotNull(applicationContext);
	}

}
