package classmapper;

import java.util.List;
import java.util.Map;

public class NormalContext extends MapperContext{

	@Override
	protected void updateBinding(){
		if(!customBinding){
			binding = beforeRow + beforeKey + keyEx + afterKey + beforeValue + valueEx + afterValue + afterRow;
		}
	}
	@Override
	public String map(String key,String value){
		return beforeRow + beforeKey + key + afterKey + beforeValue + value + afterValue + afterRow;
	}
	@Override
	public String map(String [] keys, String [] values){
		if(keys.length != values.length){
			throw new MapUnmatchedException();
		}
		updateBinding();
		StringBuilder builder = new StringBuilder();
		builder.append(beforeWrapper);
		if(this.isFirstElement() && keys.length>0){
			builder.append(map(this.getFirstElementKey(),this.getFirstElementValue()));
		}
		int count = 1;
		for(int i=0; i<keys.length; i++){
			String keyLow = keys[i].toLowerCase();
			
			builder.append(binding.replaceAll(keyExUpper, keys[i].toUpperCase()).replaceAll(keyExLower,keyLow).replaceAll(keyEx,keys[i]).replaceAll(valueExUpper, values[i].toUpperCase()).replaceAll(valueExLower, values[i].toLowerCase()).replaceAll(valueEx, values[i]));
			count++;
			if(count == fieldCount){
				builder.append(divideFields);
				count = 1;
			}
		}
		if(this.isLastElement()&&keys.length>0){
			builder.append(map(this.getLastElementKey(),this.getLastElementValue()));
		}
		builder.append(afterWrapper);
		return builder.toString();
	}

	@Override
	public String map(List<Map<String, String>> list) {
		StringBuilder builder = new StringBuilder();
		for(Map<String, String> m : list){
			builder.append(map(m.keySet().toArray(new String[m.keySet().size()]),m.values().toArray(new String[m.values().size()])));
		}
		return builder.toString();
	}
}