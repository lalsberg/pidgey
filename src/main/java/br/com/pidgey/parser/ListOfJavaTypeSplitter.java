package br.com.pidgey.parser;

import java.util.ArrayList;
import java.util.List;

public class ListOfJavaTypeSplitter {

	public List<PFieldElement> split(Object value, int listSizeSum, int size,
			int position, int repeated) {
		
		List<PFieldElement> elements = new ArrayList<PFieldElement>();
		
		@SuppressWarnings("unchecked")
		List<Object> values = (List<Object>) value;
		
		int listLimit;
		if(repeated != -1) {
			listLimit = repeated;
		} else {
			listLimit = values.size();
		}
		
		for(int i=0; i<listLimit; i++) {
			int dynamicPosition = position + (size * i) + listSizeSum;
			
			Object itemValue;
			if(values == null || i >= values.size()) {
				itemValue = null;
			} else {
				itemValue = values.get(i);
			}
			
			int endPosition = dynamicPosition + size;
			
			PFieldElement element = new PFieldElement(dynamicPosition, endPosition, itemValue);
			elements.add(element);
		}
		return elements;
	}

}