package br.com.pidgey.test.internal.run;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

import br.com.pidgey.parser.PFieldElement;
import br.com.pidgey.parser.PFieldSplitter;
import br.com.pidgey.test.mock.Mocker;
import br.com.pidgey.test.model.types.ObjectWithListOfStrings;

public class PFieldSplitterTest {
	
	@Test
	public void testSplitListOfStrings() {
		ObjectWithListOfStrings objectWithListOfString = 
				Mocker.getObjectWithListOfString();
		PFieldSplitter splitter = new PFieldSplitter();
		Iterator<PFieldElement> elementsIterator = splitter.split(
				objectWithListOfString);
		
		PFieldElement item1 = elementsIterator.next();
		assertEquals(0, item1.getStartPosition());
		assertEquals(10, item1.getEndPosition());
		assertEquals("ELEMENT", item1.getValue());
		
		PFieldElement item2 = elementsIterator.next();
		assertEquals(10, item2.getStartPosition());
		assertEquals(20, item2.getEndPosition());
		assertEquals("ELEMENT", item2.getValue());
		
		PFieldElement item3 = elementsIterator.next();
		assertEquals(20, item3.getStartPosition());
		assertEquals(30, item3.getEndPosition());
		assertEquals("ELEMENT", item3.getValue());
	}
	
}
