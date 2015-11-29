package classmapper;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Mapper {
	
	private LinkedList<Method> methods = new LinkedList<Method>();
	private MapperContext context;
	
	public Mapper(Class<?> some){
		Method[] allMethods = some.getDeclaredMethods();
		for(int i=0; i < allMethods.length; i++){
			if(allMethods[i].getName().contains("get")){
			methods.add(allMethods[i]);
			}
		}
	}
	
	public Mapper(Mapper mapper){
		this.methods = new LinkedList<Method>();
		for(Method m : mapper.getMethods()){
			this.methods.add(m);
		}
	}
	
	public MapperContext getContext() {
		return context;
	}

	public void setContext(MapperContext context) {
		this.context = context;
	}
	
	protected List<Method> getMethods(){
		return methods;
	}
	
	public void removeVariable(String name){
		String variable = "get"+name.substring(0,1).toUpperCase()+name.substring(1);
		for(int i=0; i<methods.size(); i++){
			if(methods.get(i).getName().equals(variable)){
				methods.remove(i);
				break;
			}
		}
	}
	
	public void removeAll(String...names){
		String[] variables = variablesToGet(names);
		for(int i=0; i<variables.length; i++){
			removeVariable(names[i]);
		}
	}
	
	private String[] variablesToGet(String...variables){
		String [] getters = new String[variables.length];
		for(int i=0; i< variables.length; i++){
			getters[i] = "get"+variables[i].substring(0,1).toUpperCase()+variables[i].substring(1);	
		}
		return getters;
	}
	
	public void removeAllExcept(String...names){
		String [] variables = variablesToGet(names);
		for(int i=0; i<methods.size(); i++){
			String methode = methods.get(i).getName();
			boolean remove = true;
			for(int j =0; j<variables.length; j++){
				if(methode.equals(variables[j])){
					remove = false;
				}	
			}
			if(remove){
				methods.remove(i);
				i--;
			}
		}
	}
	
	public Map<String,String> mapRaw(Object obj){
		Map<String,String> keyValue = new HashMap<String,String>();
		if(obj == null){
			return keyValue;
		}
		for(int i=0; i<methods.size();i++){
			try {
				keyValue.put(methods.get(i).getName().replaceAll("get", ""),""+methods.get(i).invoke(obj));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return keyValue;
	}
	

	public String map(Object obj){
		String[] keys = new String[methods.size()];
		String[] values = new String[methods.size()];
		
		for(int i=0; i<methods.size();i++){
			try {
				keys[i] = methods.get(i).getName().replaceAll("get", "");
				values[i] = (String) methods.get(i).invoke(obj);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	return this.context.map(keys, values);
	}
	
	public String map(Object...obj){
		LinkedList<Map<String,String>> list = new LinkedList<Map<String,String>>();
		for(Object o: obj){
			list.add(this.mapRaw(o));
		}
		return context.map(list);
	}
	
	public String map(List<?> obj){
		LinkedList<Map<String,String>> list = new LinkedList<Map<String,String>>();
		for(Object o: obj){
		list.add(this.mapRaw(o));
		}
		return context.map(list);
	}
	
}
