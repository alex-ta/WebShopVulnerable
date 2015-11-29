package controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ErrorController {
	//for 403 access denied page
	@RequestMapping(value = "error/403", method = RequestMethod.GET)
	public ModelAndView accessDenied() {

	  ModelAndView model = new ModelAndView();
		
	  //check if user is login
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (auth.getAuthorities().toString().contains("ROLE_USER")) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();	
		model.addObject("username", userDetail.getUsername());
	  }
		
	  model.setViewName("error/403");
	  return model;

	}
	
	
	//for 403 access denied page
	@RequestMapping(value = "error/default", method = RequestMethod.GET)
	public ModelAndView notFound() {
	  ModelAndView model = new ModelAndView();
	  model.setViewName("error/404");
	  return model;

	}

}
