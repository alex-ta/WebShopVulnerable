package tests;

import java.util.LinkedList;

import classmapper.Mapper;
import classmapper.NormalContext;
import database.tables.Print;
import database.tables.Product;
import database.tables.Textil;

public class ProductTest {

	public static void main(String[] args){
		Product product = new Product();
		
		
		LinkedList<Product> list = new LinkedList<Product>();
		list.add(product);
		list.add(product);
		Print print = new Print();
		print.setImgid("/img.png");
		print.setName("Some Print");
		product.addPrint(print);
		Textil textil = new Textil();
		textil.setName("T shirt");
		textil.setImgid("/add/new.png");
		product.setTextil(textil);
		
		System.out.println(product.toObject());
	}
	
}
