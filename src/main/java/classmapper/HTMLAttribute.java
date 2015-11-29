package classmapper;

import java.util.HashMap;

public class HTMLAttribute {

	private HashMap<String,String> key;
	private HashMap<String,String> value;
	private HashMap<String,String> wrapper;
	
	public HTMLAttribute(){
		this.key = new HashMap<String,String>();
		this.value = new HashMap<String,String>();
		this.wrapper = new HashMap<String,String>();
	}
	
	private void addKeyAttr(String attributeName, String attributeValue){
		String attrValue = attributeValue;
		if(this.key.get(attributeName) != null){
			attrValue = this.key.get(attributeName) +" "+ attrValue;  
		}
		this.key.put(attributeName, attrValue);	
	}
	
	private void addValueAttr(String attributeName, String attributeValue){
		String attrValue = attributeValue;
		if(this.value.get(attributeName) != null){
			attrValue = this.value.get(attributeName) + attrValue;  
		}
		this.value.put(attributeName, attrValue);		
	}
	
	private void addWrapperAttr(String attributeName, String attributeValue){
		String attrValue = attributeValue;
		if(this.wrapper.get(attributeName) != null){
			attrValue = this.wrapper.get(attributeName) + attrValue;  
		}
		this.wrapper.put(attributeName, attrValue);		
	}
	
	public void addKeyAttribute(String attributeName, String attributeValue){
		this.addKeyAttr(attributeName, attributeValue);
	}
	public void addValueAttribute(String attributeName, String attributeValue){
		this.addValueAttr(attributeName, attributeValue);
	}
	public void addWrapperAttribute(String attributeName, String attributeValue){
		this.addWrapperAttr(attributeName, attributeValue);
	}
	public void addAttribute(String attributeName, String attributeValue){
		this.addValueAttr(attributeName, attributeValue);
		this.addKeyAttr(attributeName, attributeValue);
	}
	
	
	public String getKeyAttributes(){
		StringBuilder builder = new StringBuilder();
		for(String k : key.keySet()){
			builder.append(k);
			builder.append("='");
			builder.append(key.get(k));
			builder.append("' ");
		}
		return builder.toString();
	}
	
	public String getWrapperAttributes(){
		StringBuilder builder = new StringBuilder();
		for(String k : wrapper.keySet()){
			builder.append(k);
			builder.append("='");
			builder.append(wrapper.get(k));
			builder.append("' ");
		}
		return builder.toString();
	}
	
	public String getValuesAttributes(){
		StringBuilder builder = new StringBuilder();
		for(String k : value.keySet()){
			builder.append(k);
			builder.append("='");
			builder.append(value.get(k));
			builder.append("' ");
		}
		return builder.toString();
	}
}
