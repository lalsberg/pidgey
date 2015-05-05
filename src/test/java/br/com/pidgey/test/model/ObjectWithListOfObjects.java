package br.com.pidgey.test.model;

import java.util.List;

import br.com.pidgey.annotation.Many;
import br.com.pidgey.annotation.PField;

public class ObjectWithListOfObjects {
	
	@Many(repeated = 3)
	@PField(size = 259)
	private List<ObjectWithoutId> listObjectWithoutId;
	
}