package br.com.pidgey.test.internal.run;

import org.junit.Test;

import br.com.pidgey.parser.AppendableItem;
import br.com.pidgey.parser.Appendables;
import br.com.pidgey.parser.Exploder;
import br.com.pidgey.test.mock.Mocker;
import br.com.pidgey.test.model.types.ObjectWithListOfStrings;
import static org.junit.Assert.assertEquals;

public class ExploderTest {
	
	@Test
	public void testExplodeListOfStrings() {
		ObjectWithListOfStrings objectWithListOfString = 
				Mocker.getObjectWithListOfString();
		Exploder exploder = new Exploder();
		Appendables applendables = exploder.explode(objectWithListOfString);
		
		AppendableItem item1 = applendables.iterator().next();
		assertEquals(0, item1.getStartPosition());
		assertEquals(10, item1.getEndPosition());
		assertEquals("ELEMENT", item1.getValue());
		
		AppendableItem item2 = applendables.iterator().next();
		assertEquals(10, item2.getStartPosition());
		assertEquals(20, item2.getEndPosition());
		assertEquals("ELEMENT", item2.getValue());
		
		AppendableItem item3 = applendables.iterator().next();
		assertEquals(20, item3.getStartPosition());
		assertEquals(30, item3.getEndPosition());
		assertEquals("ELEMENT", item3.getValue());
	}
	
}
