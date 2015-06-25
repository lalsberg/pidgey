package br.com.pidgey.test.internal.run;

import org.junit.Test;

import br.com.pidgey.parser.PFieldElement;
import br.com.pidgey.parser.PFieldElements;
import br.com.pidgey.parser.PFieldSplitter;
import br.com.pidgey.test.mock.Mocker;
import br.com.pidgey.test.model.types.ObjectWithListOfStrings;
import static org.junit.Assert.assertEquals;

public class PFieldSplitterTest {
	
	@Test
	public void testExplodeListOfStrings() {
		ObjectWithListOfStrings objectWithListOfString = 
				Mocker.getObjectWithListOfString();
		PFieldSplitter splitter = new PFieldSplitter();
		PFieldElements elements = splitter.split(objectWithListOfString);
		
		PFieldElement item1 = elements.iterator().next();
		assertEquals(0, item1.getStartPosition());
		assertEquals(10, item1.getEndPosition());
		assertEquals("ELEMENT", item1.getValue());
		
		PFieldElement item2 = elements.iterator().next();
		assertEquals(10, item2.getStartPosition());
		assertEquals(20, item2.getEndPosition());
		assertEquals("ELEMENT", item2.getValue());
		
		PFieldElement item3 = elements.iterator().next();
		assertEquals(20, item3.getStartPosition());
		assertEquals(30, item3.getEndPosition());
		assertEquals("ELEMENT", item3.getValue());
	}
	
}
