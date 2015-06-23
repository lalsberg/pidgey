package br.com.pidgey.parser;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

//Essa classe existe pois o java nao fornece um "unmodifiable iterator" 
//e eu quero permitir que os clientes apenas iterem, por isso nao retorno 
//uma unmodifiable list.
public class Appendables implements Iterable<AppendableItem>{
	
	private final List<AppendableItem> items;

	public Appendables(List<AppendableItem> items) {
		this.items = Collections.unmodifiableList(items);
	}

	public Iterator<AppendableItem> iterator() {
		return items.iterator();
	}

}
