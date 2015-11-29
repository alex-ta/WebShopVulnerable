package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import database.dao.ChargeDao;
import database.dao.ProductDao;
import database.extension.DatabaseException;
import database.mapper.SpringUser;
import database.tables.Address;
import database.tables.Order;
import database.tables.Invoice;
import database.tables.Product;
import pojo.basket.Basket;

	@Controller
	public class AddCharge {
		
		@Autowired
		private ChargeDao dao;
		@Autowired
		private ProductDao pDao;
		
		@RequestMapping(value = "/charge", method = RequestMethod.GET)
		public ModelAndView charge(){
			Product product = null;
			try {
				product = pDao.selectAll().get(0);
			} catch (DatabaseException e1) {
				e1.printStackTrace();
			}
			Authentication a = SecurityContextHolder.getContext().getAuthentication();
		    SpringUser currentUser = (SpringUser) a.getPrincipal();
		    System.out.println(currentUser);
		    Basket b = currentUser.getBasket();
			System.out.println(b);
			// product counter not working
			b.addProduct(product);
			b.addProduct(product);
			b.addProduct(product);		
			Invoice v = b.order(new Address(), new Address());
			Order charge = v.getOrder();
			try {
				dao.save(charge);
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ModelAndView model = new ModelAndView();
			return model;
		}	
	
	}
