package classmapper;
import java.util.List;
import java.util.Map;

public class DevisionContext extends MapperContext {

	public DevisionContext(){
		super();
		afterKey = afterValue= " ";
	}
	
	@Override
	protected void updateBinding(){
		if(!this.isCustomBinding()){
			this.setBinding("Does not support Binding");
		}
	}

	protected String bindKey(String key){
		return this.getBeforeKey() + key + this.getAfterKey();	 
	}
	
	protected String bindValue(String value){
		return this.getBeforeValue() + value + this.getAfterValue();
	}
	
	protected String bindColor(String value){
		String color = "000000";
		if(!value.equals("null")){
		color = Integer.toHexString((Integer.parseInt(value)));
		while(color.length()<6){
			color = "0"+color;
		}}
		return this.getBeforeValue() + color + this.getBeforeColor() + color + this.getAfterColor() + this.getAfterValue();
	}

	@Override
	public String map(String key, String value) {
		return this.getBeforeRow() + bindKey(key)  + this.getAfterRow() + divideFields+ this.getBeforeRow() + bindValue(value) + this.getAfterRow();
	}	
	
	@Override
	public String map(String [] keys, String [] values){
		if(keys.length != values.length){
			throw new MapUnmatchedException();
		}
		StringBuilder builder = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		builder.append(this.getBeforeWrapper());
		builder.append(this.getBeforeRow());
		for(int i=0; i<keys.length; i++){
			if(i==0 && this.isFirstElement()){
				builder.append(bindKey(this.getFirstElementKey()));
				builder2.append(bindValue(this.getFirstElementValue()));
			}
			builder.append(bindKey(keys[i]));
			if(keys[i].toLowerCase().equals("color")){
			builder2.append(bindColor(values[i]));	
			}else{
			builder2.append(bindValue(values[i]));
			}
			if(i==keys.length && this.isLastElement()){
				builder.append(bindKey(this.getLastElementKey()));
				builder2.append(bindValue(this.getLastElementValue()));
			}
		}
		builder.append(this.getAfterRow());
		builder.append(divideFields);
		builder.append(this.getBeforeRow());
		builder.append(builder2);
		builder.append(this.getAfterRow());
		builder.append(this.getAfterWrapper());
		return builder.toString();
	}
	
	@Override
	public String map(List<Map<String, String>> list) {
		int colorColumn = -1;
		StringBuilder builder = new StringBuilder();
		StringBuilder builder2 = new StringBuilder(); 
		builder.append(this.getBeforeWrapper());
		// map keys

		builder.append(this.getBeforeRow());
		if(this.isFirstElement() && list.size()>0){
			builder.append(bindKey(this.getFirstElementKey()));
		}
		for(int i=0; i<1 && list.size()>0;i++){
			int j = 0;
			for(String k : list.get(i).keySet()){
				builder.append(bindKey(k));
				if(k.toLowerCase().equals("color")){
					colorColumn = j;	
				}
				j++;
			}
		}
		if(this.isLastElement()&& list.size()>0){
			builder.append(bindKey(this.getLastElementKey()));
		}
		builder.append(this.getAfterRow());
		builder.append(divideFields);
		
		for(int i=0; i<list.size();i++){
			builder2.append(this.getBeforeRow());
			if(this.isFirstElement()){
				builder2.append(bindValue(this.getFirstElementValue()));
			}
			int j = 0;
			for(String v : list.get(i).values()){
				if(colorColumn == j){
					builder2.append(bindColor(v));	
				}else{
					builder2.append(bindValue(v));
				}
				j++;
			}
			if(this.isLastElement()){
				builder2.append(bindValue(this.getLastElementValue()));	
			}
			builder2.append(this.getAfterRow());
			builder2.append(divideFields);
		}
		builder.append(builder2);
		builder.append(this.getAfterWrapper());
		return builder.toString();
	}

}
