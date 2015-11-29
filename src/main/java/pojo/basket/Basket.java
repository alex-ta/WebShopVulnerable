package pojo.basket;

import java.io.Serializable;
import java.util.Calendar;
import database.tables.*;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import database.tables.Address;
import database.tables.Order;
import database.tables.Invoice;
import database.tables.Product;
import database.tables.User;

public class Basket implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 81015L;
	
	private Map<Product,Integer> products;
	private int price;
	private User user;
	
	private Date timeTillPayment(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);
		return cal.getTime();
	}
	
	public Basket(User user){
		products = new HashMap<Product,Integer>();
		price = 0;
		this.user = user;
	}
	
	private void updatePrice(){
		price = 0;
		for(Product p: products.keySet()){
			int bprice = p.getSellingprice();
			int btimes = products.get(p);
			if(bprice > 0 && btimes > 0){
				price += (btimes*bprice);
			}
		}
	}
	
	public int getPrice(){
		updatePrice();
		return price;
	}
	
	public void addProduct(Product product,int times){
		if(products.containsKey(product)){
			products.put(product, products.get(product)+times);
		}else{
			products.put(product, times);
		}
	}
	
	public void addProduct(Product product){
		addProduct(product,1);
	}
	
	public void setProduct(Product product, int times){
		products.put(product, times);
	}
	
	public void removeProduct(Product product){
		products.remove(product);
	}

	public Invoice order(Address orderAdress,Address invoiceAddress){
		Order charge = new Order();
		charge.setProducts(this.products);
		charge.setAddress(orderAdress);
		charge.setSellingprice(getPrice());
		charge.setUser(user);
		Invoice invoice = new Invoice();
		invoice.setAddress(invoiceAddress);
		invoice.setOrder(charge);
		invoice.setPayload(getPrice());
		invoice.setOvertime(timeTillPayment());
		invoice.setOrder(charge);
		return invoice;
	}
}
