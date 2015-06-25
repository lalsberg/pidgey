package br.com.pidgey.parser;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

//Essa classe existe pois o java nao fornece um "unmodifiable iterator" 
//e eu quero permitir que os clientes apenas iterem, por isso nao retorno 
//uma unmodifiable list.
public class PFieldElements implements Iterable<PFieldElement>{
	
	private final List<PFieldElement> elements;

	public PFieldElements(List<PFieldElement> elements) {
		this.elements = Collections.unmodifiableList(elements);
	}

	public Iterator<PFieldElement> iterator() {
		return elements.iterator();
	}

}
