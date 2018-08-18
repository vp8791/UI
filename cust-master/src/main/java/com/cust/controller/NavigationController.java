package com.cust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Main Controller which routes to index page.
 * 
 * @author murali
 *
 */
@Controller
public class NavigationController {
	
	/**
	 *  returns index as ModelAndView object.
	 *  
	 * @return ModelAndView
	 */
	@RequestMapping(value = { "/", "index" }, method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("index");
	}

}
