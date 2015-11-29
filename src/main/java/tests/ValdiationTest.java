package tests;

import classmapper.HTMLAttribute;
import classmapper.Mapper;
import classmapper.NormalContext;

public class ValdiationTest {

	private static String a= "";
	private static String b;
	private static String c = "someAasda";
	private static String d = "  ";
	
	public static void main(String[] args){
		if(checkString(c)){
			System.out.println(c);
		}
		
	}
	
	public static boolean checkString(String field){
		System.out.println(field);
		System.out.println("------");
		if(field != null && !(field.trim().equals(""))){
			return true;
		}
		return false;
	}
}
