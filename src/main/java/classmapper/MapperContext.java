package classmapper;

import java.util.List;
import java.util.Map;

public abstract class MapperContext {

	protected String keyEx;
	protected String keyExLower;
	protected String keyExUpper;
	protected String valueEx;
	protected String valueExLower;
	protected String valueExUpper;
	protected String binding;
	protected int fieldCount;
	protected String divideFields;
	protected String beforeKey;
	protected String afterKey;
	protected String beforeValue;
	protected String afterValue;
	protected String beforeRow;
	protected String afterRow;
	protected String beforeWrapper;
	protected String afterWrapper;
	protected String beforeColor;
	protected String afterColor;
	protected String firstElementKey;
	protected String firstElementValue;
	protected String lastElementKey;
	protected String lastElementValue;
	protected boolean firstElement;
	protected boolean lastElement;
	protected boolean customBinding;
	
	
	
	public MapperContext(){
		customBinding = false;
		keyEx = "KEY";
		keyExLower = "KEYL";
		keyExUpper = "KEYU";
		valueEx = "VALUE";
		valueExLower = "VALUEL";
		valueExUpper = "VALUEU";
		afterKey = " :";
		lastElementKey = lastElementValue = firstElementKey = firstElementValue = beforeRow = afterRow = beforeWrapper = afterWrapper = beforeKey = beforeValue = afterValue = "";
		divideFields = "\r\n";
		fieldCount = 2;
	}
	
	
	public void htmlContext(String element){
		if(element.equals("table")){
			beforeValue = "<td>";
			afterValue = "</td>";
			beforeKey = "<th>";
			afterValue = "</th>";
			beforeRow = "<tr>";
			afterRow = "</tr>";
			beforeWrapper = "<table>";
			afterWrapper = "</table>";
		}else{
			beforeKey = beforeValue = "<"+element+">";
			afterKey = afterValue = "</"+element+">";
			divideFields = "";
		}
	}
	
	public void htmlContext(String element,HTMLAttribute attribute){
		if(element.equals("table")){
			beforeValue = "<td "+attribute.getValuesAttributes()+">";
			afterValue = "</td>";
			beforeColor = "<span"+attribute.getValuesAttributes()+" style = 'height: 20px; width: 20px; float: left; margin-right:10px; background-color:#"; 
			afterColor = "' width='10px' height='10px'></span>";
			beforeKey = "<th "+attribute.getKeyAttributes()+">";
			afterValue = "</th>";
			beforeRow = "<tr>";
			afterRow = "</tr>";
			beforeWrapper = "<table "+attribute.getWrapperAttributes()+">";
			afterWrapper = "</table>";
		}else{
			beforeKey  = "<"+element+" "+attribute.getKeyAttributes()+">";
		    beforeValue = "<"+element+" "+attribute.getValuesAttributes()+">";
			afterKey = afterValue = "</"+element+">";
			divideFields = ""; 
		}
	}
	
	
	
	protected abstract void updateBinding();
	
	public abstract String map(String key,String value);
	
	public abstract String map(String [] keys, String [] values);
	
	public abstract String map(List<Map<String,String>> list);	
	
	public String getKeyEx() {
		return keyEx;
	}

	public void setKeyEx(String keyEx) {
		this.keyEx = keyEx;
	}

	public String getValueEx() {
		return valueEx;
	}

	public void setValueEx(String valueEx) {
		this.valueEx = valueEx;
	}

	public String getBinding() {
		updateBinding();
		return binding;
	}

	public void setBinding(String binding) {
		this.binding = binding;
	}

	public int getFieldCount() {
		return fieldCount;
	}

	public void setFieldCount(int fieldCount) {
		this.fieldCount = fieldCount;
	}

	public String getDivideFields() {
		return divideFields;
	}

	public void setDivideFields(String divideFields) {
		this.divideFields = divideFields;
	}

	public String getBeforeKey() {
		return beforeKey;
	}

	public void setBeforeKey(String beforeKey) {
		this.beforeKey = beforeKey;
	}

	public String getAfterKey() {
		return afterKey;
	}

	public void setAfterKey(String afterKey) {
		this.afterKey = afterKey;
	}

	public String getBeforeValue() {
		return beforeValue;
	}

	public void setBeforeValue(String beforeValue) {
		this.beforeValue = beforeValue;
	}

	public String getAfterValue() {
		return afterValue;
	}

	public void setAfterValue(String afterValue) {
		this.afterValue = afterValue;
	}

	public boolean isCustomBinding() {
		return customBinding;
	}

	public void setCustomBinding(boolean customBinding) {
		this.customBinding = customBinding;
	}

	public String getBeforeRow() {
		return beforeRow;
	}

	public void setBeforeRow(String beforeRow) {
		this.beforeRow = beforeRow;
	}

	public String getAfterRow() {
		return afterRow;
	}

	public void setAfterRow(String afterRow) {
		this.afterRow = afterRow;
	}

	public String getBeforeWrapper() {
		return beforeWrapper;
	}

	public void setBeforeWrapper(String beforeWrapper) {
		this.beforeWrapper = beforeWrapper;
	}

	public String getAfterWrapper() {
		return afterWrapper;
	}

	public void setAfterWrapper(String afterWrapper) {
		this.afterWrapper = afterWrapper;
	}

	public String getKeyExLower() {
		return keyExLower;
	}

	public void setKeyExLower(String keyExLower) {
		this.keyExLower = keyExLower;
	}

	public String getKeyExUpper() {
		return keyExUpper;
	}

	public void setKeyExUpper(String keyExUpper) {
		this.keyExUpper = keyExUpper;
	}

	public String getValueExLower() {
		return valueExLower;
	}

	public void setValueExLower(String valueExLower) {
		this.valueExLower = valueExLower;
	}

	public String getValueExUpper() {
		return valueExUpper;
	}

	public void setValueExUpper(String valueExUpper) {
		this.valueExUpper = valueExUpper;
	}


	public String getBeforeColor() {
		return beforeColor;
	}


	public void setBeforeColor(String beforeColor) {
		this.beforeColor = beforeColor;
	}


	public String getAfterColor() {
		return afterColor;
	}


	public void setAfterColor(String afterColor) {
		this.afterColor = afterColor;
	}


	public String getFirstElementKey() {
		return firstElementKey;
	}


	public void setFirstElementKey(String firstElementKey) {
		this.firstElementKey = firstElementKey;
		firstElement = true;
	}


	public String getFirstElementValue() {
		return firstElementValue;
	}


	public void setFirstElementValue(String firstElementValue) {
		this.firstElementValue = firstElementValue;
		firstElement = true;
	}


	public String getLastElementKey() {
		return lastElementKey;
	}


	public void setLastElementKey(String lastElementKey) {
		this.lastElementKey = lastElementKey;
		lastElement = true;
	}


	public String getLastElementValue() {
		return lastElementValue;
	}


	public void setLastElementValue(String lastElementValue) {
		this.lastElementValue = lastElementValue;
		lastElement = true;
	}


	public boolean isFirstElement() {
		return firstElement;
	}


	public void setFirstElement(boolean firstElement) {
		this.firstElement = firstElement;
	}


	public boolean isLastElement() {
		return lastElement;
	}


	public void setLastElement(boolean lastElement) {
		this.lastElement = lastElement;
	}
	
}
