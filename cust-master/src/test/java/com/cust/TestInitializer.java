package com.cust;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cust.init.Initializer;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { Initializer.class }, loader = AnnotationConfigWebContextLoader.class)
public class TestInitializer {

	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * Tests applicationContext, is loaded successfully or not
	 * 
	 */
	@Test
	public void testWiredDependencies() {
		assertNotNull(applicationContext);
	}

}
