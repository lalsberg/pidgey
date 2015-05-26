package br.com.pidgey.test.model.types;

import java.util.ArrayList;
import java.util.List;

import br.com.pidgey.annotation.Many;
import br.com.pidgey.annotation.PField;

public class ObjectWithListOfIntegers {
	
	@Many(repeated=3)
	@PField(position=0, size=10)
	private List<Integer> list = new ArrayList<Integer>();

	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "ObjectWithListOfIntegers [list=" + list + "]";
	}

}