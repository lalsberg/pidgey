package br.com.pidgey.test.internal.run;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.pidgey.parser.ListOfJavaTypeSplitter;
import br.com.pidgey.parser.PFieldElement;

public class ListOfJavaTypeSplitterTest {
	
	@Test
	public void testSplitListOfStrings() {
		
		List<String> theList = new ArrayList<String>();
		theList.add("ELEMENT");
		theList.add("ELEMENT");
		theList.add("ELEMENT");
		int listSizeSum = 0;
		int size = 10;
		int position = 0;
		int repeated = 3;
		
		ListOfJavaTypeSplitter splitter = new ListOfJavaTypeSplitter();
		List<PFieldElement> elements = splitter.split(theList, listSizeSum, 
				size, position, repeated);
		
		PFieldElement item1 = elements.get(0);
		assertEquals(0, item1.getStartPosition());
		assertEquals(10, item1.getEndPosition());
		assertEquals("ELEMENT", item1.getValue());
		
		PFieldElement item2 = elements.get(1);
		assertEquals(10, item2.getStartPosition());
		assertEquals(20, item2.getEndPosition());
		assertEquals("ELEMENT", item2.getValue());
		
		PFieldElement item3 = elements.get(2);
		assertEquals(20, item3.getStartPosition());
		assertEquals(30, item3.getEndPosition());
		assertEquals("ELEMENT", item3.getValue());
	}
	
	@Test
	public void testSplitListOfStringsWithNullElements() {
		
		List<String> theList = new ArrayList<String>();
		theList.add(null);
		theList.add(null);
		theList.add(null);
		int listSizeSum = 0;
		int size = 10;
		int position = 0;
		int repeated = 3;
		
		ListOfJavaTypeSplitter splitter = new ListOfJavaTypeSplitter();
		List<PFieldElement> elements = splitter.split(theList, listSizeSum, 
				size, position, repeated);
		
		PFieldElement item1 = elements.get(0);
		assertEquals(0, item1.getStartPosition());
		assertEquals(10, item1.getEndPosition());
		assertNull(item1.getValue());
		
		PFieldElement item2 = elements.get(1);
		assertEquals(10, item2.getStartPosition());
		assertEquals(20, item2.getEndPosition());
		assertNull(item2.getValue());
		
		PFieldElement item3 = elements.get(2);
		assertEquals(20, item3.getStartPosition());
		assertEquals(30, item3.getEndPosition());
		assertNull(item3.getValue());
	}
	
	//TODO test other java types.
	//TODO test every other branch of ListOfJavaTypeSplitter
}