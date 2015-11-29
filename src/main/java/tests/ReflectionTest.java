package tests;

import java.util.LinkedList;

import classmapper.DevisionContext;
import classmapper.HTMLAttribute;
import classmapper.Mapper;
import classmapper.NormalContext;

public class ReflectionTest {
	
	public static void main(String[] args){
		SomeClass s = new SomeClass();
		NormalContext ctx = new NormalContext();
		ctx.setBinding("\r\n"+ctx.getKeyEx()+" - "+ctx.getValueEx());
		ctx.setCustomBinding(true);
		
		//HTMLAttribute attribute = new HTMLAttribute();
		
		//ctx.htmlContext("i", attribute);
		//ctx.setBeforeRow("-#");
		//ctx.setAfterRow("#-\r\n");
	
		Mapper mapper = new Mapper(SomeClass.class);
		mapper.setContext(ctx);
		LinkedList<SomeClass> list = new LinkedList<SomeClass>();
		list.add(s);
		list.add(s);
		list.add(s);
		System.out.println(mapper.map(list));
		//System.out.println(mapper.getMethods());
		//mapper.removeAllExcept("key","a1","a2","a3");
		//System.out.println(mapper.getMethods());
	}
}
