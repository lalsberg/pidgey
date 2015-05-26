package br.com.pidgey.test.model.types;

import java.util.List;

import br.com.pidgey.annotation.Many;
import br.com.pidgey.annotation.PField;

public class ObjectWithListOfStrings {
	
	@Many(repeated=3)
	@PField(position=0, size=10)
	private List<String> theList;

	public List<String> getTheList() {
		return theList;
	}

	public void setTheList(List<String> theList) {
		this.theList = theList;
	}

	@Override
	public String toString() {
		return "ObjectWithListOfStrings [theList=" + theList + "]";
	}

}