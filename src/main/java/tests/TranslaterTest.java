package tests;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import database.mapper.AuthorityTranslater;


public class TranslaterTest {

	public static void main(String [] args){
		
		ApplicationContext context = new FileSystemXmlApplicationContext(
				"src/main/webapp/WEB-INF/configs/spring-security.xml");	
		AuthorityTranslater obj = (AuthorityTranslater) context.getBean("authorityTranslater");
		System.out.println(obj.decorateAuthoriy("ROLE_USER"));
	}
}